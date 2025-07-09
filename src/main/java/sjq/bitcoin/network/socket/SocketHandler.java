package sjq.bitcoin.network.socket;

import sjq.bitcoin.configuration.NetworkConfiguration;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.network.node.PeerNode;
import sjq.bitcoin.network.packet.BitcoinPacket;
import sjq.bitcoin.network.packet.PacketHeader;
import sjq.bitcoin.network.exception.ProtocolException;

import java.nio.Buffer;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

public class SocketHandler {

    private final PeerNode peerNode;

    private byte[] largeMessageBuffer;

    private int largeMessageBufferPos;

    private PacketHeader packetHeader;

    public SocketHandler(PeerNode peerNode) {
        this.peerNode = peerNode;
    }

    public int handleSocketData(ByteBuffer buffer) {
        boolean isNormal = buffer.position()==0 && buffer.capacity()>BitcoinPacket.HEADER_LENGTH;
        if (!isNormal) {
            throw new IllegalArgumentException("byte buffer from peer is not correct!");
        }

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
                    seekPastMagicPosition(buffer);
                    message = BitcoinPacket.parse(buffer).getMessage();
                } catch (BufferUnderflowException e) {
                    // If we went through the whole buffer without a full message, we need to use the largeReadBuffer
                    if (firstMessage && buffer.limit() == buffer.capacity()) {
                        // ...so reposition the buffer to 0 and read the next message header
                        buffer.position(0);
                        try {
                            seekPastMagicPosition(buffer);
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

    public void seekPastMagicPosition(ByteBuffer buffer) throws BufferUnderflowException {
        int magicCursor = 3;  // Which byte of the magic we're looking for currently.
        int magicCode = NetworkConfiguration.getConfiguration().getMagicCode();
        while (true) {
            byte b = buffer.get();
            // We're looking for a run of bytes that is the same as the packet magic but we want to ignore partial
            // magics that aren't complete. So we keep track of where we're up to with magicCursor.
            byte expectedByte = (byte)(0xFF & magicCode >>> (magicCursor * 8));
            if (b == expectedByte) {
                magicCursor--;
                if (magicCursor < 0) {
                    // We found the magic sequence.
                    return;
                } else {
                    // We still have further to go to find the next message.
                }
            } else {
                magicCursor = 3;
            }
        }
    }
}
