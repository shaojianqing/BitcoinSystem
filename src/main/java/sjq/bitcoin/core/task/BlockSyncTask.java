package sjq.bitcoin.core.task;

import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.network.PeerManager;

import java.util.TimerTask;

public class BlockSyncTask extends TimerTask {

    private Blockchain blockchain;

    private PeerManager peerManager;

    public BlockSyncTask(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    @Override
    public void run() {
        peerManager.startSyncBlockData();
    }

    public void setPeerManager(PeerManager peerManager) {
        this.peerManager = peerManager;
    }
}
