package sjq.bitcoin.core.task;

import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.logger.Logger;

import java.util.TimerTask;

public class BlockRefreshTask extends TimerTask {

    @Autowire
    private Blockchain blockchain;

    @Override
    public void run() {
        Logger.info("start to execute block refresh task to update block height!");
    }
}
