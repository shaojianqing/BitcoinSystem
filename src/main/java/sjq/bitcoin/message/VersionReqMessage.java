package sjq.bitcoin.message;

import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.data.VariableInteger;
import sjq.bitcoin.network.Services;
import sjq.bitcoin.utility.AddressUtils;
import sjq.bitcoin.utility.ByteUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import static sjq.bitcoin.constant.Constants.IPV6_LENGTH;

public class VersionReqMessage extends BaseMessage implements Message {

    public static String COMMAND = "version";

    private static final int NONCE_LENGTH = 8;

    private int clientVersion;

    private long timestamp;

    private Services localServices;

    private Services receivingServices;

    private InetSocketAddress receivingAddress;

    private String userAgent;

    private int bestBlockHeight;

    private boolean relay;

    public VersionReqMessage() {
        super(COMMAND);
    }

    @Override
    public byte[] serializeMessage() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteUtils.writeInt32LE(clientVersion, outputStream);
        outputStream.write(localServices.serialize());
        ByteUtils.writeInt64LE(timestamp, outputStream);
        outputStream.write(receivingServices.serialize());

        byte[] ipv6Address = AddressUtils.normalizeIpAddress(receivingAddress.getAddress().getAddress());
        outputStream.write(ipv6Address);
        ByteUtils.writeInt16BE(receivingAddress.getPort(), outputStream);
        outputStream.write(AddressUtils.dummyAddress());

        ByteUtils.writeInt64LE(0, outputStream);

        byte[] userAgentBytes = userAgent.getBytes();
        outputStream.write(VariableInteger.of(userAgent.length()).serialize());
        outputStream.write(userAgentBytes);
        ByteUtils.writeInt32LE(bestBlockHeight, outputStream);
        outputStream.write(relay ? 1 : 0);

        return outputStream.toByteArray();
    }

    @Override
    public void deserializeMessage(byte[] data) throws Exception {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        this.clientVersion = (int) ByteUtils.readUint32LE(buffer);
        this.localServices = Services.read(buffer);
        this.timestamp = ByteUtils.readInt64LE(buffer);
        this.receivingServices = Services.read(buffer);
        byte[] receivingAddressBytes = new byte[IPV6_LENGTH];
        buffer.get(receivingAddressBytes);
        InetAddress receivingAddress = InetAddress.getByAddress(receivingAddressBytes);
        int receivingPort = ByteUtils.readUint16BE(buffer);
        this.receivingAddress =  new InetSocketAddress(receivingAddress, receivingPort);
        // Here we just ignore some data fields which is not important.
        // They are mainly from address and nonce field
        int skipByteCount = Services.LENGTH + IPV6_LENGTH + Short.BYTES + NONCE_LENGTH;
        buffer.position(buffer.position() + skipByteCount);

        byte[] userAgentBytes = ByteUtils.readLengthPrefixedBytes(buffer);
        this.userAgent = new String(userAgentBytes);
        this.bestBlockHeight = ByteUtils.readInt32LE(buffer);
        if (clientVersion >= Constants.VERSION_BLOOM_FILTER) {
            this.relay = (buffer.get() != 0);
        } else {
            this.relay = true;
        }
    }

    public int getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(int clientVersion) {
        this.clientVersion = clientVersion;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Services getLocalServices() {
        return localServices;
    }

    public void setLocalServices(Services localServices) {
        this.localServices = localServices;
    }

    public Services getReceivingServices() {
        return receivingServices;
    }

    public void setReceivingServices(Services receivingServices) {
        this.receivingServices = receivingServices;
    }

    public InetSocketAddress getReceivingAddress() {
        return receivingAddress;
    }

    public void setReceivingAddress(InetSocketAddress receivingAddress) {
        this.receivingAddress = receivingAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public int getBestBlockHeight() {
        return bestBlockHeight;
    }

    public void setBestBlockHeight(int bestBlockHeight) {
        this.bestBlockHeight = bestBlockHeight;
    }

    public boolean isRelay() {
        return relay;
    }

    public void setRelay(boolean relay) {
        this.relay = relay;
    }
}