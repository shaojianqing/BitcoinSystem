package sjq.bitcoin.core.task;

import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.GetHeadersMessage;
import sjq.bitcoin.network.PeerManager;
import sjq.bitcoin.network.node.PeerNode;
import sjq.bitcoin.storage.domain.Block;

import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

public class BlockSyncTask extends TimerTask {

    private final AtomicLong peerSelector;

    @Autowire
    private Blockchain blockchain;

    @Autowire
    private PeerManager peerManager;

    public BlockSyncTask() {
        peerSelector = new AtomicLong(0);
    }

    @Override
    public void run() {
        try {
            Logger.info("start to execute block sync task!");
            PeerNode blockSyncNode = peerManager.selectSyncNode(peerSelector.getAndIncrement());
            if (blockSyncNode != null) {
                GetHeadersMessage getHeadersMessage = new GetHeadersMessage();
                getHeadersMessage.setVersion(Constants.VERSION_CURRENT);

                Block block = blockchain.getBestBlock();
                Hash blockHash = Hash.wrap(block.getBlockHash());
                getHeadersMessage.addHash(blockHash);
                getHeadersMessage.setStopHash(Hash.ZERO_HASH);
                blockSyncNode.sendMessage(getHeadersMessage);
                Logger.info("send getheaders message to node:%s, successfully, with block hash:%s",
                        blockSyncNode.getAddress(), block.getBlockHash());
            }
        } catch (Exception e) {
            Logger.error("sync block data raise error:%s", e);
        }
    }
}
