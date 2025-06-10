package sjq.bitcoin.script;

import org.junit.Test;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.service.data.LegacyAddress;
import sjq.bitcoin.utility.HexUtils;

public class TestLegacyAddress {

    private final String addressStr = "047211a824f55b505228e4c3d5194c1fcfaa15a456abdf37f9b9d97a4040afc073dee6c89064984f03385237d92167c13e236446b417ab79a0fcae412ae3316b77";

    @Test
    public void convert() throws Exception {
        byte[] addressData = HexUtils.parseHex(addressStr);
        LegacyAddress address = LegacyAddress.fromPubKeyHash(BitcoinNetwork.TESTNET, addressData);
        Logger.info("The formatted address:%s", address.stringFormat());
    }
}
