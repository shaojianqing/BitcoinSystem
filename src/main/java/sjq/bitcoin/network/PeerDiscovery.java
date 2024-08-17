package sjq.bitcoin.network;


import sjq.bitcoin.configuration.NetworkConfiguration;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.network.node.PeerNode;
import sjq.bitcoin.utility.ThreadUtils;

import java.util.List;

public class PeerDiscovery {

    private static PeerDiscovery Instance;

    private NetworkConfiguration configuration;

    private PeerManager peerManager;

    private RefreshPeerTask refreshPeerTask;

    private PeerDiscovery(){
    }

    public static PeerDiscovery build() {
        if (Instance == null) {
            Instance = new PeerDiscovery();
        }
        return Instance;
    }

    public void initialize() {
        configuration = NetworkConfiguration.getConfiguration();

        refreshPeerTask = new RefreshPeerTask();
    }

    public void start() {
        ThreadUtils.run(refreshPeerTask, "RefreshPeerTask");
        Logger.info("peer discovery -> refresh peer task started!");
    }

    public void setPeerManager(PeerManager peerManager) {
        this.peerManager = peerManager;
    }

    class RefreshPeerTask implements Runnable {

        public void run() {

            while (true) {
                try {
                    if (peerManager.getActivePeerCount() < configuration.getDnsSeedList().size()) {
                        List<String> seedList = configuration.getDnsSeedList();
                        for (String seed : seedList) {
                            PeerNode peer = peerManager.createPeerNode(seed);
                            peerManager.addPeerNode(peer);
                            peer.start();
                        }
                    }
                } catch (Exception e) {
                    Logger.error("refresh peer task error:%s!", e);
                }
            }
        }
    }
}


