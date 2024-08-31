package sjq.bitcoin.blockchain;

import sjq.bitcoin.core.task.BlockSyncTask;
import sjq.bitcoin.network.PeerManager;
import sjq.bitcoin.storage.StorageRepo;

import java.util.Timer;
import java.util.TimerTask;

public class Blockchain {

    private static final long SYNC_TASK_DELAY = 20000;

    private static final long SYNC_TASK_PERIOD = 5000;

    private static Blockchain Instance;

    private PeerManager peerManager;

    private StorageRepo storageRepo;

    private Timer syncTaskTimer;

    private BlockSyncTask blockSyncTask;

    private Blockchain() {
        syncTaskTimer = new Timer();
        blockSyncTask = new BlockSyncTask(this);
    }

    public static Blockchain build() {
        if (Instance == null) {
            Instance = new Blockchain();
        }
        return Instance;
    }

    public void start() {
        syncTaskTimer.schedule(blockSyncTask, SYNC_TASK_DELAY, SYNC_TASK_PERIOD);
    }

    public int getBestBlockHeight() {
        return 0;
    }

    public void setPeerManager(PeerManager peerManager) {
        this.peerManager = peerManager;
        this.blockSyncTask.setPeerManager(peerManager);
    }

    public void setStorageRepo(StorageRepo storageRepo) {
        this.storageRepo = storageRepo;
    }

}
