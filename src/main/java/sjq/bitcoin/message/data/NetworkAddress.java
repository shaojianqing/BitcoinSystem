package sjq.bitcoin.message.data;

import sjq.bitcoin.network.Services;
import sjq.bitcoin.utility.ByteUtils;
import sjq.bitcoin.utility.HexUtils;

import java.net.InetAddress;
import java.net.ProtocolException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class NetworkAddress {

    public static final int PROTOCOL_VERSION_1 = 1;

    public static final int PROTOCOL_VERSION_2 = 2;

    public static final int NETWORK_IPV4 = 1;

    public static final int NETWORK_IPV6 = 2;

    private long timestamp;

    private Services services;

    private InetAddress address;

    private String hostname;

    private int port;

    public static NetworkAddress read(ByteBuffer buffer, int protocolVersion) throws Exception {
        if (protocolVersion < PROTOCOL_VERSION_1 || protocolVersion > PROTOCOL_VERSION_2) {
            throw new IllegalStateException("invalid protocolVariant: " + protocolVersion);
        }
        NetworkAddress address = new NetworkAddress();
        address.timestamp = ByteUtils.readUint32LE(buffer);
        if (protocolVersion == PROTOCOL_VERSION_1) {
            address.services = Services.read(buffer);

            byte[] addressBytes = new byte[16];
            buffer.get(addressBytes);
            address.address = InetAddress.getByAddress(addressBytes);
            address.hostname = null;
        } else if (protocolVersion == PROTOCOL_VERSION_2) {
            address.services = Services.wrap(VariableInteger.read(buffer).longValue());
            int networkId = buffer.get();
            byte[] addressBytes = ByteUtils.readLengthPrefixedBytes(buffer);
            if (networkId == NETWORK_IPV4) {
                if (addressBytes.length != 4) {
                    throw new ProtocolException("network address is not ipv4!");
                }
                address.address = InetAddress.getByAddress(addressBytes);
                address.hostname = null;
            } else if (networkId == NETWORK_IPV6) {
                if (addressBytes.length != 16) {
                    throw new ProtocolException("network address is not ipv6!");
                }
                address.address = InetAddress.getByAddress(addressBytes);
                address.hostname = null;
            } else {
                address.address = null;
                address.hostname = null;
            }
        }

        address.port = ByteUtils.readUint16BE(buffer);
        return address;
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

    public InetAddress getAddress() {
        return address;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }
}
