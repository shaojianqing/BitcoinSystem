package sjq.bitcoin.message;

import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.utility.ByteUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

public class BlockMessage extends BaseMessage implements Message {

    public static String COMMAND = "block";

    private static final int HEADER_LENGTH = 4 + 32 + 32 + 4 + 4 + 4;

    private long version;

    private Hash prevBlockHash;

    private Hash blockHash;

    private Hash merkleRoot;

    private long timestamp;

    private long difficulty;

    private long nonce;

    private List<TransactionMessage> transactions;

    public BlockMessage() {
    }

    @Override
    public void deserialize(byte[] data) throws Exception {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        this.version = ByteUtils.readInt32LE(buffer);

        this.prevBlockHash = Hash.read(buffer);
        this.merkleRoot = Hash.read(buffer);

        this.timestamp = ByteUtils.readInt32LE(buffer);
        this.difficulty = ByteUtils.readInt32LE(buffer);
        this.nonce = ByteUtils.readInt32LE(buffer);

        buffer.rewind();

        byte[] headerBytes = new byte[HEADER_LENGTH];
        byte[] blockHashBytes = Hash.calculateTwice(headerBytes);
        this.blockHash = Hash.wrapReversed(blockHashBytes);

    }

    @Override
    public int messageSize() {
        return 0;
    }


    @Override
    protected byte[] serializeMessage() throws IOException {
        return new byte[0];
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }

}
