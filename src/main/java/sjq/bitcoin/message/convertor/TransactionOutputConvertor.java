package sjq.bitcoin.message.convertor;

import org.apache.commons.collections4.CollectionUtils;
import sjq.bitcoin.message.data.TransactionOutput;
import sjq.bitcoin.script.ScriptProgram;
import sjq.bitcoin.service.data.TransactionData;
import sjq.bitcoin.service.data.TransactionOutputData;

import java.util.ArrayList;
import java.util.List;

public class TransactionOutputConvertor {

    public static TransactionOutputData convertTransactionOutputData(
            TransactionData transactionData, TransactionOutput transactionOutput) throws Exception {
        TransactionOutputData transactionOutputData = new TransactionOutputData();
        transactionOutputData.setParentTransaction(transactionData);
        transactionOutputData.setCoinValue(transactionOutput.getCoinValue());
        transactionOutputData.setScriptPubKey(transactionOutput.getScriptPubKey());
        transactionOutputData.setTransactionOutputIndex(transactionOutput.getTransactionOutputIndex());
        transactionOutputData.setProgram(ScriptProgram.parse(transactionOutput.getScriptPubKey()));

        return transactionOutputData;
    }

    public static List<TransactionOutputData> convertTransactionOutputDataList(
            TransactionData transactionData, List<TransactionOutput> transactionOutputList) throws Exception {
        List<TransactionOutputData> transactionOutputDataList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(transactionOutputList)) {
            for (TransactionOutput transactionOutput:transactionOutputList) {
                transactionOutputDataList.add(convertTransactionOutputData(transactionData, transactionOutput));
            }
        }
        return transactionOutputDataList;
    }
}
