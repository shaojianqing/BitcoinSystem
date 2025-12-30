package sjq.bitcoin.script.address;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.script.BitcoinNetwork;
import sjq.bitcoin.script.ScriptProgram;
import sjq.bitcoin.service.data.BitcoinAddress;
import sjq.bitcoin.utility.HexUtils;

public class TestBitcoinAddress {

    private static final String P2PKPubKey = "410496b538e853519c726a2c91e61ec11600ae1390813a627c66fb8be7947be63c52da7589379515d4e0a604f8141781e62294721166bf621e73a82cbf2342c858eeac";

    private static final String P2PKAddress = "12c6DSiU4Rq3P4ZxziKxzrL5LmMBrzjrJX";

    private static final String P2PKHPubKey = "76a914dba88f8584ded4e9e2f84b438b20eb6005d0de3f88ac";

    private static final String P2PKHAddress = "1M2Suouujzm6NFU38QA5CHVGeJCU9iVLDQ";

    @Test
    public void parseAddressP2PK() throws Exception {
        boolean success = verifyParseAddress(P2PKPubKey, P2PKAddress);
        Assert.assertTrue("P2PK address not equal!", success);
    }

    @Test
    public void parseAddressP2PKH() throws Exception {
        boolean success = verifyParseAddress(P2PKHPubKey, P2PKHAddress);
        Assert.assertTrue("P2PKH address not equal!", success);
    }

    @Test
    public void parseAddressP2SH() throws Exception {

    }

    @Test
    public void parseAddressP2WH() throws Exception {

    }

    @Test
    public void parseAddressP2TR() throws Exception {

    }

    private boolean verifyParseAddress(String pubKeyString, String expectedAddress) throws Exception {

        byte[] scriptPubKey = HexUtils.parseHex(pubKeyString);
        ScriptProgram program = ScriptProgram.build(scriptPubKey);

        BitcoinAddress address = program.getDestinationAddress(BitcoinNetwork.MAINNET);
        Logger.info("The bitcoin address:%s", address.getStringFormat());

        return StringUtils.equals(address.getStringFormat(), expectedAddress);
    }
}
