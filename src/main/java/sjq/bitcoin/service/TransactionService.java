package sjq.bitcoin.service;

import org.apache.commons.collections4.CollectionUtils;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.service.data.TransactionData;
import sjq.bitcoin.service.data.TransactionInputData;
import sjq.bitcoin.service.data.TransactionOutputData;
import sjq.bitcoin.service.data.TransactionWitnessData;
import sjq.bitcoin.storage.dao.*;
import sjq.bitcoin.storage.domain.*;

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

    public TransactionData getGenesisTransaction() {
        return null;
    }

    public Hash calculateMerkleRoot(List<TransactionData> transactions) {
        return null;
    }

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
            transactionList.forEach(transactionData->{
                Transaction transaction = buildTransaction(blockInDB, transactionData);
                transactionDao.saveTransaction(transaction);

                List<TransactionInputData> transactionInputDataList = transactionData.getTransactionInputList();
                if (CollectionUtils.isNotEmpty(transactionInputDataList)) {
                    transactionInputDataList.forEach(transactionInputData->{
                        TransactionInput transactionInput = buildTransactionInput(transactionData, transactionInputData);
                        transactionInputDao.saveTransactionInput(transactionInput);
                    });
                }

                List<TransactionOutputData> transactionOutputDataList = transactionData.getTransactionOutputList();
                if (CollectionUtils.isNotEmpty(transactionOutputDataList)) {
                    transactionOutputDataList.forEach(transactionOutputData->{
                        TransactionOutput transactionOutput = buildTransactionOutput(transactionData, transactionOutputData);
                        transactionOutputDao.saveTransactionOutput(transactionOutput);
                    });
                }

                TransactionWitnessData transactionWitnessData = transactionData.getTransactionWitness();
                if (transactionWitnessData != null) {
                    TransactionWitness transactionWitness = buildTransactionWitness(transactionData, transactionWitnessData);
                    transactionWitnessDao.saveTransactionWitness(transactionWitness);
                }
            });
        }

        return true;
    }

    private Transaction buildTransaction(Block block, TransactionData transactionData) {
        Transaction transaction = new Transaction();
        return transaction;
    }

    private TransactionInput buildTransactionInput(
            TransactionData transactionData, TransactionInputData transactionInputData) {
        TransactionInput transactionInput = new TransactionInput();
        return transactionInput;
    }

    private TransactionOutput buildTransactionOutput(
            TransactionData transactionData, TransactionOutputData transactionOutputData) {
        TransactionOutput transactionOutput = new TransactionOutput();
        return transactionOutput;
    }

    private TransactionWitness buildTransactionWitness(
            TransactionData transactionData, TransactionWitnessData transactionWitnessData) {
        TransactionWitness transactionWitness = new TransactionWitness();
        return transactionWitness;
    }
}
