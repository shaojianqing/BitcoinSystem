package sjq.bitcoin.service;

import org.apache.commons.lang3.StringUtils;
import sjq.bitcoin.configuration.NetworkConfiguration;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.BlockMessage;
import sjq.bitcoin.orm.transaction.TransactionTemplate;
import sjq.bitcoin.service.convertor.BlockConvertor;
import sjq.bitcoin.storage.dao.BlockDao;
import sjq.bitcoin.storage.domain.Block;

import java.util.List;

public class BlockService {

    private static final NetworkConfiguration configuration = NetworkConfiguration.getConfiguration();

    private static final Integer GENESIS_BLOCK_HEIGHT = 0;

    private static final Integer BLOCK_BATCH_LIMIT = 10;

    @Autowire
    private BlockDao blockDao;

    @Autowire
    private TransactionService transactionService;

    @Autowire
    private TransactionTemplate transactionTemplate;

    private Block GENESIS_BLOCK = new Block();

    public BlockService() {
        try {
            BlockMessage blockMessage = configuration.getGenesisBlock();
            GENESIS_BLOCK = BlockConvertor.convert(blockMessage, GENESIS_BLOCK_HEIGHT);
        } catch (Exception e) {
            Logger.fatal("can not initiate genesis block exception:%s", e);
            System.exit(-1);
        }
    }

    public Block getBestBlock() throws Exception {
        Block bestBlock = blockDao.getBestBlock();
        if (bestBlock == null) {
            bestBlock = GENESIS_BLOCK;
        }
        return bestBlock;
    }

    public List<Block> queryBlockWithHeaderSynced() throws Exception {
        return blockDao.queryBlockBatchBySyncStatus(Block.STATUS_SYNC_HEADER, BLOCK_BATCH_LIMIT);
    }

    public Block getBlockByHash(String blockHash) throws Exception {
        // Because genesis block is not stored in the database, so we tell
        // whether the block hash is equal to genesis block, and
        // then query block from database if not equal.
        if (StringUtils.equals(GENESIS_BLOCK.getBlockHash(), blockHash)) {
            return GENESIS_BLOCK;
        }
        return blockDao.getBlockByHash(blockHash);
    }

    public boolean saveBlock(Block block) throws Exception {
        // If the block does exist in the database, directly ignore the block data. This may
        // happen when receiving repeated block header message from other p2p node.
        boolean exist = blockDao.existBlock(block);
        if (exist) {
            Logger.info("ignore exist block data in the database, block hash:%s", block.getBlockHash());
            return false;
        }

        // Only if the previous block has been stored in the database, the received
        // block can be saved into the database as expected. This is mainly for
        // data consistency consideration.
        Block prevBlock = getBlockByHash(block.getPrevBlockHash());
        if (prevBlock != null) {
            Long blockHeight = prevBlock.getBlockHeight() + 1;
            block.setBlockHeight(blockHeight);
            return blockDao.saveBlock(block);
        }
        Logger.warn("ignore block data without previous block in the database, block hash:%s", block.getBlockHash());
        return false;
    }

    public boolean updateBlockSyncStatus(Block block, String oldStatus, String newStatus) throws Exception {
        return blockDao.updateBlockSyncStatus(block.getBlockHash(), oldStatus, newStatus);
    }

    public boolean updateBlockVerifyStatus(Block block, String oldStatus, String newStatus) throws Exception {
        return blockDao.updateBlockVerifyStatus(block.getBlockHash(), oldStatus, newStatus);
    }
}
