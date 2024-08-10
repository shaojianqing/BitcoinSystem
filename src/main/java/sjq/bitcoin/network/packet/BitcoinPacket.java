package sjq.bitcoin.network.packet;

import sjq.bitcoin.message.Message;
import sjq.bitcoin.message.parser.MessageParser;

import java.nio.ByteBuffer;

public class BitcoinPacket {

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

    }

    private void parseMessage(ByteBuffer buffer) throws Exception {
        this.message = MessageParser.newMessageInstance(this.command);
        this.message.deserialize(buffer.array());
    }

    public String getCommand() {
        return command;
    }

    public Message getMessage() {
        return message;
    }
}
