package sjq.bitcoin.script;

import org.junit.Assert;
import org.junit.Test;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.service.data.BitcoinAddress;
import sjq.bitcoin.utility.HexUtils;

public class TestScriptProgram {

    private static final String P2PKPubKey = "410496b538e853519c726a2c91e61ec11600ae1390813a627c66fb8be7947be63c52da7589379515d4e0a604f8141781e62294721166bf621e73a82cbf2342c858eeac";

    private static final String P2PKHPubKey = "76a91433e81a941e64cda12c6a299ed322ddbdd03f8d0e88ac";

    @Test
    public void parseP2PK() throws Exception {
        byte[] scriptPubKey = HexUtils.parseHex(P2PKPubKey);
        ScriptProgram program = ScriptProgram.parse(scriptPubKey);
        String actualScriptFormat = program.format();

        Assert.assertTrue("the type is not expected P2PK", program.isP2PK());

        BitcoinAddress address = program.getDestAddress(BitcoinNetwork.MAINNET);
        Logger.info("bitcoin address:%s", address.getStringFormat());

        String expectedBitcoinAddress = "12c6DSiU4Rq3P4ZxziKxzrL5LmMBrzjrJX";
        Assert.assertEquals("P2PK address not equal!", expectedBitcoinAddress, address.getStringFormat());

        String expectedScriptFormat = "PUSH(65) [0496b538e853519c726a2c91e61ec11600ae1390813a627c66fb8be7947be63c52da7589379515d4e0a604f8141781e62294721166bf621e73a82cbf2342c858ee] CHECKSIG";
        Assert.assertEquals("script format not equal!", expectedScriptFormat, actualScriptFormat);
        Logger.info("The formatted script:%s", actualScriptFormat);
    }

    @Test
    public void parseP2PKH() throws Exception {

        byte[] scriptPubKey = HexUtils.parseHex(P2PKHPubKey);
        ScriptProgram program = ScriptProgram.parse(scriptPubKey);
        String actualScriptFormat = program.format();

        String expectedScriptFormat = "DUP HASH160 PUSH(20) [33e81a941e64cda12c6a299ed322ddbdd03f8d0e] EQUALVERIFY CHECKSIG";
        Assert.assertEquals("script format not equal!", expectedScriptFormat, actualScriptFormat);
        Logger.info("The formatted script:%s", actualScriptFormat);
    }
}
