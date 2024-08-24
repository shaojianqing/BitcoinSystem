package sjq.bitcoin.core;

import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.network.PeerHandler;
import sjq.bitcoin.network.PeerManager;
import sjq.bitcoin.network.PeerDiscovery;
import sjq.bitcoin.storage.StorageRepo;

public class BitcoinCore {

    private static BitcoinCore Instance;

    private StorageRepo storageRepo;

    private PeerManager peerManager;

    private PeerHandler peerHandler;

    private PeerDiscovery peerDiscovery;

    private Blockchain blockchain;

    private BitcoinCore() {
        peerManager = PeerManager.build();
        peerHandler = PeerHandler.build();
        peerDiscovery = PeerDiscovery.build();
        storageRepo = StorageRepo.build();
        blockchain = Blockchain.build();

        peerManager.setPeerHandler(peerHandler);
        peerManager.setBlockchain(blockchain);
        peerDiscovery.setPeerManager(peerManager);
        peerHandler.setBlockchain(blockchain);
        peerHandler.setPeerManager(peerManager);
        blockchain.setPeerManager(peerManager);
        blockchain.setStorageRepo(storageRepo);

        peerManager.initialize();
        peerHandler.initialize();
        peerDiscovery.initialize();
    }

    public static BitcoinCore build() {
        if (Instance == null) {
            Instance = new BitcoinCore();
        }
        return Instance;
    }

    public void start() {
        //peerManager.start();
        //peerDiscovery.start();
    }

    public void setStorageRepo(StorageRepo storageRepo) {
        this.storageRepo = storageRepo;
    }
}
