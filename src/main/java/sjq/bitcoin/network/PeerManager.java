package sjq.bitcoin.network;

import org.apache.commons.lang3.StringUtils;
import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.configuration.NetworkConfiguration;
import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.context.Context;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.GetAddressMessage;
import sjq.bitcoin.message.GetHeadersMessage;
import sjq.bitcoin.message.SendHeadersMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.network.node.PeerNode;
import sjq.bitcoin.utility.HexUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PeerManager {

    private final NetworkConfiguration configuration;

    private final Map<String, PeerNode> peerMap;

    private final Services requiredServices;

    private final int maxConnectionCount;

    @Autowire
    private PeerHandler peerHandler;

    @Autowire
    private Blockchain blockchain;

    public PeerManager(){
        configuration = NetworkConfiguration.getConfiguration();
        requiredServices = Services.none();
        maxConnectionCount = Constants.MAX_CONNECTION_COUNT;
        peerMap = new ConcurrentHashMap<>(maxConnectionCount);
    }

    public PeerNode createPeerNode(String address) throws Exception {
        return new PeerNode(this, address, requiredServices, blockchain.getBestBlockHeight());
    }

    public void injectPeerNode(PeerNode peerNode) {
        this.peerMap.put(peerNode.getIPAddress(), peerNode);
    }

    public void initialize() {

    }

    public void start() {

    }

    public void sendAddressRequest() {
        Collection<PeerNode> peerNodeList = peerMap.values();
        for (PeerNode node:peerNodeList) {
            if (StringUtils.equals(node.getStatus(), PeerNode.ACKNOWLEDGE)) {
                node.sendMessage(new GetAddressMessage());
            }
        }
    }

    public PeerNode selectBlockSyncNode() {
        Collection<PeerNode> peerNodeList = peerMap.values();
        for (PeerNode node:peerNodeList) {
            if (PeerNode.ACKNOWLEDGE.equals(node.getStatus())) {
                return node;
            }
        }
        return null;
    }

    public void processPeerData(PeerNode node, Message message) {
        this.peerHandler.handlePeerData(node, message);
    }

    public void disconnectPeerNode(PeerNode node) {
        peerMap.remove(node.getIPAddress());
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
}
