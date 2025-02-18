package sjq.bitcoin.network;

import org.apache.commons.lang3.StringUtils;
import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.GetAddressMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.network.event.PeerChangeListener;
import sjq.bitcoin.network.node.PeerNode;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PeerManager {

    private final Map<InetSocketAddress, PeerNode> peerMap;

    private final Services requiredServices;

    private final int maxConnectionCount;

    @Autowire
    private PeerHandler peerHandler;

    @Autowire
    private Blockchain blockchain;

    private List<PeerChangeListener> peerChangeListeners;

    public PeerManager() {
        requiredServices = Services.none();
        maxConnectionCount = Constants.MAX_CONNECTION_COUNT;
        peerMap = new ConcurrentHashMap<>(maxConnectionCount);
        peerChangeListeners = new ArrayList<>();
    }

    public PeerNode createPeerNode(InetSocketAddress address) throws Exception {
        return new PeerNode(this, address, requiredServices, blockchain.getBestBlockHeight());
    }

    public void injectPeerNode(PeerNode peerNode) {
        this.peerMap.put(peerNode.getAddress(), peerNode);
        this.notifyPeerChangeEvent(peerNode);
    }

    public void sendAddressRequest() {
        Collection<PeerNode> peerNodeList = peerMap.values();
        for (PeerNode node:peerNodeList) {
            if (StringUtils.equals(node.getStatus(), PeerNode.ACKNOWLEDGE)) {
                node.sendMessage(new GetAddressMessage());
                Logger.info("send address request to peer:%s!", node.getAddress());
            }
        }
    }

    public PeerNode selectSyncNode(long peerSelectIndex) {
        Collection<PeerNode> activePeerNodeList = peerMap.values().
                stream().filter((peer) -> PeerNode.ACKNOWLEDGE.equals(peer.getStatus())).collect(Collectors.toList());
        if (activePeerNodeList.size()>0) {
            Object[] activePeerNodes = activePeerNodeList.toArray();
            int position = (int)peerSelectIndex%activePeerNodeList.size();
            return (PeerNode)activePeerNodes[position];
        }
        return null;
    }

    public void notifyPeerChangeEvent(PeerNode peerNode) {
        for (PeerChangeListener listener:peerChangeListeners) {
            listener.onPeerChange(this, peerNode);
        }
    }

    public void registerPeerChangeListener(PeerChangeListener listener) {
        this.peerChangeListeners.add(listener);
    }

    public void processPeerData(PeerNode node, Message message) {
        this.peerHandler.handlePeerData(node, message);
    }

    public void disconnectPeerNode(PeerNode peerNode) {
        this.peerMap.remove(peerNode.getAddress());
        this.notifyPeerChangeEvent(peerNode);
    }

    public boolean containPeerAddress(InetSocketAddress address) {
        return peerMap.containsKey(address);
    }

    public int getMaxConnectionCount() {
        return maxConnectionCount;
    }

    public int getNeedConnectionCount() {
        if (maxConnectionCount <= peerMap.size()) {
            return 0;
        }
        return maxConnectionCount - peerMap.size();
    }

    public int getPeerNodeSize() {
        return peerMap.size();
    }

    public List<PeerNode> getPeerNodeList() {
        return new ArrayList<>(peerMap.values());
    }

    public List<PeerNode> getConnectedPeerNodeList() {
        return peerMap.values().stream().filter(peer->
                PeerNode.CONNECTED.equals(peer.getStatus())).collect(Collectors.toList());
    }
}
