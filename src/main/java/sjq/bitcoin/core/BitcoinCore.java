package sjq.bitcoin.core;

import sjq.bitcoin.network.PeerManager;
import sjq.bitcoin.storage.dao.StorageRepo;

public class BitcoinCore {

    private StorageRepo storageRepo;

    private PeerManager peerManager;

    private BitcoinCore() {

    }

    public static BitcoinCore build() {
        return new BitcoinCore();
    }

    public void start() {

    }

    public void setStorageRepo(StorageRepo storageRepo) {
        this.storageRepo = storageRepo;
    }
}
