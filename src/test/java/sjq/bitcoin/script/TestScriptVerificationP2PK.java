package sjq.bitcoin.script;

import org.junit.Assert;
import org.junit.Test;

public class TestScriptVerificationP2PK extends TestScriptVerificationBase  {

    private static final String rawTransactionP2PK = "";

    private static final String connectedScriptPubKeyP2PK = "";

    private static final String scriptPubKeySuccessP2PK = "";

    private static final String scriptSignatureSuccessP2PK = "";

    @Test
    public void verifySuccess() {
        boolean result = verifyScript(rawTransactionP2PK,
                connectedScriptPubKeyP2PK, scriptSignatureSuccessP2PK, scriptPubKeySuccessP2PK);
        Assert.assertTrue("script verification for P2PK with failure", result);
    }
}
