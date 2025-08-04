package sjq.bitcoin.service;

import org.apache.commons.collections4.CollectionUtils;
import sjq.bitcoin.configuration.NetworkConfiguration;
import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.crypto.transaction.SignatureContext;
import sjq.bitcoin.crypto.transaction.SignatureHashType;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.convertor.TransactionOutputConvertor;
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
    private TransactionBlockDao transactionBlockDao;

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
                if (!transactionDao.existTransaction(transaction)) {
                    boolean success = transactionDao.saveTransaction(transaction);
                    if (success) {
                        Logger.info("save transaction successfully with transaction hash:%s", transaction.getTransactionHash());
                    } else {
                        return false;
                    }
                }

                TransactionBlock transactionBlock = buildTransactionBlockMap(blockInDB, transactionData);
                if (!transactionBlockDao.existTransactionBlock(transactionBlock)) {
                    boolean success = transactionBlockDao.saveTransactionBlock(transactionBlock);
                    if (success) {
                        Logger.info("save transaction block successfully with block hash:%s, transaction hash:%s",
                                transactionBlock.getBlockHash(), transactionBlock.getTransactionHash());
                    } else {
                        return false;
                    }
                }

                List<TransactionInputData> transactionInputDataList = transactionData.getTransactionInputList();
                if (CollectionUtils.isNotEmpty(transactionInputDataList)) {
                    for (TransactionInputData transactionInputData:transactionInputDataList) {
                        TransactionInput transactionInput = buildTransactionInput(transactionData, transactionInputData);
                        if (!transactionInputDao.existTransactionInput(transactionInput)) {
                            boolean success = transactionInputDao.saveTransactionInput(transactionInput);
                            if (success) {
                                Logger.info("save transaction input successfully with transaction hash:%s, from transaction hash:%s",
                                       transactionInput.getTransactionHash(), transactionInput.getFromTransactionHash());
                            } else {
                                return false;
                            }
                        }

                        TransactionWitness transactionWitness = buildTransactionWitness(transactionData, transactionInputData.getTransactionWitness());
                        if (!transactionWitnessDao.exist(transactionWitness)) {
                            transactionWitnessDao.saveTransactionWitness(transactionWitness);
                        }

                        TransactionSpend transactionSpend = buildTransactionSpend(transactionData, transactionInputData);

                        if (transactionSpend != null) {
                            if (!transactionSpendDao.existTransactionSpend(transactionSpend)) {
                                boolean success = transactionSpendDao.saveTransactionSpend(transactionSpend);
                                if (success) {
                                    Logger.info("save transaction spend successfully with transaction hash:%s, from transaction hash:%s",
                                            transactionSpend.getTransactionHash(), transactionSpend.getFromTransactionHash());
                                } else {
                                    return false;
                                }
                            }
                        }
                    }
                }

                List<TransactionOutputData> transactionOutputDataList = transactionData.getTransactionOutputList();
                if (CollectionUtils.isNotEmpty(transactionOutputDataList)) {
                    for (TransactionOutputData transactionOutputData:transactionOutputDataList) {
                        TransactionOutput transactionOutput = buildTransactionOutput(transactionData, transactionOutputData);
                        if (!transactionOutputDao.existTransactionOutput(transactionOutput)) {
                            boolean success = transactionOutputDao.saveTransactionOutput(transactionOutput);
                            if (success) {
                                Logger.info("save transaction output successfully with transaction hash:%s, script pubKey:%s",
                                        transactionOutput.getTransactionHash(), transactionOutput.getScriptPubKey());
                            } else {
                                return false;
                            }
                        }

                        TransactionAddress transactionAddress = buildTransactionAddress(transactionData, transactionOutputData);
                        if (!transactionAddressDao.existTransactionAddress(transactionAddress)) {
                            boolean success = transactionAddressDao.saveTransactionAddress(transactionAddress);
                            if (success) {
                                Logger.info("save transaction address successfully with transaction hash:%s, address:%s",
                                        transactionAddress.getTransactionHash(), transactionAddress.getAddress());
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean verifyTransaction(TransactionData transactionData) throws Exception {
        List<TransactionInputData> transactionInputList = transactionData.getTransactionInputList();
        // if transaction does not contain any transaction inputs,
        // directly return true. Even if this case should not exist.
        if (CollectionUtils.isEmpty(transactionInputList)) {
            return true;
        }

        boolean verifyStatus = false;
        for (int index=0;index<transactionInputList.size();++index) {
            TransactionInputData transactionInputData = transactionInputList.get(index);
            // if this is coinbase transaction input, it does not need to be verified.
           if (transactionInputData.isCoinbaseTransactionInput()) {
               continue;
           }

           TransactionOutputData transactionOutputData =
                   queryTransactionOutputByTransactionInput(transactionData, transactionInputData);
            // if the corresponding transaction output can not be queried, maybe it is because the transaction message
            // has not been synced up locally, directly break and ignore this verification and wait for transaction
            // to be synced up
           if (transactionOutputData == null) {
               break;
           }

           byte[] connectScriptPubKey = transactionOutputData.getScriptPubKey();
           SignatureContext signatureContext = new SignatureContext(transactionData, index, connectScriptPubKey);

            verifyStatus = transactionInputData.verify(signatureContext, transactionOutputData);
           if (!verifyStatus) {
               transactionDao.updateTransactionVerifyStatus(transactionData.getTransactionHash().hexValue(),
                       Transaction.STATUS_UN_VERIFY, Transaction.STATUS_VERIFY_FAILURE);
               break;
           }
        }

        if (verifyStatus) {
            transactionDao.updateTransactionVerifyStatus(
                    transactionData.getTransactionHash().hexValue(),
                    Transaction.STATUS_UN_VERIFY, Transaction.STATUS_VERIFY_SUCCESS);
        }
        return verifyStatus;
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
        transactionInput.setScriptSignature(HexUtils.formatHex(transactionInputData.getScriptSignature()));

        return transactionInput;
    }

    private TransactionOutput buildTransactionOutput(TransactionData transactionData,
                                                     TransactionOutputData transactionOutputData) {
        TransactionOutput transactionOutput = new TransactionOutput();

        transactionOutput.setTransactionHash(transactionData.getTransactionHash().hexValue());
        transactionOutput.setTransactionOutputIndex(transactionOutputData.getTransactionOutputIndex());
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
        ScriptProgram scriptProgram = ScriptProgram.build(transactionOutputData.getScriptPubKey());
        BitcoinNetwork network = NetworkConfiguration.getConfiguration().getBitcoinNetwork();
        BitcoinAddress destAddress = scriptProgram.getDestAddress(network);

        TransactionAddress transactionAddress = new TransactionAddress();
        transactionAddress.setTransactionHash(transactionData.getTransactionHash().hexValue());
        transactionAddress.setTransactionOutputIndex(transactionOutputData.getTransactionOutputIndex());
        transactionAddress.setAddress(destAddress.getStringFormat());
        transactionAddress.setAddressType(destAddress.getScriptType().getName());
        transactionAddress.setCoinValue(transactionOutputData.getCoinValue().getValue());
        return transactionAddress;
    }

    private TransactionOutputData queryTransactionOutputByTransactionInput(
            TransactionData transactionData, TransactionInputData transactionInputData) throws Exception {
        String fromTransactionHash = transactionInputData.getFromTransactionHash().hexValue();
        Long transactionOutputIndex = transactionInputData.getTransactionOutputIndex();
        TransactionOutput transactionOutput = transactionOutputDao.
                queryTransactionOutputByTransactionInput(fromTransactionHash, transactionOutputIndex);
        if (transactionOutput != null) {
            byte[] scriptPubKeyBytes = HexUtils.parseHex(transactionOutput.getScriptPubKey());

            TransactionOutputData transactionOutputData = new TransactionOutputData();
            transactionOutputData.setParentTransaction(transactionData);
            transactionOutputData.setTransactionOutputIndex(transactionOutput.getTransactionOutputIndex());
            transactionOutputData.setScriptPubKey(scriptPubKeyBytes);
            transactionOutputData.setPubKeyProgram(ScriptProgram.build(scriptPubKeyBytes));
            transactionOutputData.setCoinValue(Coin.of(transactionOutput.getCoinValue()));

            return transactionOutputData;
        }
        return null;
    }
}
