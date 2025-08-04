package sjq.bitcoin.message.convertor;

import sjq.bitcoin.message.data.TransactionWitness;
import sjq.bitcoin.service.data.TransactionWitnessData;

public class TransactionWitnessConvertor {

    public static TransactionWitnessData convertTransactionWitnessData(TransactionWitness transactionWitness) {
        TransactionWitnessData transactionWitnessData = new TransactionWitnessData();
        return transactionWitnessData;
    }

    public static TransactionWitness convertTransactionWitnessFromData(TransactionWitnessData transactionWitnessData) {
        return new TransactionWitness();
    }
}
