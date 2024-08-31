package sjq.bitcoin.network.socket;

import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.network.node.PeerNode;
import sjq.bitcoin.network.packet.BitcoinPacket;
import sjq.bitcoin.network.packet.PacketHeader;
import sjq.bitcoin.network.protocol.ProtocolException;

import java.nio.Buffer;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

public class SocketHandler {

    private PeerNode peerNode;

    private byte[] largeMessageBuffer;

    private int largeMessageBufferPos;

    private PacketHeader packetHeader;

    public SocketHandler(PeerNode peerNode) {
        this.peerNode = peerNode;
    }

    public int handleSocketData(ByteBuffer buffer) {

        try {
            // Repeatedly try to deserialize messages until we hit a BufferUnderflowException
            boolean firstMessage = true;
            while (true) {
                // If we are in the middle of reading a message, try to fill that one first, before we expect another
                if (largeMessageBuffer != null) {
                    // This can only happen in the first iteration
                    if (!firstMessage) {
                        throw new IllegalStateException();
                    }
                    // Read new bytes into the largeReadBuffer
                    int bytesToGet = Math.min(buffer.remaining(), largeMessageBuffer.length - largeMessageBufferPos);
                    buffer.get(largeMessageBuffer, largeMessageBufferPos, bytesToGet);
                    largeMessageBufferPos += bytesToGet;
                    // Check the largeReadBuffer's status
                    if (largeMessageBufferPos == largeMessageBuffer.length) {
                        // ...processing a message if one is available
                        BitcoinPacket packet = BitcoinPacket.parse(packetHeader, ByteBuffer.wrap(largeMessageBuffer));
                        peerNode.processMessage(packet.getMessage());
                        largeMessageBuffer = null;
                        packetHeader = null;
                        firstMessage = false;
                    } else // ...or just returning if we don't have enough bytes yet
                        return buffer.position();
                }

                // Now try to deserialize any messages left in buff
                Message message;
                int preSerializePosition = buffer.position();
                try {
                    message = BitcoinPacket.parse(buffer).getMessage();
                } catch (BufferUnderflowException e) {
                    // If we went through the whole buffer without a full message, we need to use the largeReadBuffer
                    if (firstMessage && buffer.limit() == buffer.capacity()) {
                        // ...so reposition the buffer to 0 and read the next message header
                        ((Buffer) buffer).position(0);
                        try {
                            packetHeader = PacketHeader.parsePacketHeader(buffer);
                            // Initialize the largeReadBuffer with the next message's size and fill it with any bytes
                            // left in buff
                            largeMessageBuffer = new byte[packetHeader.getSize()];
                            largeMessageBufferPos = buffer.remaining();
                            buffer.get(largeMessageBuffer, 0, largeMessageBufferPos);
                        } catch (BufferUnderflowException e1) {
                            // If we went through a whole buffer's worth of bytes without getting a header, give up
                            // In cases where the buff is just really small, we could create a second largeReadBuffer
                            // that we use to deserialize the magic+header, but that is rather complicated when the buff
                            // should probably be at least that big anyway (for efficiency)
                            throw new ProtocolException("No magic bytes+header after reading " + buffer.capacity() + " bytes");
                        }
                    } else {
                        // Reposition the buffer to its original position, which saves us from skipping messages by
                        // seeking past part of the magic bytes before all of them are in the buffer
                        ((Buffer) buffer).position(preSerializePosition);
                    }
                    return buffer.position();
                }
                // Process our freshly deserialized message
                peerNode.processMessage(message);
                firstMessage = false;
            }
        } catch (Exception e) {
            Logger.error("handle socket data error:%s", e);
            e.printStackTrace();
            return -1; // Returning -1 also throws an IllegalStateException upstream and kills the connection
        }
    }
}
