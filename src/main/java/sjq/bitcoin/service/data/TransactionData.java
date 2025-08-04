package sjq.bitcoin.service.data;

import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.message.data.TransactionLockTime;

import java.util.ArrayList;
import java.util.List;

public class TransactionData {

    private long messageVersion;

    private Hash transactionHash;

    private long transactionIndex;

    private TransactionLockTime transactionLockTime;

    private List<TransactionInputData> transactionInputList = new ArrayList<>();

    private List<TransactionOutputData> transactionOutputList = new ArrayList<>();

    public static TransactionData buildCoinbaseTransaction(byte[] inputScript) {
        TransactionData transaction = new TransactionData();
        TransactionInputData transactionInput = TransactionInputData.
                buildCoinbaseTransactionInput(transaction, inputScript);
        transaction.addTransactionInput(transactionInput);

        return transaction;
    }

    public TransactionData clone() {
        return new TransactionData();
    }

    public TransactionInputData addTransactionInput(TransactionInputData transactionInput) {
        this.transactionInputList.add(transactionInput);
        transactionInput.setParentTransaction(this);
        return transactionInput;
    }

    public TransactionOutputData addTransactionOutput(TransactionOutputData transactionOutput) {
        this.transactionOutputList.add(transactionOutput);
        transactionOutput.setParentTransaction(this);
        return transactionOutput;
    }

    public long getMessageVersion() {
        return messageVersion;
    }

    public void setMessageVersion(long messageVersion) {
        this.messageVersion = messageVersion;
    }

    public Hash getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(Hash transactionHash) {
        this.transactionHash = transactionHash;
    }

    public long getTransactionIndex() {
        return transactionIndex;
    }

    public void setTransactionIndex(long transactionIndex) {
        this.transactionIndex = transactionIndex;
    }

    public TransactionLockTime getTransactionLockTime() {
        return transactionLockTime;
    }

    public void setTransactionLockTime(TransactionLockTime transactionLockTime) {
        this.transactionLockTime = transactionLockTime;
    }

    public List<TransactionInputData> getTransactionInputList() {
        return transactionInputList;
    }

    public void setTransactionInputList(List<TransactionInputData> transactionInputList) {
        this.transactionInputList = transactionInputList;
    }

    public List<TransactionOutputData> getTransactionOutputList() {
        return transactionOutputList;
    }

    public void setTransactionOutputList(List<TransactionOutputData> transactionOutputList) {
        this.transactionOutputList = transactionOutputList;
    }
}
