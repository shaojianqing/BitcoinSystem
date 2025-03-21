package sjq.bitcoin.message.convertor;

import sjq.bitcoin.message.TransactionMessage;
import sjq.bitcoin.service.data.TransactionData;

import java.util.ArrayList;
import java.util.List;

public class TransactionConvertor {

    public static TransactionData convertTransactionDataFromMessage(TransactionMessage transactionMessage) {
        TransactionData transactionData = new TransactionData();
        return transactionData;
    }

    public static List<TransactionData> convertTransactionDataFromMessage(List<TransactionMessage> messageList) {
        List<TransactionData> transactionDataList = new ArrayList<>();
        return transactionDataList;
    }
}
