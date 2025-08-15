package sjq.bitcoin.script;

import org.junit.Assert;
import org.junit.Test;

public class TestScriptVerificationP2TR extends TestScriptVerificationBase  {

    private static final String rawTransactionP2TR = "";

    private static final String connectedScriptPubKeyP2TR = "";

    private static final String scriptPubKeySuccessP2TR = "";

    private static final String scriptSignatureSuccessP2TR = "";

    @Test
    public void verifySuccess() {
        boolean result = verifyScript(rawTransactionP2TR,
                connectedScriptPubKeyP2TR, scriptSignatureSuccessP2TR, scriptPubKeySuccessP2TR);
        Assert.assertTrue("script verification for P2TR with failure", result);
    }
}
