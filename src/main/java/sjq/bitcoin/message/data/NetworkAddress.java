package sjq.bitcoin.message.data;

import sjq.bitcoin.configuration.NetworkConfiguration;
import sjq.bitcoin.network.Services;
import sjq.bitcoin.utility.ByteUtils;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.nio.ByteBuffer;

public class NetworkAddress {

    public static final int PROTOCOL_VERSION_1 = 1;

    public static final int PROTOCOL_VERSION_2 = 2;

    public static final int NETWORK_IPV4 = 1;

    public static final int NETWORK_IPV6 = 2;

    private long timestamp;

    private Services services;

    private InetSocketAddress address;

    private String hostname;

    private int port;

    public static NetworkAddress read(ByteBuffer buffer, int protocolVersion) throws Exception {
        if (protocolVersion < PROTOCOL_VERSION_1 || protocolVersion > PROTOCOL_VERSION_2) {
            throw new IllegalStateException("invalid protocolVariant: " + protocolVersion);
        }

        NetworkConfiguration configuration = NetworkConfiguration.getConfiguration();
        NetworkAddress networkAddress = new NetworkAddress();
        networkAddress.timestamp = ByteUtils.readUint32LE(buffer);
        if (protocolVersion == PROTOCOL_VERSION_1) {
            networkAddress.services = Services.read(buffer);

            byte[] addressBytes = new byte[16];
            buffer.get(addressBytes);
            InetAddress inetAddress = InetAddress.getByAddress(addressBytes);
            networkAddress.port = ByteUtils.readUint16BE(buffer);
            networkAddress.address = new InetSocketAddress(inetAddress, networkAddress.port);
            networkAddress.hostname = null;
        } else if (protocolVersion == PROTOCOL_VERSION_2) {
            networkAddress.services = Services.wrap(VariableInteger.read(buffer).longValue());
            int networkId = buffer.get();
            byte[] addressBytes = ByteUtils.readLengthPrefixedBytes(buffer);
            if (networkId == NETWORK_IPV4) {
                if (addressBytes.length != 4) {
                    throw new ProtocolException("network address is not ipv4!");
                }
                InetAddress inetAddress = InetAddress.getByAddress(addressBytes);
                networkAddress.port = ByteUtils.readUint16BE(buffer);
                networkAddress.address = new InetSocketAddress(inetAddress, networkAddress.port);
                networkAddress.hostname = null;
            } else if (networkId == NETWORK_IPV6) {
                if (addressBytes.length != 16) {
                    throw new ProtocolException("network address is not ipv6!");
                }
                InetAddress inetAddress = InetAddress.getByAddress(addressBytes);
                networkAddress.port = ByteUtils.readUint16BE(buffer);
                networkAddress.address = new InetSocketAddress(inetAddress, networkAddress.port);
                networkAddress.hostname = null;
            } else {
                networkAddress.address = null;
                networkAddress.hostname = null;
            }
        }
        return networkAddress;
    }

    public static ByteBuffer write(ByteBuffer buffer, int protocolVersion) {
        return buffer;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Services getServices() {
        return services;
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }
}
