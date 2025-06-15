package sjq.bitcoin.script.address;

import org.junit.Assert;
import org.junit.Test;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.script.BitcoinNetwork;
import sjq.bitcoin.service.data.LegacyAddress;
import sjq.bitcoin.utility.HexUtils;

public class TestLegacyAddress {

    private static final String originRawAddressStr = "8acf7021339881af075cc749f0cdce9eeb6d373c";

    private static final String originBase58Address = "1DexkX3UBLjL4o4EcnMDmzzeUzFYBsDtGr";

    @Test
    public void convertToBase58Address() {
        byte[] addressData = HexUtils.parseHex(originRawAddressStr);
        LegacyAddress address = LegacyAddress.fromPubKeyHash(BitcoinNetwork.MAINNET, addressData);
        String actualScriptFormat = address.getStringFormat();
        Logger.info("The formatted address:%s", actualScriptFormat);

        Assert.assertEquals("script format not equal!", originBase58Address, actualScriptFormat);
    }

    @Test
    public void generateFromBase58Address() {
        LegacyAddress address = LegacyAddress.fromBase58Address(BitcoinNetwork.MAINNET, originBase58Address);
        byte[] rawAddress = address.getRawAddress();
        String rawAddressStr = HexUtils.formatHex(rawAddress);
        Logger.info("The raw address:%s", rawAddressStr);

        Assert.assertEquals("raw address not equal!", originRawAddressStr, rawAddressStr);
    }
}
