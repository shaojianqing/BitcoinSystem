package sjq.bitcoin.message.data;

import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.utility.ByteUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class BlockHeader {

    private static final int HEADER_SIZE = 80;

    private Hash blockHash;

    private long version;

    private Hash prevBlockHash;

    private Hash merkleRoot;

    private long timestamp;

    private long difficulty;

    private long nonce;

    private VariableInteger trxCount;

    public void read(ByteBuffer buffer) throws Exception {
        this.version = ByteUtils.readUint32LE(buffer);
        this.prevBlockHash = Hash.read(buffer);
        this.merkleRoot = Hash.read(buffer);
        this.timestamp = ByteUtils.readUint32LE(buffer);
        this.difficulty = ByteUtils.readUint32LE(buffer);
        this.nonce = ByteUtils.readUint32LE(buffer);
        this.trxCount = VariableInteger.read(buffer);
        this.blockHash = calculateHash();
    }

    public byte[] serialize() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteUtils.writeInt32LE(version, outputStream);
        outputStream.write(prevBlockHash.serialize());
        outputStream.write(merkleRoot.serialize());
        ByteUtils.writeInt32LE(timestamp, outputStream);
        ByteUtils.writeInt32LE(difficulty, outputStream);
        ByteUtils.writeInt32LE(nonce, outputStream);
        outputStream.write(trxCount.serialize());

        return outputStream.toByteArray();
    }

    public Hash calculateHash() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(HEADER_SIZE);
        ByteUtils.writeInt32LE(version, outputStream);
        outputStream.write(prevBlockHash.serialize());
        outputStream.write(merkleRoot.serialize());
        ByteUtils.writeInt32LE(timestamp, outputStream);
        ByteUtils.writeInt32LE(difficulty, outputStream);
        ByteUtils.writeInt32LE(nonce, outputStream);
        return Hash.wrapReversed(Hash.calculateTwice(outputStream.toByteArray()));
    }

    public Hash getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(Hash blockHash) {
        this.blockHash = blockHash;
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

    public VariableInteger getTrxCount() {
        return trxCount;
    }

    public void setTrxCount(VariableInteger trxCount) {
        this.trxCount = trxCount;
    }

    @Override
    public String toString() {
        return "BlockHeader[" +
                "blockHash=" + blockHash +
                ", version=" + version +
                ", prevBlockHash=" + prevBlockHash +
                ", merkleRoot=" + merkleRoot +
                ", timestamp=" + timestamp +
                ", difficulty=" + difficulty +
                ", nonce=" + nonce +
                ", trxCount=" + trxCount +
                ']';
    }
}
