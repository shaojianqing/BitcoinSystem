package sjq.bitcoin.service.data;

import java.util.ArrayList;
import java.util.List;

import sjq.bitcoin.hash.Hash;

public class TransactionData {

    private Hash transactionHash;

    private List<TransactionInputData> transactionInputList = new ArrayList<>();

    private List<TransactionOutputData> transactionOutputList = new ArrayList<>();

    private TransactionWitnessData transactionWitness;

    public static TransactionData buildCoinbaseTransaction(byte[] inputScript) {
        TransactionData transaction = new TransactionData();
        TransactionInputData transactionInput = TransactionInputData.buildCoinbaseTransactionInput(transaction, inputScript);
        transaction.addTransactionInput(transactionInput);

        return transaction;
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

    public Hash getTransactionHash() {
        return transactionHash;
    }

    public List<TransactionInputData> getTransactionInputList() {
        return transactionInputList;
    }

    public List<TransactionOutputData> getTransactionOutputList() {
        return transactionOutputList;
    }

    public TransactionWitnessData getTransactionWitness() {
        return transactionWitness;
    }
}
