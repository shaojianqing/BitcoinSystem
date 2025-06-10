package sjq.bitcoin.service;

import org.apache.commons.collections4.CollectionUtils;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.data.TransactionLockTime;
import sjq.bitcoin.monetary.Coin;
import sjq.bitcoin.script.BitcoinNetwork;
import sjq.bitcoin.script.ScriptProgram;
import sjq.bitcoin.service.data.*;
import sjq.bitcoin.storage.dao.*;
import sjq.bitcoin.storage.domain.*;
import sjq.bitcoin.utility.HexUtils;

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
    private TransactionBlockDao transactionBlockMapDao;

    @Autowire
    private TransactionAddressDao transactionAddressDao;

    @Autowire
    private TransactionSpendDao transactionSpendDao;

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
                transactionBlockMapDao.saveTransactionBlockMap(transactionBlock);

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

                        TransactionAddress transactionAddress = buildTransactionAddressMap(transactionData, transactionOutputData);
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
        TransactionSpend transactionSpend = new TransactionSpend();
        transactionSpend.setTransactionHash(transactionData.getTransactionHash().hexValue());
        transactionSpend.setFromTransactionHash(transactionInputData.getFromTransactionHash().hexValue());
        transactionSpend.setTransactionOutputIndex(transactionInputData.getTransactionOutputIndex());

        return transactionSpend;
    }

    private TransactionAddress buildTransactionAddressMap(TransactionData transactionData,
                                                          TransactionOutputData transactionOutputData) throws Exception {
        TransactionAddress transactionAddress = new TransactionAddress();
        transactionAddress.setTransactionHash(transactionData.getTransactionHash().hexValue());
        transactionAddress.setTransactionIndex(transactionData.getTransactionIndex());
        transactionAddress.setAddress(extractCoinAddress(transactionOutputData.getScriptPubKey()));
        transactionAddress.setCoinValue(transactionOutputData.getCoinValue().getValue());
        return transactionAddress;
    }

    private String extractCoinAddress(byte[] scriptPubKey) throws Exception {
        ScriptProgram scriptProgram = ScriptProgram.parse(scriptPubKey);
        if (scriptProgram.isP2PKH()) {
            byte[] addressBytes = scriptProgram.extractHashFromP2PKH();
            LegacyAddress legacyAddress = LegacyAddress.fromPubKeyHash(BitcoinNetwork.MAINNET, addressBytes);
            return legacyAddress.stringFormat();
        } else if (scriptProgram.isP2SH()) {
            byte[] addressBytes = scriptProgram.extractHashFromP2SH();
            LegacyAddress legacyAddress = LegacyAddress.fromScriptHash(BitcoinNetwork.MAINNET, addressBytes);
            return legacyAddress.stringFormat();
        } else if (scriptProgram.isP2PK()) {

        }
        return null;
    }
}
