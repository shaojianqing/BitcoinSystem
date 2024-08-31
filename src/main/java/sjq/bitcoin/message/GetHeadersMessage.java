package sjq.bitcoin.message;

import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.data.VariableInteger;
import sjq.bitcoin.network.protocol.ProtocolException;
import sjq.bitcoin.utility.ByteUtils;
import sjq.bitcoin.utility.HexUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class GetHeadersMessage extends BaseMessage implements Message {

    public static String COMMAND = "getheaders";

    public static final int MAXIMUM_BLOCK_COUNT = 500;

    private long version;

    private List<Hash> hashList = new ArrayList<Hash>();

    private Hash stopHash;

    @Override
    protected byte[] serializeMessage() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteUtils.writeInt32LE(version, outputStream);

        VariableInteger hashCount = VariableInteger.of(hashList.size());
        outputStream.write(hashCount.serialize());

        for (Hash blockHash:hashList) {
            outputStream.write(blockHash.serialize());
        }

        outputStream.write(stopHash.serialize());
        return outputStream.toByteArray();
    }

    @Override
    public void deserializeMessage(byte[] data) throws Exception {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        this.version = ByteUtils.readInt32LE(buffer);

        VariableInteger hashCount = VariableInteger.read(buffer);
        if (hashCount.intValue() > MAXIMUM_BLOCK_COUNT) {
            throw new ProtocolException("hash count is large than maximum limit!");
        }

        for (int i=0; i<hashCount.intValue(); ++i) {
            Hash blockHash = Hash.read(buffer);
            this.hashList.add(blockHash);
        }

        this.stopHash = Hash.read(buffer);
    }

    public void addHash(Hash hash) {
        this.hashList.add(hash);
    }

    public String getCommand() {
        return COMMAND;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public List<Hash> getHashList() {
        return hashList;
    }

    public void setHashList(List<Hash> hashList) {
        this.hashList = hashList;
    }

    public Hash getStopHash() {
        return stopHash;
    }

    public void setStopHash(Hash stopHash) {
        this.stopHash = stopHash;
    }
}
