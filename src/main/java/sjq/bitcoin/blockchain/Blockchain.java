package sjq.bitcoin.blockchain;

import sjq.bitcoin.configuration.NetworkConfiguration;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.context.Context;
import sjq.bitcoin.core.task.BlockSyncTask;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.convertor.BlockConvertor;
import sjq.bitcoin.message.data.BlockHeader;
import sjq.bitcoin.network.PeerManager;
import sjq.bitcoin.service.BlockService;
import sjq.bitcoin.storage.domain.Block;

import java.util.Timer;

public class Blockchain {

    private static final long SYNC_TASK_DELAY = 20000;

    private static final long SYNC_TASK_PERIOD = 5000;

    @Autowire
    private PeerManager peerManager;

    @Autowire
    private BlockSyncTask blockSyncTask;

    @Autowire
    private BlockService blockService;

    private Timer syncTaskTimer;

    public Blockchain() {
        syncTaskTimer = new Timer();
    }

    public void start() {
        syncTaskTimer.schedule(blockSyncTask, SYNC_TASK_DELAY, SYNC_TASK_PERIOD);
    }

    public Long getBestBlockHeight() {
        Block bestBlock = blockService.getBestBlock();
        if (bestBlock==null) {
            // if best block is null, it means there does not exist any normal block in local storage.
            // Directly take the genesis block as the best block for block height.
            bestBlock = blockService.getGenesisBlock();
        }
        return bestBlock.getBlockHeight();
    }

    /**
     * In header first strategy, the block data is persisted into database with block header directly, and
     * the corresponding status is set to be initial value, so that the block data could be completed later.
     * @param header block header data by getHeaders message from another remote peer node.
     */
    public void persistBlockWithHeader(BlockHeader header) {
        Block block = BlockConvertor.convertBlockFromHeader(header);
        block.setSyncStatus(Block.STATUS_SYNC_HEADER);
        block.setVerifyStatus(Block.STATUS_UN_VERIFY_HEADER);
        boolean success = blockService.saveBlock(block);
        if (!success) {
            Logger.error("fail to save block into database with header:%s", header);
        }
    }
}
