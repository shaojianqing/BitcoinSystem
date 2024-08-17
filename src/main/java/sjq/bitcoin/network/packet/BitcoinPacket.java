package sjq.bitcoin.network.packet;

import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.parser.MessageParser;
import sjq.bitcoin.utility.ByteUtils;

import java.nio.ByteBuffer;

public class BitcoinPacket {

    public static final int MAGIC_LENGTH = 4;

    public static final int COMMAND_LENGTH = 12;

    public static final int MESSAGE_LENGTH = 4;

    public static final int CHECKSUM_LENGTH = 4;

    private String magic;

    private String command;

    private int size;

    private int checksum;

    private Message message;

    private BitcoinPacket(){
    }

    public static BitcoinPacket parse(ByteBuffer buffer) throws Exception {
        BitcoinPacket packet = new BitcoinPacket();

        packet.parseHeader(buffer);
        packet.parseMessage(buffer);

        return packet;
    }

    private void parseHeader(ByteBuffer buffer) throws Exception {
        byte[] magicBytes = new byte[MAGIC_LENGTH];
        buffer.get(magicBytes, 0, MAGIC_LENGTH);
        this.magic = new String(magicBytes);

        byte[] commandBytes = new byte[COMMAND_LENGTH];
        buffer.get(commandBytes, 0, COMMAND_LENGTH);
        this.command = parseCommand(commandBytes);

        byte[] sizeBytes = new byte[MESSAGE_LENGTH];
        buffer.get(sizeBytes, 0, MESSAGE_LENGTH);
        this.size = ByteUtils.readInt32(ByteBuffer.wrap(sizeBytes));

        byte[] checksumBytes = new byte[CHECKSUM_LENGTH];
        buffer.get(checksumBytes, 0, CHECKSUM_LENGTH);
        this.checksum = ByteUtils.readInt32(ByteBuffer.wrap(checksumBytes));
    }

    private void parseMessage(ByteBuffer buffer) throws Exception {
        this.message = MessageParser.newMessageInstance(this.command);
        byte[] messageBytes = new byte[size];
        buffer.get(messageBytes, 0, size);
        this.message.deserialize(messageBytes);
    }

    private String parseCommand(byte[] commandBytes){
        int i = 0;
        while(i<commandBytes.length) {
            if (commandBytes[i] == 0x00) {
                break;
            }
            i++;
        }
        return new String(commandBytes, 0, i);
    }

    public String getCommand() {
        return command;
    }

    public Message getMessage() {
        return message;
    }
}
