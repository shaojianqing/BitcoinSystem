package sjq.bitcoin.core.task;

import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.network.PeerManager;

import java.util.TimerTask;

public class BlockSyncTask extends TimerTask {

    @Autowire
    private Blockchain blockchain;

    @Autowire
    private PeerManager peerManager;

    @Override
    public void run() {
        peerManager.startSyncBlockData();
    }
}
