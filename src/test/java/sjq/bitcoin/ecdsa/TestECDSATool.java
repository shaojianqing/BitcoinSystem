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

    private static final String publicKeyString1 = "0308cbe7b5b7f3d366f07f01d6906d56b40871c7865b38f2e8a91f76df639fe640";

    private static final String publicKeyString2 = "027e8acffa97f47f0318524cc4cf16e25da5e0a755e43f6b9544b81916922b08af";

    private static final String scriptString = "5121022afc20bf379bc96a2f4e9e63ffceb8652b2b6a097f63fbee6ecec2a49a48010e2103a767c7221e9f15f870f1ad9311f5ab937d79fcaeee15bb2c722bca515581b4c052ae";

    private static final String originScriptHashString = "748284390f9e263a4b766a75d0633c50426eb875";

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
        byte[] publicKeyBytes = HexUtils.parseHex(publicKeyString2);
        ECDSAKey ecdsaKey = ECDSAKey.getInstanceFromPublicKey(publicKeyBytes);

        byte[] publicKeyHash = HashUtils.sha256ToHash160(ecdsaKey.getPublicKey());
        String publicKeyHashString = HexUtils.formatHex(publicKeyHash);

        Logger.info("public key hash:%s", publicKeyHashString);

        Assert.assertEquals("Public key hash is not equal!", publicKeyHashString, originPublicKeyHashString);
    }

    @Test
    public void verifyScriptHash() throws Exception {
        byte[] scriptBytes = HexUtils.parseHex(scriptString);
        byte[] scriptHash = HashUtils.sha256ToHash160(scriptBytes);
        String scriptHashString = HexUtils.formatHex(scriptHash);

        Logger.info("script hash:%s", scriptHashString);

        Assert.assertEquals("Script hash is not equal!", scriptHashString, originScriptHashString);
    }

    @Test
    public void verifyTransactionSignature() throws Exception {

        byte[] signatureBytes = HexUtils.parseHex(signature);
        TransactionSignature transactionSignature = TransactionSignature.decode(signatureBytes, true, true);

        byte[] signatureHashBytes = HexUtils.parseHex(hashForSignature);

        byte[] publicKeyBytes = HexUtils.parseHex(publicKeyString1);
        ECDSAKey ecdsaKey = ECDSAKey.getInstanceFromPublicKey(publicKeyBytes);

        boolean success = ECDSATool.verifySignature(signatureHashBytes, ecdsaKey.getPublicKey(), transactionSignature);

        Assert.assertTrue("Transaction signature verification failure!", success);
    }
}
