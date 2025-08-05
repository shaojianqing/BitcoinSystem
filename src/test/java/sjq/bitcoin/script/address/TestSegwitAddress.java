package sjq.bitcoin.script.address;

import org.junit.Assert;
import org.junit.Test;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.script.BitcoinNetwork;
import sjq.bitcoin.script.ScriptProgram;
import sjq.bitcoin.service.data.BitcoinAddress;
import sjq.bitcoin.utility.HexUtils;

public class TestSegwitAddress {

    private static final String originScriptPubKeyP2WH = "0014ecdcc61338eb642d612617bf78a41fcb1dacd508";

    private static final String expectedSegwitAddressP2WH = "bc1qanwvvyecadjz6cfxz7lh3fqlevw6e4gg7zkrwr";

    private static final String originScriptPubKeyP2TR = "5120132b90875a9d411dea2feadf96ffca4e98628dd458b91510dfc7a0983e9df889";

    private static final String expectedSegwitAddressP2TR = "bc1pzv4epp66n4q3m630at0edl72f6vx9rw5tzu32yxlc7sfs05alzysumsvym";

    @Test
    public void convertToBech32AddressP2WH() throws Exception {

        byte[] pukKeyBytes = HexUtils.parseHex(originScriptPubKeyP2WH);
        ScriptProgram program = ScriptProgram.build(pukKeyBytes);

        BitcoinAddress address = program.getDestAddress(BitcoinNetwork.MAINNET);
        Logger.info("The formatted address:%s", address.getStringFormat());

        Assert.assertEquals("The Seqwit Address is not expected!", expectedSegwitAddressP2WH, address.getStringFormat());
    }

    @Test
    public void convertToBech32AddressP2TR() throws Exception {

        byte[] pukKeyBytes = HexUtils.parseHex(originScriptPubKeyP2TR);
        ScriptProgram program = ScriptProgram.build(pukKeyBytes);

        BitcoinAddress address = program.getDestAddress(BitcoinNetwork.MAINNET);
        Logger.info("The formatted address:%s", address.getStringFormat());

        Assert.assertEquals("The Seqwit Address is not expected!", expectedSegwitAddressP2TR, address.getStringFormat());
    }
}
