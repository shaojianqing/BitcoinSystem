package sjq.bitcoin.network.client;

import org.apache.commons.lang3.StringUtils;
import sjq.bitcoin.configuration.NetworkConfiguration;

import java.net.InetSocketAddress;

public class ClientFactory {

    private static final String TYPE_CLIENT_SOCKET = "socket";

    private static final String TYPE_CLIENT_NETTY = "netty";

    private static final NetworkConfiguration configuration = NetworkConfiguration.getConfiguration();

    private static final String clientType = configuration.getClientType();

    public static Client createClientInstance(InetSocketAddress address, Callback callback) throws Exception {
        if (StringUtils.equals(TYPE_CLIENT_SOCKET, clientType)) {
            return new SocketClient(address, callback);
        } else if (StringUtils.equals(TYPE_CLIENT_NETTY, clientType)) {
            return new NettyClient(address, callback);
        }
        throw new RuntimeException(String.format("client type is unexpected, type:%s", clientType));
    }
}
