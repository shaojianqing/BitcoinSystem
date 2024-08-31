package sjq.bitcoin.network.packet;

import sjq.bitcoin.configuration.NetworkConfiguration;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.parser.MessageParser;
import sjq.bitcoin.network.protocol.ProtocolException;
import sjq.bitcoin.utility.ByteUtils;

import java.nio.ByteBuffer;

public class BitcoinPacket {

    public static final int MAGIC_LENGTH = 4;

    public static final int COMMAND_LENGTH = 12;

    public static final int MESSAGE_LENGTH = 4;

    public static final int CHECKSUM_LENGTH = 4;

    private static final NetworkConfiguration Configuration = NetworkConfiguration.getConfiguration();

    private int magic;

    private String command;

    private int size;

    private byte[] checksum;

    private byte[] messageHash;

    private Message message;

    private BitcoinPacket() {
    }

    public static BitcoinPacket parse(ByteBuffer buffer) throws Exception {
        BitcoinPacket packet = new BitcoinPacket();

        packet.parseHeader(buffer);
        packet.parseMessage(buffer);

        return packet;
    }

    public static BitcoinPacket parse(PacketHeader header, ByteBuffer buffer) throws Exception {
        BitcoinPacket packet = new BitcoinPacket();

        packet.magic = header.getMagic();
        packet.command = header.getCommand();
        packet.size = header.getSize();
        packet.checksum = header.getChecksum();

        packet.parseMessage(buffer);

        return packet;
    }

    private void parseHeader(ByteBuffer buffer) {
        this.magic = ByteUtils.readInt32BE(buffer);

        byte[] commandBytes = new byte[COMMAND_LENGTH];
        buffer.get(commandBytes, 0, COMMAND_LENGTH);
        this.command = parseCommand(commandBytes);

        byte[] sizeBytes = new byte[MESSAGE_LENGTH];
        buffer.get(sizeBytes, 0, MESSAGE_LENGTH);
        this.size = ByteUtils.readInt32LE(ByteBuffer.wrap(sizeBytes));

        byte[] checksumBytes = new byte[CHECKSUM_LENGTH];
        buffer.get(checksumBytes, 0, CHECKSUM_LENGTH);
        this.checksum = checksumBytes;

        if (magic != Configuration.getMagicCode()) {
            throw new ProtocolException("message magic code is not correct!");
        }
    }

    private void parseMessage(ByteBuffer buffer) throws Exception {
        this.message = MessageParser.newMessageInstance(this.command);
        byte[] messageBytes = new byte[buffer.remaining()];
        buffer.get(messageBytes);

        this.message.deserializeMessage(messageBytes);
        this.messageHash = Hash.calculateTwice(messageBytes);
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
