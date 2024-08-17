package sjq.bitcoin.configuration;

import sjq.bitcoin.message.BlockMessage;

public class TestnetConfiguration extends NetworkConfiguration {

    public TestnetConfiguration() {
        dnsSeedList = new String[] {};

        port = 18333;
    }

    public BlockMessage getGenesisBlock() {
        return new BlockMessage();
    }
}
