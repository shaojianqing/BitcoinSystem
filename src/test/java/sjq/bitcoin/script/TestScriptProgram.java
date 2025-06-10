package sjq.bitcoin.script;

import org.junit.Assert;
import org.junit.Test;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.utility.HexUtils;

public class TestScriptProgram {

    private static final String PubKey = "41047211a824f55b505228e4c3d5194c1fcfaa15a456abdf37f9b9d97a4040afc073dee6c89064984f03385237d92167c13e236446b417ab79a0fcae412ae3316b77ac";

    @Test
    public void parse() throws Exception {

        byte[] scriptPubKey = HexUtils.parseHex(PubKey);
        ScriptProgram program = ScriptProgram.parse(scriptPubKey);
        String actualScriptFormat = program.format();

        String expectedScriptFormat = "DUP HASH160 PUSH(20) [33e81a941e64cda12c6a299ed322ddbdd03f8d0e] EQUALVERIFY CHECKSIG";
        Assert.assertEquals("script format not equal!", expectedScriptFormat, actualScriptFormat);
        Logger.info("The formatted script:%s", actualScriptFormat);
    }
}
