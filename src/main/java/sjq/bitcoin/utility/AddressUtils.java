package sjq.bitcoin.utility;

public class AddressUtils {

    public static final int IPv4_LENGTH = 4;

    public static final int IPv6_LENGTH = 16;

    public static final int ADDRESS_LENGTH = 26;

    public static byte[] normalizeIpAddress(byte[] ip) {

        if (ip.length == IPv6_LENGTH) {
            return ip;
        }
        byte[] ipv6 = new byte[IPv6_LENGTH];
        System.arraycopy(ip, 0, ipv6, 12, IPv4_LENGTH);
        ipv6[10] = (byte) 0xFF;
        ipv6[11] = (byte) 0xFF;
        return ipv6;
    }

    public static byte[] dummyAddress() {
        return new byte[ADDRESS_LENGTH ];
    }
}
