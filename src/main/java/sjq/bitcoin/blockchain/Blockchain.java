package sjq.bitcoin.blockchain;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.core.task.BlockSyncTask;
import sjq.bitcoin.core.task.TransactionSyncTask;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.merkle.MerkleTree;
import sjq.bitcoin.message.BlockMessage;
import sjq.bitcoin.message.TransactionMessage;
import sjq.bitcoin.message.convertor.BlockConvertor;
import sjq.bitcoin.message.convertor.TransactionConvertor;
import sjq.bitcoin.message.data.BlockHeader;
import sjq.bitcoin.network.PeerManager;
import sjq.bitcoin.service.BlockService;
import sjq.bitcoin.service.TransactionService;
import sjq.bitcoin.service.data.TransactionData;
import sjq.bitcoin.storage.domain.Block;

import java.io.IOException;
import java.util.List;
import java.util.Timer;

public class Blockchain {

    private static final long SYNC_TASK_DELAY = 20000;

    private static final long SYNC_TASK_PERIOD = 20000;

    private static final String BLOCK_SYNC_TIMER = "BlockSyncTimer";

    private static final String TRANSACTION_SYNC_TIMER = "TransactionSyncTimer";

    private final Timer blockSyncTaskTimer;

    private final Timer transactionSyncTaskTimer;

    @Autowire
    private PeerManager peerManager;

    @Autowire
    private BlockService blockService;

    @Autowire
    private TransactionService transactionService;

    @Autowire
    private BlockSyncTask blockSyncTask;

    @Autowire
    private TransactionSyncTask transactionSyncTask;

    public Blockchain() {
        blockSyncTaskTimer = new Timer(BLOCK_SYNC_TIMER);
        transactionSyncTaskTimer = new Timer(TRANSACTION_SYNC_TIMER);
    }

    public void start() {
        blockSyncTaskTimer.schedule(blockSyncTask, SYNC_TASK_DELAY, SYNC_TASK_PERIOD);
        transactionSyncTaskTimer.schedule(transactionSyncTask, SYNC_TASK_DELAY, SYNC_TASK_PERIOD);
    }

    public Long getBestBlockHeight() throws Exception {
        Block bestBlock = blockService.getBestBlock();
        return bestBlock.getBlockHeight();
    }

    public Block getBestBlock() throws Exception {
        return blockService.getBestBlock();
    }

    public List<Block> queryBlockWithHeaderSynced() throws Exception {
        return blockService.queryBlockWithHeaderSynced();
    }

    public void processTransaction(TransactionMessage transactionMessage) {
        try {
            TransactionData transactionData = TransactionConvertor.convertTransactionDataFromMessage(transactionMessage);
            boolean success = transactionService.acceptTransaction(transactionData);
            if (success) {
                Logger.info("verify and persist transaction successfully, transaction hash:%s", transactionData.getTransactionHash());
            } else {
                Logger.error("verify and persist transaction failure, transaction hash:%s", transactionData.getTransactionHash());
            }
        } catch (Exception e) {
            Logger.error("process transaction message with exception");
        }
    }

    /**
     * In header first strategy, the block data is persisted into database with block header directly, and
     * the corresponding status is set to be initial value, so that the block data could be completed later.
     *
     * @param headers block header data by getHeaders message from another remote peer node.
     **/
    public void persistBlockHeader(List<BlockHeader> headers) {
        try {
            for (BlockHeader header : headers) {
                Block block = BlockConvertor.convertBlockFromHeader(header);
                block.setSyncStatus(Block.STATUS_SYNC_HEADER);
                block.setVerifyStatus(Block.STATUS_UN_VERIFY_HEADER);
                boolean success = blockService.saveBlock(block);
                if (success) {
                    Logger.info("save block into database successfully with block hash:%s", header.getBlockHash());
                } else {
                    Logger.warn("fail to save block into database with block hash:%s", header.getBlockHash());
                }
            }
        } catch (Exception e) {
            Logger.error("save block into database encounter error:%s", e.fillInStackTrace());
        }
    }

    public void persistBlockBody(BlockMessage blockMessage) {
        try {
            boolean consistence = checkMerkleTreeConsistence(blockMessage);
            if (!consistence) {
                Logger.error("fail to check merkle root consistence with transactions, block hash:%s", blockMessage.getBlockHash());
            }

            Block block = BlockConvertor.convertBlockFromMessage(blockMessage);
            List<TransactionData> transactionList = TransactionConvertor.
                    convertTransactionDataFromMessage(blockMessage.getTransactions());

            boolean success = transactionService.batchSaveTransactionData(block, transactionList);
            if (success) {
                Logger.info("batch save transaction data list into database successfully with block hash:%s", block.getBlockHash());
                success = blockService.updateBlockSyncStatus(block, Block.STATUS_SYNC_HEADER, Block.STATUS_SYNC_TRANSACTION);
                if (success) {
                    Logger.info("update block sync status into database successfully with block hash:%s", block.getBlockHash());
                } else {
                    Logger.warn("fail to update block sync data into database with block hash:%s", block.getBlockHash());
                }
            } else {
                Logger.warn("fail to batch save transaction data list into database with block hash:%s", block.getBlockHash());
            }
        } catch (Exception e) {
            Logger.error("batch save transaction data list into database encounter error:%s", e.fillInStackTrace());
        }
    }

    private boolean checkMerkleTreeConsistence(BlockMessage blockMessage) throws IOException {
        MerkleTree merkleTree = MerkleTree.build(blockMessage.getTransactions(), false);
        Hash merkleRoot = merkleTree.getRoot().getNodeHash();
        return blockMessage.getMerkleRoot().equals(merkleRoot);
    }
}