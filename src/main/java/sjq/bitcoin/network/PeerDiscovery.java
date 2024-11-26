package sjq.bitcoin.network;


import sjq.bitcoin.configuration.NetworkConfiguration;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.data.NetworkAddress;
import sjq.bitcoin.network.node.PeerNode;
import sjq.bitcoin.utility.ThreadUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PeerDiscovery {

    private static final long REFRESH_PEER_DELAY = 1000;

    private static final long REFRESH_PEER_PERIOD = 10000;

    private NetworkConfiguration configuration;

    @Autowire
    private PeerManager peerManager;

    private Timer refreshPeerTimer;

    private RefreshPeerTask refreshPeerTask;

    private PrepareSeedPeerTask prepareSeedPeerTask;

    public void initialize() {
        configuration = NetworkConfiguration.getConfiguration();

        refreshPeerTimer = new Timer();
        refreshPeerTask = new RefreshPeerTask();
        prepareSeedPeerTask = new PrepareSeedPeerTask();
    }

    public void start() {
        ThreadUtils.run(prepareSeedPeerTask, "PrepareSeedPeerTask");
        Logger.info("peer discovery -> prepare peer task started!");

        refreshPeerTimer.schedule(refreshPeerTask, REFRESH_PEER_DELAY, REFRESH_PEER_PERIOD);
        Logger.info("peer discovery -> refresh peer task started!");
    }

    public void sync(List<NetworkAddress> addressList) {
        ThreadUtils.run(new SyncPeerAddressTask(addressList), "SyncPeerAddressTask");
        Logger.info("peer discovery -> sync peer address task started!");
    }

    public void setPeerManager(PeerManager peerManager) {
        this.peerManager = peerManager;
    }

    class PrepareSeedPeerTask implements Runnable {

        public void run() {
            try {
                if (peerManager.getPeerNodeSize() < configuration.getDnsSeedList().size()) {
                    List<String> seedList = configuration.getDnsSeedList();
                    for (String seed : seedList) {
                        PeerNode peer = peerManager.createPeerNode(seed);
                        boolean success = peer.start();
                        if (success) {
                            peerManager.injectPeerNode(peer);
                            Logger.info("successfully connect to peer, address:%s", peer.getAddress());
                        } else {
                            Logger.warn("failure to connect to peer, address:%s", peer.getAddress());
                        }
                    }
                }
            } catch (Exception e) {
                Logger.error("prepare peer task error:%s!", e);
            }
        }
    }

    class RefreshPeerTask extends TimerTask {

        public void run() {
            try {
                if (peerManager.getPeerNodeSize() < peerManager.getMaxConnectionCount()) {
                    peerManager.sendAddressRequest();
                    Logger.info("send address request to the peer!");
                }
            } catch (Exception e) {
                Logger.error("refresh peer task error:%s!", e);
            }
        }
    }

    class SyncPeerAddressTask implements Runnable {

        List<NetworkAddress> addressList;

        public SyncPeerAddressTask(List<NetworkAddress> addressList) {
            this.addressList = addressList;
        }

        public void run() {
            try {
                if (addressList != null && addressList.size()>0) {
                    int limit = peerManager.getNeedConnectionCount();
                    limit = (limit <= addressList.size()?limit:addressList.size());

                    int peerCount = 0;
                    for (NetworkAddress address:addressList) {
                        PeerNode peer = peerManager.createPeerNode(address.getAddress().getHostAddress());
                        boolean success = peer.start();
                        if (success) {
                            peerManager.injectPeerNode(peer);
                            Logger.info("successfully connect to peer, address:%s", peer.getAddress());
                        } else {
                            Logger.warn("failure to connect to peer, address:%s", peer.getAddress());
                        }

                        peerCount++;
                        if (peerCount >= limit) {
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                Logger.error("sync peer address task error:%s!", e);
                e.printStackTrace();
            }
        }
    }
}


