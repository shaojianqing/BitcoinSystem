package sjq.bitcoin.service.data;

import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.crypto.transaction.SignatureContext;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.script.ScriptProgram;

public class TransactionInputData {

    private TransactionData parentTransaction;

    private long sequence;

    private Hash fromTransactionHash;

    private long transactionOutputIndex;

    private byte[] scriptSignature;

    private ScriptProgram signatureProgram;

    private TransactionWitnessData transactionWitness;

    /**
     * coinbase transaction is made in case of new block mining. It is
     * designed by Bitcoin protocol as the only way for new bitcoin issuance.
     **/
    public static TransactionInputData buildCoinbaseTransactionInput(TransactionData parentTransaction, byte[] scriptSignature) {
        TransactionInputData transactionInput = new TransactionInputData();
        transactionInput.parentTransaction = parentTransaction;
        transactionInput.fromTransactionHash = Hash.ZERO_HASH;
        transactionInput.transactionOutputIndex = Constants.MAX_UNSIGNED_INTEGER;
        transactionInput.scriptSignature = scriptSignature;

        return transactionInput;
    }

    public boolean isCoinbaseTransactionInput() {
        if (Hash.ZERO_HASH.equals(fromTransactionHash) &&
                Constants.MAX_UNSIGNED_INTEGER.equals(transactionOutputIndex)) {
            return true;
        }
        return false;
    }

    public boolean verify(SignatureContext signatureContext, TransactionOutputData transactionOutput) {
        return ScriptProgram.verify(signatureContext, this.signatureProgram,
                transactionOutput.getPubKeyProgram(), ScriptProgram.ALL_VERIFY_FLAGS);
    }

    public TransactionData getParentTransaction() {
        return parentTransaction;
    }

    public void setParentTransaction(TransactionData parentTransaction) {
        this.parentTransaction = parentTransaction;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public Hash getFromTransactionHash() {
        return fromTransactionHash;
    }

    public void setFromTransactionHash(Hash fromTransactionHash) {
        this.fromTransactionHash = fromTransactionHash;
    }

    public long getTransactionOutputIndex() {
        return transactionOutputIndex;
    }

    public void setTransactionOutputIndex(long transactionOutputIndex) {
        this.transactionOutputIndex = transactionOutputIndex;
    }

    public byte[] getScriptSignature() {
        return scriptSignature;
    }

    public void setScriptSignature(byte[] scriptSignature) {
        this.scriptSignature = scriptSignature;
    }

    public ScriptProgram getSignatureProgram() {
        return signatureProgram;
    }

    public void setSignatureProgram(ScriptProgram signatureProgram) {
        this.signatureProgram = signatureProgram;
    }

    public TransactionWitnessData getTransactionWitness() {
        return transactionWitness;
    }

    public void setTransactionWitness(TransactionWitnessData transactionWitness) {
        this.transactionWitness = transactionWitness;
    }
}
