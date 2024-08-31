package sjq.bitcoin.network.packet;

import sjq.bitcoin.utility.ByteUtils;

import java.nio.ByteBuffer;

import static sjq.bitcoin.network.packet.BitcoinPacket.CHECKSUM_LENGTH;
import static sjq.bitcoin.network.packet.BitcoinPacket.COMMAND_LENGTH;
import static sjq.bitcoin.network.packet.BitcoinPacket.MESSAGE_LENGTH;

public class PacketHeader {

    private int magic;

    private String command;

    private int size;

    private byte[] checksum;

    private PacketHeader() {
    }

    public static PacketHeader parsePacketHeader(ByteBuffer buffer) {

        PacketHeader packetHeader = new PacketHeader();

        packetHeader.magic = ByteUtils.readInt32BE(buffer);

        byte[] commandBytes = new byte[COMMAND_LENGTH];
        buffer.get(commandBytes, 0, COMMAND_LENGTH);
        packetHeader.command = parseCommand(commandBytes);

        byte[] sizeBytes = new byte[MESSAGE_LENGTH];
        buffer.get(sizeBytes, 0, MESSAGE_LENGTH);
        packetHeader.size = ByteUtils.readInt32LE(ByteBuffer.wrap(sizeBytes));


        byte[] checksumBytes = new byte[CHECKSUM_LENGTH];
        buffer.get(checksumBytes, 0, CHECKSUM_LENGTH);
        packetHeader.checksum = checksumBytes;

        return packetHeader;
    }

    private static String parseCommand(byte[] commandBytes){
        int i = 0;
        while(i<commandBytes.length) {
            if (commandBytes[i] == 0x00) {
                break;
            }
            i++;
        }
        return new String(commandBytes, 0, i);
    }

    public int getMagic() {
        return magic;
    }

    public String getCommand() {
        return command;
    }

    public int getSize() {
        return size;
    }

    public byte[] getChecksum() {
        return checksum;
    }
}
