package sjq.bitcoin.crypto.transaction;

import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.message.TransactionMessage;
import sjq.bitcoin.message.convertor.TransactionConvertor;
import sjq.bitcoin.message.data.TransactionInput;
import sjq.bitcoin.message.data.TransactionOutput;
import sjq.bitcoin.monetary.Coin;
import sjq.bitcoin.script.ScriptConstant;
import sjq.bitcoin.service.data.TransactionData;
import sjq.bitcoin.utility.ByteUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * SignatureContext is used for generating signature hash value from transaction and relative script data. Since it is
 * necessary for script execution runtime to involve transaction context and the signature hash generating logic is
 * relatively complex, SignatureContext is designed to organize transaction signature context data here and provides
 * relative support for signature hash generating and public key verification process.
 **/
public class SignatureContext {

    private final TransactionData transaction;

    private final Integer scriptSignatureIndex;

    private SignatureContext(TransactionData transaction, Integer scriptSignatureIndex) {
        this.transaction = transaction;
        this.scriptSignatureIndex = scriptSignatureIndex;
    }

    public static SignatureContext build(TransactionData transaction, Integer scriptSignatureIndex) {
        return new SignatureContext(transaction, scriptSignatureIndex);
    }

    public Hash generateHashForSignature(SignatureHashType hashType,  byte[] connectScriptData) throws IOException {

        TransactionMessage transactionMessage = TransactionConvertor.convertTransactionMessageFromData(transaction);

        for(int i=0;i<transactionMessage.getTransactionInputs().size();++i) {
            TransactionInput transactionInput = transactionMessage.getTransactionInputs().get(i);

            transactionInput = transactionInput.cloneWithoutScript();
            transactionInput = transactionInput.cloneWithoutWitness();

            transactionMessage.replaceTransactionInput(transactionInput, i);
        }

        byte[] trimConnectScriptData = trimSeparatorCode(connectScriptData);

        TransactionInput signatureTransactionInput = transactionMessage.getTransactionInputs().get(scriptSignatureIndex);
        signatureTransactionInput = signatureTransactionInput.cloneWithScript(trimConnectScriptData);
        transactionMessage.replaceTransactionInput(signatureTransactionInput, scriptSignatureIndex);

        if ((hashType.value & 0x1F) == SignatureHashType.NONE.value) {
            transactionMessage.setTransactionOutputs(new ArrayList<>(0));

            for (int i=0;i<transactionMessage.getTransactionInputs().size();++i) {
                TransactionInput transactionInput = transactionMessage.getTransactionInputs().get(i);
                transactionInput = transactionInput.cloneWithSequence(0);
                transactionMessage.replaceTransactionInput(transactionInput, i);
            }
        } else if ((hashType.value & 0x1F) == SignatureHashType.SINGLE.value) {
            if (scriptSignatureIndex>=transactionMessage.getTransactionOutputs().size()) {
                return Hash.wrap("0100000000000000000000000000000000000000000000000000000000000000");
            }
            transactionMessage.setTransactionOutputs(
                    new ArrayList<>(transactionMessage.getTransactionOutputs().subList(0, scriptSignatureIndex + 1)));
            for (int i=0;i<transactionMessage.getTransactionOutputs().size();++i) {
                TransactionOutput transactionOutput = new TransactionOutput();
                transactionOutput.setParentTransaction(transactionMessage);
                transactionOutput.setCoinValue(Coin.NEGATIVE);
                transactionOutput.setScriptPubKey(new byte[]{});
                transactionMessage.getTransactionOutputs().set(i, transactionOutput);
            }

            for (int i=0;i<transactionMessage.getTransactionInputs().size();++i) {
                TransactionInput transactionInput = transactionMessage.getTransactionInputs().get(i);
                transactionInput = transactionInput.cloneWithSequence(0);
                transactionMessage.replaceTransactionInput(transactionInput, i);
            }
        }

        if ((hashType.value & SignatureHashType.ANYONECANPAY.value) == SignatureHashType.ANYONECANPAY.value) {
            transactionMessage.setTransactionInputs(new ArrayList<>());
            transactionMessage.getTransactionInputs().add(signatureTransactionInput);
        }

        byte[] transactionBytes = transactionMessage.serializeMessage();
        ByteBuffer buffer = ByteBuffer.allocate(transactionBytes.length + 4);
        buffer.put(transactionBytes);
        int signatureHashTypeValue = (hashType.value & 0x000000FF);
        ByteUtils.writeInt32LE(signatureHashTypeValue, buffer);

        byte[] hashBytes = Hash.calculateTwice(buffer.array());
        return Hash.wrap(hashBytes);
    }

    private byte[] trimSeparatorCode(byte[] connectScriptData) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream(connectScriptData.length);
        for (byte data : connectScriptData) {
            if (data != (byte) ScriptConstant.OP_CODESEPARATOR) {
                stream.write(data);
            }
        }
        return stream.toByteArray();
    }
}
