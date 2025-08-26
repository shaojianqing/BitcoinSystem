package sjq.bitcoin.ecdsa;

import org.junit.Assert;
import org.junit.Test;
import sjq.bitcoin.crypto.ECDSAKey;
import sjq.bitcoin.crypto.ECDSASignature;
import sjq.bitcoin.crypto.ECDSATool;
import sjq.bitcoin.crypto.transaction.TransactionSignature;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.utility.HashUtils;
import sjq.bitcoin.utility.HexUtils;

public class TestECDSATool {

    private static final String content = "shaojianqing";

    private static final String privateKeyString = "915eefcd223e4eca132aa0f85b8db8c3c1fb36eaab523c202fa3ba4eef52bc4b";

    private static final String originPublicKeyHashString = "ff9fb84dc0462e501a7c7a3731ecb69f89c9cdbe";

    private static final String signature = "3045022025f1fab8db15f56c8e5f1ba2d4ffabf42f128173874a8f57242a1c307d4b427" +
            "0022100c4b6fad07f6c9f0f7ad498e97a607529b8cbcb4eb4b580d185032561a42cb3b801";

    private static final String hashForSignature = "04e3868f5dd0d71fbe7b2df55c3378caa36141fb27855d6cda124932474be3fc";

    private static final String publicKeyString = "0308cbe7b5b7f3d366f07f01d6906d56b40871c7865b38f2e8a91f76df639fe640";

    @Test
    public void verifyECDSASignature() {

        byte[] hash = HexUtils.parseHex(privateKeyString);

        ECDSAKey ecdsaKey = ECDSAKey.getInstanceFromPrivateKey(hash);

        Logger.info("ECDSA private Key:%s", HexUtils.formatHex(ecdsaKey.getPrivateKey()));
        Logger.info("ECDSA public Key:%s", HexUtils.formatHex(ecdsaKey.getPublicKey()));

        ECDSASignature signature = ecdsaKey.calculateSignature(content.getBytes());
        boolean success = ECDSATool.verifySignature(content.getBytes(), ecdsaKey.getPublicKey(), signature);
        Assert.assertTrue("ECDSA signature verification failure!", success);
    }

    @Test
    public void verifyPublicKeyHash() throws Exception {
        byte[] publicKeyBytes = HexUtils.parseHex("027e8acffa97f47f0318524cc4cf16e25da5e0a755e43f6b9544b81916922b08af");
        ECDSAKey ecdsaKey = ECDSAKey.getInstanceFromPublicKey(publicKeyBytes);

        byte[] publicKeyHash = HashUtils.sha256ToHash160(ecdsaKey.getPublicKey());
        String publicKeyHashString = HexUtils.formatHex(publicKeyHash);

        Logger.info("public key hash:%s", publicKeyHashString);

        Assert.assertEquals("Public key hash is not equal!", publicKeyHashString, originPublicKeyHashString);
    }

    @Test
    public void verifyTransactionSignature() throws Exception {

        byte[] signatureBytes = HexUtils.parseHex(signature);
        TransactionSignature transactionSignature = TransactionSignature.decode(signatureBytes, true, true);

        byte[] signatureHashBytes = HexUtils.parseHex(hashForSignature);

        byte[] publicKeyBytes = HexUtils.parseHex(publicKeyString);
        ECDSAKey ecdsaKey = ECDSAKey.getInstanceFromPublicKey(publicKeyBytes);

        boolean success = ECDSATool.verifySignature(signatureHashBytes, ecdsaKey.getPublicKey(), transactionSignature);

        Assert.assertTrue("Transaction signature verification failure!", success);
    }
}
