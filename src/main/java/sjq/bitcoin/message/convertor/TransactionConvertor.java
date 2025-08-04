package sjq.bitcoin.message.convertor;

import org.apache.commons.collections4.CollectionUtils;
import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.message.TransactionMessage;
import sjq.bitcoin.message.data.TransactionInput;
import sjq.bitcoin.message.data.TransactionOutput;
import sjq.bitcoin.service.data.TransactionData;
import sjq.bitcoin.service.data.TransactionInputData;
import sjq.bitcoin.service.data.TransactionOutputData;

import java.util.ArrayList;
import java.util.List;

public class TransactionConvertor {

    public static final Long DEFAULT_TRANSACTION_INDEX = 0l;

    public static TransactionData convertTransactionDataFromMessage(
            TransactionMessage transactionMessage) throws Exception {
        return convertTransactionDataFromMessage(transactionMessage, DEFAULT_TRANSACTION_INDEX);
    }

    public static TransactionData convertTransactionDataFromMessage(
            TransactionMessage transactionMessage, Long transactionIndex) throws Exception {
        TransactionData transactionData = new TransactionData();
        transactionData.setMessageVersion(transactionMessage.getMessageVersion());
        transactionData.setTransactionHash(transactionMessage.getTransactionHash());
        transactionData.setTransactionIndex(transactionIndex);
        transactionData.setTransactionLockTime(transactionMessage.getTransactionLockTime());

        List<TransactionInputData> transactionInputDataList = TransactionInputConvertor.
                convertTransactionInputDataList(transactionData, transactionMessage.getTransactionInputs());
        transactionData.setTransactionInputList(transactionInputDataList);

        List<TransactionOutputData> transactionOutputDataList = TransactionOutputConvertor.
                convertTransactionOutputDataList(transactionData, transactionMessage.getTransactionOutputs());
        transactionData.setTransactionOutputList(transactionOutputDataList);

        return transactionData;
    }

    public static List<TransactionData> convertTransactionDataFromMessage(List<TransactionMessage> messageList) throws Exception {
        List<TransactionData> transactionDataList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(messageList)) {
            Long transactionIndex = 0l;
            for (TransactionMessage message:messageList) {
                transactionDataList.add(convertTransactionDataFromMessage(message, transactionIndex));
                transactionIndex++;
            }
        }
        return transactionDataList;
    }

    public static TransactionMessage convertTransactionMessageFromData(TransactionData transactionData) {
        TransactionMessage transactionMessage = TransactionMessage.createTransactionMessage(Constants.VERSION_CURRENT);

        transactionMessage.setMessageVersion(transactionData.getMessageVersion());
        transactionMessage.setTransactionLockTime(transactionData.getTransactionLockTime());

        List<TransactionInput> transactionInputList = TransactionInputConvertor.
                convertTransactionInputFromDataList(transactionMessage, transactionData.getTransactionInputList());
        transactionMessage.setTransactionInputs(transactionInputList);

        List<TransactionOutput> transactionOutputList = TransactionOutputConvertor.
                convertTransactionOutputFromDataList(transactionMessage, transactionData.getTransactionOutputList());
        transactionMessage.setTransactionOutputs(transactionOutputList);

        return transactionMessage;
    }
}
