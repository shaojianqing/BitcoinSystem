package sjq.bitcoin.configuration;

import java.util.ArrayList;
import java.util.List;

public class MainnetConfiguration extends NetworkConfiguration {

    private static final String NETWORK_CLIENT_TYPE = "netty";

    private static final int MAIN_NET_MAGIC_CODE = 0xf9beb4d9;

    private static final short MAIN_NET_NODE_PORT = 8333;

    private static final long GENESIS_BLOCK_VERSION = 1;

    private static final long GENESIS_BLOCK_NONCE = 2083236893;

    private static final long GENESIS_BLOCK_TIMESTAMP = 1231006505;

    private static final long GENESIS_BLOCK_DIFFICULTY = 0x1d00ffffL;

    private static final List<String> DNS_SEED_LIST = new ArrayList<String>();

    public MainnetConfiguration() {
        initDnsSeedList();
    }

    private void initDnsSeedList() {
        DNS_SEED_LIST.add("dnsseed.emzy.de");
        DNS_SEED_LIST.add("dnsseed.bluematt.me");
        DNS_SEED_LIST.add("seed.bitcoin.sipa.be");
        DNS_SEED_LIST.add("seed.bitcoin.wiz.biz");
        DNS_SEED_LIST.add("dnsseed.bitcoin.dashjr.org");
    }

    @Override
    public int getMagicCode() {
        return MAIN_NET_MAGIC_CODE;
    }

    @Override
    public short getPort() {
        return MAIN_NET_NODE_PORT;
    }

    @Override
    public List<String> getDnsSeedList() {
        return DNS_SEED_LIST;
    }

    @Override
    public long getGenesisBlockVersion() {
        return GENESIS_BLOCK_VERSION;
    }

    @Override
    public long getGenesisBlockNonce() {
        return GENESIS_BLOCK_NONCE;
    }

    @Override
    public long getGenesisBlockTimestamp() {
        return GENESIS_BLOCK_TIMESTAMP;
    }

    @Override
    public long getGenesisBlockDifficulty() {
        return GENESIS_BLOCK_DIFFICULTY;
    }

    @Override
    public String getClientType() {
        return NETWORK_CLIENT_TYPE;
    }
}
