package sjq.bitcoin.message;

import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.data.VariableInteger;
import sjq.bitcoin.utility.ByteUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
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
        super(COMMAND);
        transactions = new ArrayList<>();
    }

    public void calculateBlockHash() {
        ByteBuffer buffer = ByteBuffer.allocate(HEADER_LENGTH);
        ByteUtils.writeInt32LE(this.version, buffer);
        buffer.put(this.prevBlockHash.serialize());
        buffer.put(this.merkleRoot.serialize());
        ByteUtils.writeInt32LE(this.timestamp, buffer);
        ByteUtils.writeInt32LE(this.difficulty, buffer);
        ByteUtils.writeInt32LE(this.nonce, buffer);

        byte[] headerBytes = buffer.array();
        byte[] blockHashBytes = Hash.calculateTwice(headerBytes);
        this.blockHash = Hash.wrapReversed(blockHashBytes);
    }

    @Override
    public byte[] serializeMessage() throws IOException {
        return new byte[0];
    }

    @Override
    public void deserializeMessage(byte[] data) throws Exception {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        buffer.mark();

        this.version = ByteUtils.readInt32LE(buffer);
        this.prevBlockHash = Hash.read(buffer);
        this.merkleRoot = Hash.read(buffer);
        this.timestamp = ByteUtils.readInt32LE(buffer);
        this.difficulty = ByteUtils.readInt32LE(buffer);
        this.nonce = ByteUtils.readInt32LE(buffer);

        buffer.reset();

        byte[] headerBytes = ByteUtils.readBytesByLength(buffer, HEADER_LENGTH);
        byte[] blockHashBytes = Hash.calculateTwice(headerBytes);
        this.blockHash = Hash.wrapReversed(blockHashBytes);

        this.readTransactions(buffer);
    }

    private void readTransactions(ByteBuffer buffer) throws Exception {
        if (buffer.hasRemaining()) {
            VariableInteger transactionCount = VariableInteger.read(buffer);
            for (int i=0;i<transactionCount.intValue();++i) {
                transactions.add(TransactionMessage.read(buffer, Constants.VERSION_CURRENT));
            }
        }
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Hash getPrevBlockHash() {
        return prevBlockHash;
    }

    public void setPrevBlockHash(Hash prevBlockHash) {
        this.prevBlockHash = prevBlockHash;
    }

    public Hash getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(Hash blockHash) {
        this.blockHash = blockHash;
    }

    public Hash getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(Hash merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(long difficulty) {
        this.difficulty = difficulty;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }

    public List<TransactionMessage> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionMessage> transactions) {
        this.transactions = transactions;
    }
}
