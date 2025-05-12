package sjq.bitcoin.service;

import org.apache.commons.collections4.CollectionUtils;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.data.TransactionLockTime;
import sjq.bitcoin.monetary.Coin;
import sjq.bitcoin.service.data.TransactionData;
import sjq.bitcoin.service.data.TransactionInputData;
import sjq.bitcoin.service.data.TransactionOutputData;
import sjq.bitcoin.service.data.TransactionWitnessData;
import sjq.bitcoin.storage.dao.*;
import sjq.bitcoin.storage.domain.*;
import sjq.bitcoin.utility.HexUtils;

import java.util.Date;
import java.util.List;

public class TransactionService {

    @Autowire
    private BlockDao blockDao;

    @Autowire
    private TransactionDao transactionDao;

    @Autowire
    private TransactionInputDao transactionInputDao;

    @Autowire
    private TransactionOutputDao transactionOutputDao;

    @Autowire
    private TransactionWitnessDao transactionWitnessDao;

    @Autowire
    private TransactionBlockMapDao transactionBlockMapDao;

    public boolean acceptTransaction(TransactionData transaction) {
        return false;
    }

    public boolean batchSaveTransactionData(Block block, List<TransactionData> transactionList) throws Exception {
        Block blockInDB = blockDao.getBlockByHash(block.getBlockHash());
        if (blockInDB==null) {
            Logger.error("block does not exist in the database with block hash:%s", block.getBlockHash());
            return false;
        }

        if (CollectionUtils.isNotEmpty(transactionList)) {
            for (TransactionData transactionData:transactionList) {
                Transaction transaction = buildTransaction(blockInDB, transactionData);
                transactionDao.saveTransaction(transaction);

                TransactionBlockMap transactionBlockMap = buildTransactionBlockMap(blockInDB, transactionData);
                transactionBlockMapDao.saveTransactionBlockMap(transactionBlockMap);

                List<TransactionInputData> transactionInputDataList = transactionData.getTransactionInputList();
                if (CollectionUtils.isNotEmpty(transactionInputDataList)) {
                    transactionInputDataList.forEach(transactionInputData->{
                        TransactionInput transactionInput = buildTransactionInput(transactionData, transactionInputData);
                        transactionInputDao.saveTransactionInput(transactionInput);

                        TransactionWitness transactionWitness = buildTransactionWitness(transactionData, transactionInputData.getTransactionWitness());
                        transactionWitnessDao.saveTransactionWitness(transactionWitness);
                    });
                }

                List<TransactionOutputData> transactionOutputDataList = transactionData.getTransactionOutputList();
                if (CollectionUtils.isNotEmpty(transactionOutputDataList)) {
                    transactionOutputDataList.forEach(transactionOutputData->{
                        TransactionOutput transactionOutput = buildTransactionOutput(transactionData, transactionOutputData);
                        transactionOutputDao.saveTransactionOutput(transactionOutput);
                    });
                }
            }
        }
        return true;
    }

    private Transaction buildTransaction(Block block, TransactionData transactionData) {
        Transaction transaction = new Transaction();
        transaction.setBlockHash(block.getBlockHash());
        transaction.setBlockHeight(block.getBlockHeight());
        transaction.setMessageVersion(transactionData.getMessageVersion());
        String transactionHash = transactionData.getTransactionHash().hexValue();
        transaction.setTransactionHash(transactionHash);
        TransactionLockTime transactionLockTime = transactionData.getTransactionLockTime();
        transaction.setTransactionLockTime(transactionLockTime.rawValue());

        Date currentDateTime = new Date();
        transaction.setCreateTime(currentDateTime);
        transaction.setModifyTime(currentDateTime);

        return transaction;
    }

    private TransactionInput buildTransactionInput(
            TransactionData transactionData, TransactionInputData transactionInputData) {
        TransactionInput transactionInput = new TransactionInput();
        transactionInput.setSequence(transactionInputData.getSequence());
        transactionInput.setTransactionHash(transactionData.getTransactionHash().hexValue());
        transactionInput.setFromTransactionHash(transactionInputData.getFromTransactionHash().hexValue());
        transactionInput.setTransactionOutputIndex(transactionInputData.getTransactionOutputIndex());
        transactionInput.setScriptSignature(HexUtils.formatHex(transactionInputData.getScriptData()));

        Coin coinValue = transactionInputData.getValue();
        transactionInput.setValue(coinValue.getValue());

        Date currentDateTime = new Date();
        transactionInput.setCreateTime(currentDateTime);
        transactionInput.setModifyTime(currentDateTime);

        return transactionInput;
    }

    private TransactionOutput buildTransactionOutput(
            TransactionData transactionData, TransactionOutputData transactionOutputData) {
        TransactionOutput transactionOutput = new TransactionOutput();

        transactionOutput.setTransactionHash(transactionData.getTransactionHash().hexValue());
        transactionOutput.setScriptPubKey(HexUtils.formatHex(transactionOutputData.getScriptPubKey()));

        Coin coinValue = transactionOutputData.getValue();
        transactionOutput.setValue(coinValue.getValue());

        Date currentDateTime = new Date();
        transactionOutput.setCreateTime(currentDateTime);
        transactionOutput.setModifyTime(currentDateTime);

        return transactionOutput;
    }

    private TransactionWitness buildTransactionWitness(
            TransactionData transactionData, TransactionWitnessData transactionWitnessData) {
        TransactionWitness transactionWitness = new TransactionWitness();
        return transactionWitness;
    }

    private TransactionBlockMap buildTransactionBlockMap(Block block, TransactionData transactionData) {
        TransactionBlockMap transactionBlockMap = new TransactionBlockMap();
        transactionBlockMap.setBlockHash(block.getBlockHash());
        transactionBlockMap.setBlockHeight(block.getBlockHeight());
        transactionBlockMap.setTransactionHash(transactionData.getTransactionHash().hexValue());
        transactionBlockMap.setTransactionIndex(transactionData.getTransactionIndex());

        Date currentDateTime = new Date();
        transactionBlockMap.setCreateTime(currentDateTime);
        transactionBlockMap.setModifyTime(currentDateTime);

        return transactionBlockMap;
    }
}
