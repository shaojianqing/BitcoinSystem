package sjq.bitcoin.service;

import org.apache.commons.collections4.CollectionUtils;
import sjq.bitcoin.configuration.NetworkConfiguration;
import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.hash.Hash;
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

import java.util.List;

public class TransactionService {

    private NetworkConfiguration configuration;

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
    private TransactionBlockDao transactionBlockDao;

    @Autowire
    private TransactionAddressDao transactionAddressDao;

    @Autowire
    private TransactionSpendDao transactionSpendDao;

    public TransactionService() {
        this.configuration = NetworkConfiguration.getConfiguration();
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
            for (TransactionData transactionData:transactionList) {
                Transaction transaction = buildTransaction(blockInDB, transactionData);
                transactionDao.saveTransaction(transaction);

                TransactionBlock transactionBlock = buildTransactionBlockMap(blockInDB, transactionData);
                transactionBlockDao.saveTransactionBlockMap(transactionBlock);

                List<TransactionInputData> transactionInputDataList = transactionData.getTransactionInputList();
                if (CollectionUtils.isNotEmpty(transactionInputDataList)) {
                    for (TransactionInputData transactionInputData:transactionInputDataList) {
                        TransactionInput transactionInput = buildTransactionInput(transactionData, transactionInputData);
                        transactionInputDao.saveTransactionInput(transactionInput);

                        TransactionWitness transactionWitness = buildTransactionWitness(transactionData, transactionInputData.getTransactionWitness());
                        transactionWitnessDao.saveTransactionWitness(transactionWitness);

                        TransactionSpend transactionSpend = buildTransactionSpend(transactionData, transactionInputData);
                        transactionSpendDao.saveTransactionSpend(transactionSpend);
                    }
                }

                List<TransactionOutputData> transactionOutputDataList = transactionData.getTransactionOutputList();
                if (CollectionUtils.isNotEmpty(transactionOutputDataList)) {
                    for (TransactionOutputData transactionOutputData:transactionOutputDataList) {
                        TransactionOutput transactionOutput = buildTransactionOutput(transactionData, transactionOutputData);
                        transactionOutputDao.saveTransactionOutput(transactionOutput);

                        TransactionAddress transactionAddress = buildTransactionAddress(transactionData, transactionOutputData);
                        transactionAddressDao.saveTransactionAddressMap(transactionAddress);
                    }
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

        return transaction;
    }

    private TransactionInput buildTransactionInput(TransactionData transactionData,
                                                   TransactionInputData transactionInputData) {
        TransactionInput transactionInput = new TransactionInput();
        transactionInput.setSequence(transactionInputData.getSequence());
        transactionInput.setTransactionHash(transactionData.getTransactionHash().hexValue());
        transactionInput.setFromTransactionHash(transactionInputData.getFromTransactionHash().hexValue());
        transactionInput.setTransactionOutputIndex(transactionInputData.getTransactionOutputIndex());
        transactionInput.setScriptSignature(HexUtils.formatHex(transactionInputData.getScriptData()));

        return transactionInput;
    }

    private TransactionOutput buildTransactionOutput(TransactionData transactionData,
                                                     TransactionOutputData transactionOutputData) {
        TransactionOutput transactionOutput = new TransactionOutput();

        transactionOutput.setTransactionHash(transactionData.getTransactionHash().hexValue());
        transactionOutput.setScriptPubKey(HexUtils.formatHex(transactionOutputData.getScriptPubKey()));

        Coin coinValue = transactionOutputData.getCoinValue();
        transactionOutput.setCoinValue(coinValue.getValue());

        return transactionOutput;
    }

    private TransactionWitness buildTransactionWitness(TransactionData transactionData,
                                                       TransactionWitnessData transactionWitnessData) {
        TransactionWitness transactionWitness = new TransactionWitness();
        return transactionWitness;
    }

    private TransactionBlock buildTransactionBlockMap(Block block, TransactionData transactionData) {
        TransactionBlock transactionBlock = new TransactionBlock();
        transactionBlock.setBlockHash(block.getBlockHash());
        transactionBlock.setBlockHeight(block.getBlockHeight());
        transactionBlock.setTransactionHash(transactionData.getTransactionHash().hexValue());
        transactionBlock.setTransactionIndex(transactionData.getTransactionIndex());

        return transactionBlock;
    }

    private TransactionSpend buildTransactionSpend(TransactionData transactionData, TransactionInputData transactionInputData) {
        Hash fromTransactionHash = transactionInputData.getFromTransactionHash();
        if (Hash.ZERO_HASH.equals(fromTransactionHash) &&
                Constants.MAX_UNSIGNED_INTEGER.equals(transactionInputData.getTransactionOutputIndex())) {
            // If it is coinbase transaction input, directly return
            // null instead of generating transaction spend record
            return null;
        } else {
            TransactionSpend transactionSpend = new TransactionSpend();
            transactionSpend.setTransactionHash(transactionData.getTransactionHash().hexValue());
            transactionSpend.setFromTransactionHash(transactionInputData.getFromTransactionHash().hexValue());
            transactionSpend.setTransactionOutputIndex(transactionInputData.getTransactionOutputIndex());
            return transactionSpend;
        }
    }

    private TransactionAddress buildTransactionAddress(TransactionData transactionData,
                                                          TransactionOutputData transactionOutputData) throws Exception {
        /*ScriptProgram scriptProgram = ScriptProgram.parse(transactionOutputData.getScriptPubKey());
        BitcoinNetwork network = configuration.getBitcoinNetwork();
        BitcoinAddress destAddress = scriptProgram.getDestAddress(network);*/

        TransactionAddress transactionAddress = new TransactionAddress();
        transactionAddress.setTransactionHash(transactionData.getTransactionHash().hexValue());
        transactionAddress.setTransactionOutputIndex(transactionOutputData.getTransactionOutputIndex());
        //transactionAddress.setAddress(destAddress.getStringFormat());
        //transactionAddress.setAddressType(destAddress.getScriptType().getName());
        transactionAddress.setCoinValue(transactionOutputData.getCoinValue().getValue());
        transactionAddress.setSpendStatus(Boolean.FALSE);
        return transactionAddress;
    }
}
