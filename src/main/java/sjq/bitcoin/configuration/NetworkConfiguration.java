package sjq.bitcoin.configuration;

import sjq.bitcoin.message.BlockMessage;
import sjq.bitcoin.script.BitcoinNetwork;

import java.util.List;

public abstract class NetworkConfiguration {

    private static NetworkConfiguration configuration;

    public synchronized static NetworkConfiguration getConfiguration() {
        if (configuration == null) {
            configuration = new MainnetConfiguration();
        }
        return configuration;
    }

    public abstract List<String> getDnsSeedList();

    public abstract long getGenesisBlockVersion();

    public abstract long getGenesisBlockNonce();

    public abstract long getGenesisBlockTimestamp();

    public abstract long getGenesisBlockDifficulty();

    public abstract int getMagicCode();

    public abstract short getPort();

    public abstract BlockMessage getGenesisBlock();

    public abstract BitcoinNetwork getBitcoinNetwork();
}
