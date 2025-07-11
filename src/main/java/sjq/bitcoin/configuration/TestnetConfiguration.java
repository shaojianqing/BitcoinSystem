package sjq.bitcoin.configuration;

import sjq.bitcoin.message.BlockMessage;
import sjq.bitcoin.script.BitcoinNetwork;

import java.util.ArrayList;
import java.util.List;

public class TestnetConfiguration extends NetworkConfiguration {

    private static final int TEST_NET_MAGIC_CODE = 0xf9beb4d9;

    private static final short TEST_NET_NODE_PORT = 18333;

    private static final List<String> DNS_SEED_LIST = new ArrayList<String>();

    public TestnetConfiguration() {
        initDnsSeedList();
    }

    private void initDnsSeedList() {
        DNS_SEED_LIST.add("dnsseed.emzy.de");
        DNS_SEED_LIST.add("seed.bitcoin.sipa.be");
        DNS_SEED_LIST.add("dnsseed.bitcoin.dashjr.org");
    }

    @Override
    public List<String> getDnsSeedList() {
        return DNS_SEED_LIST;
    }

    @Override
    public int getMagicCode() {
        return TEST_NET_MAGIC_CODE;
    }

    @Override
    public short getPort() {
        return TEST_NET_NODE_PORT;
    }

    @Override
    public long getGenesisBlockVersion() {
        return 0;
    }

    @Override
    public long getGenesisBlockNonce() {
        return 0;
    }

    @Override
    public long getGenesisBlockTimestamp() {
        return 0;
    }

    @Override
    public long getGenesisBlockDifficulty() {
        return 0;
    }

    public BlockMessage getGenesisBlock() {
        return null;
    }

    @Override
    public BitcoinNetwork getBitcoinNetwork() {
        return BitcoinNetwork.TESTNET;
    }
}
