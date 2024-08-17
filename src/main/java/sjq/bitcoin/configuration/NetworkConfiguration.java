package sjq.bitcoin.configuration;

import sjq.bitcoin.message.BlockMessage;

import java.util.Arrays;
import java.util.List;

public abstract class NetworkConfiguration {

    private static NetworkConfiguration configuration;

    protected static String[] dnsSeedList;

    protected static int magicCode;

    protected static short port;

    public static NetworkConfiguration getConfiguration() {
        if (configuration == null) {
            configuration = new MainnetConfiguration();
        }
        return configuration;
    }

    public abstract BlockMessage getGenesisBlock();

    public List<String> getDnsSeedList() {
        return Arrays.asList(dnsSeedList);
    }

    public int getMagicCode() {
        return magicCode;
    }

    public short getPort() {
        return port;
    }
}
