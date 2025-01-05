package sjq.bitcoin.message.convertor;

import sjq.bitcoin.message.TransactionMessage;
import sjq.bitcoin.service.data.TransactionData;

public class TransactionConvertor {

    public static TransactionData convertTransactionDataFromMessage(TransactionMessage transactionMessage) {
        TransactionData transactionData = new TransactionData();
        return transactionData;
    }
}
