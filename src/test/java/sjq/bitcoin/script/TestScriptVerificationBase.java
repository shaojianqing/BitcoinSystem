package sjq.bitcoin.script;

import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.crypto.transaction.SignatureContext;
import sjq.bitcoin.message.TransactionMessage;
import sjq.bitcoin.message.convertor.TransactionConvertor;
import sjq.bitcoin.service.data.TransactionData;
import sjq.bitcoin.utility.HexUtils;

import java.nio.ByteBuffer;

public abstract class TestScriptVerificationBase {

    protected boolean verifyScript(String rawTransaction, String connectedScriptPubKey, String scriptSignature, String scriptPubKey) {
        try {
            byte[] rawTransactionBytes = HexUtils.parseHex(rawTransaction);
            ByteBuffer transactionBuffer = ByteBuffer.wrap(rawTransactionBytes);
            TransactionMessage transactionMessage = TransactionMessage.read(transactionBuffer, Constants.VERSION_CURRENT);
            TransactionData transactionData = TransactionConvertor.convertTransactionDataFromMessage(transactionMessage);

            byte[] connectedScriptPubKeyBytes = HexUtils.parseHex(connectedScriptPubKey);

            SignatureContext signatureContext = new SignatureContext(
                    transactionData, 0, connectedScriptPubKeyBytes);

            byte[] signatureBytes = HexUtils.parseHex(scriptSignature);
            ScriptProgram signatureProgram = ScriptProgram.build(signatureBytes);

            byte[] pubKeyBytes = HexUtils.parseHex(scriptPubKey);
            ScriptProgram pubKeyProgram = ScriptProgram.build(pubKeyBytes);

            return ScriptProgram.verify(signatureContext, signatureProgram,  pubKeyProgram, ScriptProgram.ALL_VERIFY_FLAGS);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
