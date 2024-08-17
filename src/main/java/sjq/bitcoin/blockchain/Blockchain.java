package sjq.bitcoin.blockchain;

import sjq.bitcoin.network.PeerManager;
import sjq.bitcoin.storage.StorageRepo;

public class Blockchain {

    private static Blockchain Instance;

    private PeerManager peerManager;

    private StorageRepo storageRepo;

    private Blockchain() {
    }

    public static Blockchain build() {
        if (Instance == null) {
            Instance = new Blockchain();
        }
        return Instance;
    }

    public int getBestBlockHeight() {
        return 0;
    }

    public void setPeerManager(PeerManager peerManager) {
        this.peerManager = peerManager;
    }

    public void setStorageRepo(StorageRepo storageRepo) {
        this.storageRepo = storageRepo;
    }
}
