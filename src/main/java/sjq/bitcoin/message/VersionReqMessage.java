package sjq.bitcoin.message;

import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.data.VariableInteger;
import sjq.bitcoin.network.Services;
import sjq.bitcoin.utility.AddressUtils;
import sjq.bitcoin.utility.ByteUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;

public class VersionReqMessage extends BaseMessage implements Message {

    public static String COMMAND = "version";

    private int clientVersion;

    private long timestamp;

    private Services localServices;

    private Services receivingServices;

    private InetSocketAddress receivingAddress;

    private String userAgent;

    private int bestBlockHeight;

    private boolean relay;

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
    public void deserialize(byte[] data){

    }

    @Override
    public String getCommand() {
        return COMMAND;
    }

    @Override
    public int messageSize() {
        return 0;
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