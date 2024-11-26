package sjq.bitcoin.core;

import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.context.Context;
import sjq.bitcoin.network.PeerHandler;
import sjq.bitcoin.network.PeerManager;
import sjq.bitcoin.network.PeerDiscovery;

public class BitcoinCore {

    @Autowire
    private PeerManager peerManager;

    @Autowire
    private PeerHandler peerHandler;

    @Autowire
    private PeerDiscovery peerDiscovery;

    @Autowire
    private Blockchain blockchain;

    public void initialize() {
        peerManager.initialize();
        peerHandler.initialize();
        peerDiscovery.initialize();
    }

    public void start() {
        blockchain.start();
        peerManager.start();
        peerDiscovery.start();
    }
}
