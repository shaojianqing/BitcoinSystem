package sjq.bitcoin.script.signature;

import org.junit.Assert;
import org.junit.Test;
import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.crypto.transaction.SignatureContext;
import sjq.bitcoin.crypto.transaction.SignatureHashType;
import sjq.bitcoin.crypto.transaction.TransactionSignature;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.message.TransactionMessage;
import sjq.bitcoin.message.convertor.TransactionConvertor;
import sjq.bitcoin.service.data.TransactionData;
import sjq.bitcoin.utility.HexUtils;

import java.nio.ByteBuffer;

public class TestSignatureContext {

    private static final String rawTransactionData = "0100000001df7860d0136bb02e547536bb9dee3b3c0d18a41147ae4c6163b0530b62c64305000000006b483045022025f1fab8db15f56c8e5f1ba2d4ffabf42f128173874a8f57242a1c307d4b4270022100c4b6fad07f6c9f0f7ad498e97a607529b8cbcb4eb4b580d185032561a42cb3b801210308cbe7b5b7f3d366f07f01d6906d56b40871c7865b38f2e8a91f76df639fe640ffffffff02008c8647000000001976a91478bbad48f1de3d033a1e8cf175666a6d6a03629a88ac00a3e111000000001976a9147ad9bb038ed9871a76e63161b604adb87f8ee26b88ac00000000";

    private static final String transactionSignature = "3045022025f1fab8db15f56c8e5f1ba2d4ffabf42f128173874a8f57242a1c307d4b4270022100c4b6fad07f6c9f0f7ad498e97a607529b8cbcb4eb4b580d185032561a42cb3b801";

    private static final String connectedScriptPubKey = "76a914f4e171be7d53175dd523c786f8cfe890ef6ee97a88ac";

    private static final String expectedSignatureHash = "04e3868f5dd0d71fbe7b2df55c3378caa36141fb27855d6cda124932474be3fc";

    @Test
    public void generateHashForSignature() throws Exception {

        byte[] rawTransaction = HexUtils.parseHex(rawTransactionData);
        ByteBuffer transactionBuffer = ByteBuffer.wrap(rawTransaction);
        TransactionMessage transactionMessage = TransactionMessage.read(transactionBuffer, Constants.VERSION_CURRENT);
        TransactionData transactionData = TransactionConvertor.convertTransactionDataFromMessage(transactionMessage);

        byte[] signatureBytes = HexUtils.parseHex(transactionSignature);
        TransactionSignature transactionSignature = TransactionSignature.decode(signatureBytes, true, true);
        SignatureHashType signatureHashType = transactionSignature.getType();

        byte[] connectedScriptPubKeyBytes = HexUtils.parseHex(connectedScriptPubKey);

        SignatureContext signatureContext = SignatureContext.build(transactionData, 0);

        Hash signatureHash = signatureContext.generateHashForSignature(signatureHashType, connectedScriptPubKeyBytes);
        String signatureHashString = signatureHash.hexValue();

        Assert.assertEquals("signature hash is not correct!", expectedSignatureHash, signatureHashString);
    }
}
