package sjq.bitcoin.configuration;

import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.message.BlockMessage;
import sjq.bitcoin.message.data.BlockHeader;
import sjq.bitcoin.storage.domain.Block;
import sjq.bitcoin.utility.ByteUtils;
import sjq.bitcoin.utility.HexUtils;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

public abstract class NetworkConfiguration {

    private static NetworkConfiguration configuration;

    public static NetworkConfiguration getConfiguration() {
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
}
