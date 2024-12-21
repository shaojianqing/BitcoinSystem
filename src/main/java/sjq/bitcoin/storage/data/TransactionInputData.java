package sjq.bitcoin.storage.data;

import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.monetary.Coin;

public class TransactionInputData {

    private TransactionData parentTransaction;

    private long sequence;

    private Hash fromTransactionHash;

    private long transactionOutputIndex;

    private byte[] scriptData;

    private Coin value;

    /**
     * Coinbase transaction is made in case of new block mining. It is
     * made by Bitcoin protocol as the only way for new bitcoin release.
     **/
    public static TransactionInputData buildCoinbaseTransactionInput(TransactionData parentTransaction, byte[] scriptData) {
        TransactionInputData transactionInput = new TransactionInputData();
        transactionInput.parentTransaction = parentTransaction;
        transactionInput.fromTransactionHash = Hash.ZERO_HASH;
        transactionInput.transactionOutputIndex = Constants.MAX_UNSIGNED_INTEGER;
        transactionInput.scriptData = scriptData;

        return transactionInput;
    }

    public void verify(TransactionOutputData transactionOutput) {

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

    public byte[] getScriptData() {
        return scriptData;
    }

    public void setScriptData(byte[] scriptData) {
        this.scriptData = scriptData;
    }

    public Coin getValue() {
        return value;
    }

    public void setValue(Coin value) {
        this.value = value;
    }
}
