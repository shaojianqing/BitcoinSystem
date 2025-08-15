package sjq.bitcoin.script;

import org.junit.Assert;
import org.junit.Test;

public class TestScriptVerificationP2PKH extends TestScriptVerificationBase  {

    private static final String rawTransactionP2PKH = "0100000001df7860d0136bb02e547536bb9dee3b3c0d18a41147ae4c6163b053" +
            "0b62c64305000000006b483045022025f1fab8db15f56c8e5f1ba2d4ffabf42f128173874a8f57242a1c307d4b4270022100c4b6fa" +
            "d07f6c9f0f7ad498e97a607529b8cbcb4eb4b580d185032561a42cb3b801210308cbe7b5b7f3d366f07f01d6906d56b40871c7865b" +
            "38f2e8a91f76df639fe640ffffffff02008c8647000000001976a91478bbad48f1de3d033a1e8cf175666a6d6a03629a88ac00a3e1" +
            "11000000001976a9147ad9bb038ed9871a76e63161b604adb87f8ee26b88ac00000000";

    private static final String connectedScriptPubKeyP2PKH = "76a914f4e171be7d53175dd523c786f8cfe890ef6ee97a88ac";

    private static final String scriptPubKeySuccessP2PKH = "76a914f4e171be7d53175dd523c786f8cfe890ef6ee97a88ac";

    private static final String scriptSignatureSuccessP2PKH = "483045022025f1fab8db15f56c8e5f1ba2d4ffabf42f128173874a8f57242a1c307d4b4270022100c4b6fad07f6c9f0f7ad498e97a607529b8cbcb4eb4b580d185032561a42cb3b801210308cbe7b5b7f3d366f07f01d6906d56b40871c7865b38f2e8a91f76df639fe640";

    @Test
    public void verifySuccess() {
        boolean result = verifyScript(rawTransactionP2PKH,
                connectedScriptPubKeyP2PKH, scriptSignatureSuccessP2PKH, scriptPubKeySuccessP2PKH);
        Assert.assertTrue("script verification for P2PKH with failure", result);
    }
}
