package sjq.bitcoin.script;

import org.junit.Assert;
import org.junit.Test;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.utility.HexUtils;

public class TestScriptProgram {

    private static final String PubKey = "76a91433e81a941e64cda12c6a299ed322ddbdd03f8d0e88ac";

    @Test
    public void parse() throws Exception {

        byte[] scriptPubKey = HexUtils.parseHex(PubKey);
        ScriptProgram program = ScriptProgram.parse(scriptPubKey);
        String actualScriptFormat = program.format();

        String expectedScriptFormat = "OP_DUP OP_HASH160 OP_PUSH(20) [33e81a941e64cda12c6a299ed322ddbdd03f8d0e] OP_EQUALVERIFY OP_CHECKSIG";
        Assert.assertEquals("script format not equal!", expectedScriptFormat, actualScriptFormat);
        Logger.info("The formatted script:%s", actualScriptFormat);
    }
}
