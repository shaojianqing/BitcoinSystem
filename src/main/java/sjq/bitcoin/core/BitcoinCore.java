package sjq.bitcoin.core;

import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.network.PeerHandler;
import sjq.bitcoin.network.PeerManager;
import sjq.bitcoin.network.PeerMonitor;
import sjq.bitcoin.storage.StorageRepo;

public class BitcoinCore {

    private StorageRepo storageRepo;

    private PeerManager peerManager;

    private PeerHandler peerHandler;

    private PeerMonitor peerMonitor;

    private Blockchain blockchain;

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
