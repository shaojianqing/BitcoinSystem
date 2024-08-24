package sjq.bitcoin.message.data;

import sjq.bitcoin.network.Services;

import java.net.InetAddress;

public class NetworkAddress {

    private int timestamp;

    private Services services;

    private InetAddress address;

    private String hostname;

    private short port;

    public int getTimestamp() {
        return timestamp;
    }

    public Services getServices() {
        return services;
    }

    public InetAddress getAddress() {
        return address;
    }

    public String getHostname() {
        return hostname;
    }

    public short getPort() {
        return port;
    }
}
