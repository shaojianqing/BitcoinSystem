package sjq.bitcoin.script;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.utility.HexUtils;

public class TestScriptProgram {

    private static final String P2PKPubKeyString = "410496b538e853519c726a2c91e61ec11600ae1390813a627c66fb8be7947be63c52da7589379515d4e0a604f8141781e62294721166bf621e73a82cbf2342c858eeac";

    private static final String P2PKPubKeyFormat = "PUSH(65) [0496b538e853519c726a2c91e61ec11600ae1390813a627c66fb8be7947be63c52da7589379515d4e0a604f8141781e62294721166bf621e73a82cbf2342c858ee] CHECKSIG";

    private static final String P2PKHPubKeyString = "76a914dba88f8584ded4e9e2f84b438b20eb6005d0de3f88ac";

    private static final String P2PKHPubKeyFormat = "DUP HASH160 PUSH(20) [dba88f8584ded4e9e2f84b438b20eb6005d0de3f] EQUALVERIFY CHECKSIG";

    private static final String P2PKSignatureString = "";

    private static final String P2PKSignatureFormat = "";

    private static final String P2PKHSignatureString = "47304402205dc908090ba4aa5ba1e527f6811177a7c3c5a5562453159d8673fd8def924eec022035327b01c1e7908a1dad21f60dde4c9762a7f40a257a2870ed5b0af7845745070121027e8acffa97f47f0318524cc4cf16e25da5e0a755e43f6b9544b81916922b08af";

    private static final String P2PKHSignatureFormat = "PUSH(71) [304402205dc908090ba4aa5ba1e527f6811177a7c3c5a5562453159d8673fd8def924eec022035327b01c1e7908a1dad21f60dde4c9762a7f40a257a2870ed5b0af78457450701] PUSH(33) [027e8acffa97f47f0318524cc4cf16e25da5e0a755e43f6b9544b81916922b08af]";

    private static final String P2SHScriptString = "5121022afc20bf379bc96a2f4e9e63ffceb8652b2b6a097f63fbee6ecec2a49a48010e2103a767c7221e9f15f870f1ad9311f5ab937d79fcaeee15bb2c722bca515581b4c052ae";

    private static final String P2SHScriptFormat = "1 PUSH(33) [022afc20bf379bc96a2f4e9e63ffceb8652b2b6a097f63fbee6ecec2a49a48010e] PUSH(33) [03a767c7221e9f15f870f1ad9311f5ab937d79fcaeee15bb2c722bca515581b4c0] 2 CHECKMULTISIG";

    @Test
    public void parseScriptPubKeyP2PK() throws Exception {
        boolean success = verifyParseScript(P2PKPubKeyString, P2PKPubKeyFormat);
        Assert.assertTrue("P2PK script pubKey not equal!", success);
    }

    @Test
    public void parseScriptSignatureP2PK() throws Exception {
        boolean success = verifyParseScript(P2PKSignatureString, P2PKSignatureFormat);
        Assert.assertTrue("P2PK script signature not equal!", success);
    }

    @Test
    public void parseScriptPubKeyP2PKH() throws Exception {
        boolean success = verifyParseScript(P2PKHPubKeyString, P2PKHPubKeyFormat);
        Assert.assertTrue("P2PKH script pubKey not equal!", success);
    }

    @Test
    public void parseScriptSignatureP2PKH() throws Exception {
        boolean success = verifyParseScript(P2PKHSignatureString, P2PKHSignatureFormat);
        Assert.assertTrue("P2PKH script signature not equal!", success);
    }

    @Test
    public void parseScriptDataP2SH() throws Exception {
        boolean success = verifyParseScript(P2SHScriptString, P2SHScriptFormat);
        Assert.assertTrue("P2SH script data not equal!", success);
    }

    @Test
    public void parseScriptPubKeyP2SH() throws Exception {

    }

    @Test
    public void parseScriptSignatureP2SH() throws Exception {

    }

    @Test
    public void parseScriptPubKeyP2WH() throws Exception {

    }

    @Test
    public void parseScriptSignatureP2WH() throws Exception {

    }

    @Test
    public void parseScriptPubKeyP2TR() throws Exception {

    }

    @Test
    public void parseScriptSignatureP2TR() throws Exception {

    }

    private boolean verifyParseScript(String scriptString, String pubKeyFormat) throws Exception {
        byte[] scriptBytes = HexUtils.parseHex(scriptString);
        ScriptProgram program = ScriptProgram.build(scriptBytes);
        String actualScriptFormat = program.format();

        Logger.info("The formatted script:%s", actualScriptFormat);

        return StringUtils.equals(actualScriptFormat, pubKeyFormat);
    }
}
