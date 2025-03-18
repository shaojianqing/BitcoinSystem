package sjq.bitcoin.core.task;

import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.GetDataMessage;
import sjq.bitcoin.message.data.InventoryItem;
import sjq.bitcoin.network.PeerManager;
import sjq.bitcoin.network.node.PeerNode;
import sjq.bitcoin.storage.domain.Block;

import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

public class TransactionSyncTask extends TimerTask {

    private final AtomicLong peerSelector;

    @Autowire
    private Blockchain blockchain;

    @Autowire
    private PeerManager peerManager;

    public TransactionSyncTask() {
        peerSelector = new AtomicLong(0);
    }

    @Override
    public void run() {
        try {
            Logger.info("start to execute transaction sync task!");
            List<Block> blockList = blockchain.queryBlockWithHeaderSynced();
            if (blockList!=null && blockList.size()>0) {
                for (Block block:blockList) {
                    startTransactionSyncForBlock(block);
                }
            }
        } catch (Exception e) {
            Logger.error("sync transaction data raise error:%s", e);
        }
    }

    private void startTransactionSyncForBlock(Block block) {
        PeerNode transactionSyncNode = peerManager.selectSyncNode(peerSelector.getAndIncrement());
        if (transactionSyncNode != null) {
            GetDataMessage getBlockMessage = new GetDataMessage();
            InventoryItem inventoryItem = InventoryItem.createBlockItem(block.getBlockHash());
            getBlockMessage.addInventoryItem(inventoryItem);
            transactionSyncNode.sendMessage(getBlockMessage);
            Logger.info("send get data message for block to node:%s, successfully, with block hash:%s",
                    transactionSyncNode.getAddress(), block.getBlockHash());
        }
    }
}