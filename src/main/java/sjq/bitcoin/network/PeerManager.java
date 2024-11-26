package sjq.bitcoin.network;

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

    private NetworkConfiguration configuration;

    @Autowire
    private PeerHandler peerHandler;

    @Autowire
    private Blockchain blockchain;

    private Map<String, PeerNode> peerMap;

    private Services requiredServices;

    private PeerNode downloadPeerNode;

    private int maxConnectionCount;

    public PeerManager(){
        configuration = NetworkConfiguration.getConfiguration();
        requiredServices = Services.none();
        maxConnectionCount = Constants.MAX_CONNECTION_COUNT;
        peerMap = new ConcurrentHashMap<String, PeerNode>(maxConnectionCount);
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

    public void sendAddressRequest() throws Exception{
        Collection<PeerNode> peerNodeList = peerMap.values();
        for (PeerNode node:peerNodeList) {
            if (node.getStatus() == PeerNode.ACKNOWLEDGE) {
                node.sendMessage(new GetAddressMessage());
            }
        }
    }

    public void startSyncBlockData() {
        selectDownloadNode();
        if (downloadPeerNode != null) {
            GetHeadersMessage getHeadersMessage = new GetHeadersMessage();
            getHeadersMessage.setVersion(Constants.VERSION_CURRENT);
            byte[] hash = HexUtils.parseHex("00000000000000000000eadb324b65cc49e68c0f24cfe6b0b20dd76bdf4563f9");
            getHeadersMessage.addHash(Hash.wrap(hash));
            byte[] stopHash = HexUtils.parseHex("00000000000000000002676e1db371c5bd13a34bc1e41cc6b5166e064bdc2a0a");
            getHeadersMessage.setStopHash(Hash.wrap(stopHash));

            try {
                //downloadPeerNode.sendMessage(getHeadersMessage);
            } catch (Exception e) {
                downloadPeerNode.connectionClose();
                Logger.error("peer sends getHeadersMessage error:%s", e);
            }
        }
    }

    private void selectDownloadNode() {
        if (downloadPeerNode==null) {
            Collection<PeerNode> peerNodeList = peerMap.values();
            for (PeerNode node:peerNodeList) {
                if (node.getStatus() == PeerNode.ACKNOWLEDGE) {
                    downloadPeerNode = node;
                    break;
                }
            }
        }
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
