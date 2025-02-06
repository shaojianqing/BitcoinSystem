package sjq.bitcoin.core.task;

import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.network.PeerManager;

import java.util.TimerTask;

public class TransactionSyncTask extends TimerTask {

    @Autowire
    private Blockchain blockchain;

    @Autowire
    private PeerManager peerManager;

    @Override
    public void run() {
        Logger.info("start to execute transaction sync task!");
    }
}
