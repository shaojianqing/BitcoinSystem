package sjq.bitcoin.configuration;

import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.merkle.MerkleTree;
import sjq.bitcoin.merkle.TreeNode;
import sjq.bitcoin.message.BlockMessage;
import sjq.bitcoin.message.TransactionMessage;
import sjq.bitcoin.message.data.TransactionLockTime;
import sjq.bitcoin.message.data.TransactionOutput;
import sjq.bitcoin.monetary.Coin;
import sjq.bitcoin.script.BitcoinNetwork;
import sjq.bitcoin.script.ScriptConstant;
import sjq.bitcoin.script.ScriptProgram;
import sjq.bitcoin.utility.HexUtils;

import java.util.ArrayList;
import java.util.List;

public class MainnetConfiguration extends NetworkConfiguration {

    private static final int MAIN_NET_MAGIC_CODE = 0xf9beb4d9;

    private static final short MAIN_NET_NODE_PORT = 8333;

    private static final long GENESIS_BLOCK_VERSION = 1;

    private static final long GENESIS_MESSAGE_VERSION = 1;

    private static final long GENESIS_BLOCK_NONCE = 2083236893;

    private static final long GENESIS_BLOCK_TIMESTAMP = 1231006505;

    private static final long GENESIS_BLOCK_DIFFICULTY = 0x1d00ffffL;

    private static final long GENESIS_INITIAL_REWARD_COIN = 50;

    private static final Hash GENESIS_PREVIOUS_BLOCK_HASH = Hash.ZERO_HASH;

    private static final byte[] GENESIS_TRANSACTION_INPUT_SCRIPT = HexUtils.parseHex("04ffff001d0104455468652054696d65732030332f4a616e2f32303039204368616e63656c6c6f72206f6e206272696e6b206f66207365636f6e64206261696c6f757420666f722062616e6b73");

    private static final byte[] GENESIS_TRANSACTION_OUTPUT_SCRIPT = HexUtils.parseHex("04678afdb0fe5548271967f1a67130b7105cd6a828e03909a67962e0ea1f61deb649f6bc3f4cef38c4f35504e51ec112de5c384df7ba0b8d578a4c702b6bf11d5f");

    private static final List<String> DNS_SEED_LIST = new ArrayList<String>();

    public MainnetConfiguration() {
        initDnsSeedList();
    }

    private void initDnsSeedList() {
        DNS_SEED_LIST.add("dnsseed.emzy.de");
        DNS_SEED_LIST.add("seed.bitcoin.sipa.be");
        DNS_SEED_LIST.add("dnsseed.bitcoin.dashjr.org");
    }

    @Override
    public int getMagicCode() {
        return MAIN_NET_MAGIC_CODE;
    }

    @Override
    public short getPort() {
        return MAIN_NET_NODE_PORT;
    }

    @Override
    public List<String> getDnsSeedList() {
        return DNS_SEED_LIST;
    }

    @Override
    public long getGenesisBlockVersion() {
        return GENESIS_BLOCK_VERSION;
    }

    @Override
    public long getGenesisBlockNonce() {
        return GENESIS_BLOCK_NONCE;
    }

    @Override
    public long getGenesisBlockTimestamp() {
        return GENESIS_BLOCK_TIMESTAMP;
    }

    @Override
    public long getGenesisBlockDifficulty() {
        return GENESIS_BLOCK_DIFFICULTY;
    }

    @Override
    public BlockMessage getGenesisBlock() {
        BlockMessage blockMessage = new BlockMessage();
        try {
            List<TransactionMessage> transactions = getGenesisTransactions();
            MerkleTree rootTree = MerkleTree.build(transactions, false);
            TreeNode rootNode = rootTree.getRoot();
            blockMessage.setTransactions(transactions);
            blockMessage.setVersion(GENESIS_BLOCK_VERSION);
            blockMessage.setPrevBlockHash(GENESIS_PREVIOUS_BLOCK_HASH);
            blockMessage.setMerkleRoot(rootNode.getNodeHash());
            blockMessage.setTimestamp(GENESIS_BLOCK_TIMESTAMP);
            blockMessage.setDifficulty(GENESIS_BLOCK_DIFFICULTY);
            blockMessage.setNonce(GENESIS_BLOCK_NONCE);
            blockMessage.calculateBlockHash();
        } catch (Exception e) {
            Logger.fatal("prepare genesis block exception:%s", e);
            System.exit(-1);
        }
        return blockMessage;
    }

    @Override
    public BitcoinNetwork getBitcoinNetwork() {
        return BitcoinNetwork.MAINNET;
    }

    private List<TransactionMessage> getGenesisTransactions() {
        List<TransactionMessage> transactionList = new ArrayList<>();
        try {
            TransactionMessage transaction = TransactionMessage.coinbaseTransaction(GENESIS_TRANSACTION_INPUT_SCRIPT);
            transaction.setMessageVersion(GENESIS_MESSAGE_VERSION);
            TransactionOutput transactionOutput = new TransactionOutput();
            transactionOutput.setCoinValue(Coin.ONE.multiply(GENESIS_INITIAL_REWARD_COIN));

            ScriptProgram scriptProgram = ScriptProgram.build().
                    data(GENESIS_TRANSACTION_OUTPUT_SCRIPT).
                    opCode(ScriptConstant.OP_CHECKSIG);

            transactionOutput.setScriptPubKey(scriptProgram.program());
            transactionOutput.setParentTransaction(transaction);
            transaction.addTransactionOutput(transactionOutput);
            TransactionLockTime transactionLockTime = TransactionLockTime.of(0);
            transaction.setTransactionLockTime(transactionLockTime);
            transactionList.add(transaction);
        } catch (Exception e) {
            Logger.fatal("prepare genesis transaction exception:%s", e);
            System.exit(-1);
        }
        return transactionList;
    }
}
