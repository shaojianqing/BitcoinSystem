package sjq.bitcoin.network;


import sjq.bitcoin.configuration.NetworkConfiguration;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.data.NetworkAddress;
import sjq.bitcoin.network.node.PeerNode;
import sjq.bitcoin.utility.ThreadUtils;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PeerDiscovery {

    private static final long REFRESH_PEER_DELAY = 500;

    private static final long REFRESH_PEER_PERIOD = 10000;

    @Autowire
    private PeerManager peerManager;

    private NetworkConfiguration configuration;

    private Timer refreshSeedPeerTimer;

    private Timer refreshExtraPeerTimer;

    private Timer refreshAcknowledgePeerTimer;

    private RefreshSeedPeerTask refreshSeedPeerTask;

    private RefreshExtraPeerTask refreshExtraPeerTask;

    private RefreshAcknowledgePeerTask refreshAcknowledgePeerTask;

    public void initialize() {
        configuration = NetworkConfiguration.getConfiguration();

        refreshSeedPeerTimer = new Timer();
        refreshExtraPeerTimer = new Timer();
        refreshAcknowledgePeerTimer = new Timer();

        refreshSeedPeerTask = new RefreshSeedPeerTask();
        refreshExtraPeerTask = new RefreshExtraPeerTask();
        refreshAcknowledgePeerTask = new RefreshAcknowledgePeerTask();
    }

    public void start() {

        refreshSeedPeerTimer.schedule(refreshSeedPeerTask, REFRESH_PEER_DELAY, REFRESH_PEER_PERIOD);
        Logger.info("peer discovery -> refresh seed peer task started!");

        refreshExtraPeerTimer.schedule(refreshExtraPeerTask, REFRESH_PEER_DELAY, REFRESH_PEER_PERIOD);
        Logger.info("peer discovery -> refresh extra peer task started!");

        refreshAcknowledgePeerTimer.schedule(refreshAcknowledgePeerTask, REFRESH_PEER_DELAY, REFRESH_PEER_PERIOD);
        Logger.info("peer discovery -> refresh acknowledge peer task started!");
    }

    public void sync(List<NetworkAddress> addressList) {
        ThreadUtils.run(new SyncPeerAddressTask(addressList), "SyncPeerAddressTask");
        Logger.info("peer discovery -> sync peer address task started!");
    }

    private boolean tryConnectPeerNode(InetSocketAddress address) throws Exception {
        if (address==null) {
            return false;
        }
        if (peerManager.containPeerAddress(address)) {
            return true;
        }
        PeerNode peer = peerManager.createPeerNode(address);
        boolean success = peer.start();
        if (success) {
            peer.setStatus(PeerNode.CONNECTED);
            peer.setConnectionTime(new Date());
            peerManager.injectPeerNode(peer);
            Logger.info("successfully connect to peer, address:%s", peer.getAddress());
            return true;
        } else {
            Logger.warn("failure to connect to peer, address:%s", peer.getAddress());
            return false;
        }
    }

    class RefreshSeedPeerTask extends TimerTask {

        @Override
        public void run() {
            try {
                if (peerManager.getPeerNodeSize() < configuration.getDnsSeedList().size()) {
                    List<String> seedList = configuration.getDnsSeedList();
                    for (String seed : seedList) {
                        InetSocketAddress address = new InetSocketAddress(seed, configuration.getPort());
                        tryConnectPeerNode(address);
                    }
                }
            } catch (Exception e) {
                Logger.error("refresh seed peer task error:%s!", e);
                e.printStackTrace();
            }
        }
    }

    class RefreshExtraPeerTask extends TimerTask {

        @Override
        public void run() {
            try {
                if (peerManager.getPeerNodeSize() < peerManager.getMaxConnectionCount()) {
                    peerManager.sendAddressRequest();
                }
            } catch (Exception e) {
                Logger.error("refresh extra peer task error:%s!", e);
            }
        }
    }

    class RefreshAcknowledgePeerTask extends TimerTask {

        @Override
        public void run() {
            List<PeerNode> peerNodeList = peerManager.getConnectedPeerNodeList();
            if (peerNodeList!=null && peerNodeList.size()>0) {
                peerNodeList.forEach(PeerNode::sendVersionReqMessage);
            }
        }
    }

    class SyncPeerAddressTask implements Runnable {

        List<NetworkAddress> addressList;

        public SyncPeerAddressTask(List<NetworkAddress> addressList) {
            this.addressList = addressList;
        }

        @Override
        public void run() {
            try {
                if (addressList != null && addressList.size()>0) {
                    int limit = peerManager.getNeedConnectionCount();
                    limit = Math.min(limit, addressList.size());

                    if (limit==0) {
                        Logger.info("peer count is enough for network status, count:%d", peerManager.getPeerNodeSize());
                        return;
                    }

                    int peerCount = 0;
                    for (NetworkAddress address:addressList) {
                        boolean success = tryConnectPeerNode(address.getAddress());
                        if (success) {
                            peerCount++;
                        }
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
