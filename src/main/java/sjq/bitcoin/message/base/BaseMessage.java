package sjq.bitcoin.message.base;


import sjq.bitcoin.configuration.NetworkConfiguration;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.message.data.NetworkAddress;
import sjq.bitcoin.utility.ByteUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static sjq.bitcoin.network.packet.BitcoinPacket.*;

public abstract class BaseMessage implements Message {

    private static final int magicCode = NetworkConfiguration.getConfiguration().getMagicCode();

    private final String command;

    protected final int protocolVersion;

    public BaseMessage(String command, int protocolVersion) {
        this.command = command;
        this.protocolVersion = protocolVersion;
    }

    public BaseMessage(String command) {
        this.command = command;
        this.protocolVersion = NetworkAddress.PROTOCOL_VERSION_1;
    }

    private byte[] serializeHeader(byte[] messageBody) {
        byte[] messageHeader = new byte[MAGIC_LENGTH + COMMAND_LENGTH + MESSAGE_LENGTH + CHECKSUM_LENGTH];
        ByteUtils.writeInt32BE(magicCode, messageHeader, 0);

        String command = getCommand();
        for (int i = 0; i < command.length() && i < COMMAND_LENGTH; i++) {
            messageHeader[MAGIC_LENGTH + i] = (byte) (command.codePointAt(i) & 0xFF);
        }

        ByteUtils.writeInt32LE(messageBody.length, messageHeader, MAGIC_LENGTH + COMMAND_LENGTH);
        byte[] messageHash = Hash.calculateTwice(messageBody);
        System.arraycopy(messageHash, 0, messageHeader, MAGIC_LENGTH + COMMAND_LENGTH + MESSAGE_LENGTH, CHECKSUM_LENGTH);

        return messageHeader;
    }

    public byte[] serialize() throws IOException {
        byte[] messageBody = serializeMessage();
        byte[] headerBody = serializeHeader(messageBody);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(headerBody);
        outputStream.write(messageBody);

        return outputStream.toByteArray();
    }

    protected byte[] serializeMessage() throws IOException {
        return new byte[0];
    }

    public void deserializeMessage(byte[] data) throws Exception {
    }

    public String getCommand() {
        return command;
    }
}
