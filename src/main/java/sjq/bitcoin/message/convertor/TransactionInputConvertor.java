package sjq.bitcoin.message.convertor;

import org.apache.commons.collections4.CollectionUtils;
import sjq.bitcoin.crypto.transaction.SignatureContext;
import sjq.bitcoin.message.TransactionMessage;
import sjq.bitcoin.message.data.TransactionInput;
import sjq.bitcoin.message.data.TransactionWitness;
import sjq.bitcoin.script.ScriptProgram;
import sjq.bitcoin.service.data.TransactionData;
import sjq.bitcoin.service.data.TransactionInputData;
import sjq.bitcoin.service.data.TransactionWitnessData;

import java.util.ArrayList;
import java.util.List;

public class TransactionInputConvertor {

    public static TransactionInputData convertTransactionInputData(
            TransactionData transactionData, TransactionInput transactionInput) throws Exception {

        TransactionInputData transactionInputData = new TransactionInputData();
        transactionInputData.setParentTransaction(transactionData);
        transactionInputData.setSequence(transactionInput.getSequence());
        transactionInputData.setScriptSignature(transactionInput.getScriptSignature());
        transactionInputData.setSignatureProgram(ScriptProgram.build(transactionInput.getScriptSignature()));
        transactionInputData.setFromTransactionHash(transactionInput.getFromTransactionHash());
        transactionInputData.setTransactionOutputIndex(transactionInput.getTransactionOutputIndex());

        TransactionWitnessData transactionWitnessData = TransactionWitnessConvertor.
                convertTransactionWitnessData(transactionInput.getTransactionWitness());
        transactionInputData.setTransactionWitness(transactionWitnessData);

        return transactionInputData;
    }

    public static List<TransactionInputData> convertTransactionInputDataList(
            TransactionData transactionData, List<TransactionInput> transactionInputList) throws Exception {
        List<TransactionInputData> transactionInputDataList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(transactionInputList)) {
            for (TransactionInput transactionInput:transactionInputList) {
                transactionInputDataList.add(TransactionInputConvertor.
                        convertTransactionInputData(transactionData, transactionInput));
            }
        }
        return transactionInputDataList;
    }

    public static TransactionInput convertTransactionInputFromData(TransactionMessage parentTransactionMessage,
                                                                   TransactionInputData transactionInputData) {
        TransactionInput transactionInput = new TransactionInput();
        transactionInput.setParentTransaction(parentTransactionMessage);
        transactionInput.setFromTransactionHash(transactionInputData.getFromTransactionHash());
        transactionInput.setTransactionOutputIndex(transactionInputData.getTransactionOutputIndex());
        transactionInput.setSequence(transactionInputData.getSequence());
        transactionInput.setScriptSignature(transactionInputData.getScriptSignature());
        TransactionWitness transactionWitness = TransactionWitnessConvertor.
                convertTransactionWitnessFromData(transactionInputData.getTransactionWitness());
        transactionInput.setTransactionWitness(transactionWitness);

        return transactionInput;
    }

    public static List<TransactionInput> convertTransactionInputFromDataList(TransactionMessage parentTransactionMessage,
                                                                             List<TransactionInputData> transactionInputDataList) {
        List<TransactionInput> transactionInputList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(transactionInputDataList)) {
            for (TransactionInputData transactionInputData:transactionInputDataList) {
                transactionInputList.add(convertTransactionInputFromData(parentTransactionMessage, transactionInputData));
            }
        }
        return transactionInputList;
    }
}
