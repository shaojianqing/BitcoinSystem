package sjq.bitcoin.script;

import org.junit.Assert;
import org.junit.Test;

public class TestScriptVerificationP2SH extends TestScriptVerificationBase  {

    private static final String rawTransactionP2SH = "";

    private static final String connectedScriptPubKeyP2SH = "";

    private static final String scriptPubKeySuccessP2SH = "";

    private static final String scriptSignatureSuccessP2SH = "";

    @Test
    public void verifySuccess() {
        boolean result = verifyScript(rawTransactionP2SH,
                connectedScriptPubKeyP2SH, scriptSignatureSuccessP2SH, scriptPubKeySuccessP2SH);
        Assert.assertTrue("script verification for P2SH with failure", result);
    }
}
