package sjq.bitcoin.message.convertor;

import org.apache.commons.collections4.CollectionUtils;
import sjq.bitcoin.message.data.TransactionInput;
import sjq.bitcoin.service.data.TransactionData;
import sjq.bitcoin.service.data.TransactionInputData;
import sjq.bitcoin.service.data.TransactionWitnessData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionInputConvertor {

    public static TransactionInputData convertTransactionInputData(
            TransactionData transactionData, TransactionInput transactionInput) throws IOException {
        TransactionInputData transactionInputData = new TransactionInputData();
        transactionInputData.setParentTransaction(transactionData);
        transactionInputData.setSequence(transactionInput.getSequence());
        transactionInputData.setScriptData(transactionInput.getScriptSignature());
        transactionInputData.setFromTransactionHash(transactionInput.getFromTransactionHash());
        transactionInputData.setTransactionOutputIndex(transactionInput.getTransactionOutputIndex());

        TransactionWitnessData transactionWitnessData =
                TransactionWitnessConvertor.convertTransactionWitnessData(transactionInput.getTransactionWitness());
        transactionInputData.setTransactionWitness(transactionWitnessData);

        return transactionInputData;
    }

    public static List<TransactionInputData> convertTransactionInputDataList(
            TransactionData transactionData, List<TransactionInput> transactionInputList) throws IOException {
        List<TransactionInputData> transactionInputDataList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(transactionInputList)) {
            for (TransactionInput transactionInput:transactionInputList) {
                transactionInputDataList.
                        add(TransactionInputConvertor.convertTransactionInputData(transactionData, transactionInput));
            }
        }
        return transactionInputDataList;
    }
}
