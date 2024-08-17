package sjq.bitcoin.configuration;

import sjq.bitcoin.message.BlockMessage;

public class MainnetConfiguration extends NetworkConfiguration {

    public MainnetConfiguration() {
        dnsSeedList = new String[] {
                "seed.bitcoin.sipa.be",
                "dnsseed.bluematt.me",
                "dnsseed.bitcoin.dashjr.org",
                "dnsseed.emzy.de",
                "seed.bitcoin.wiz.biz"};

        port = 8333;
        magicCode = 0xf9beb4d9;
    }

    public BlockMessage getGenesisBlock() {
        return new BlockMessage();
    }
}
