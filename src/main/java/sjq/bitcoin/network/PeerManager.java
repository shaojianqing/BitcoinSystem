package sjq.bitcoin.network;

import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.configuration.NetworkConfiguration;
import sjq.bitcoin.network.node.PeerNode;

import java.util.ArrayList;
import java.util.List;

public class PeerManager {

    private static PeerManager Instance;

    private NetworkConfiguration configuration;

    private PeerHandler peerHandler;

    private Blockchain blockchain;

    private List<PeerNode> peerNodeList;

    private int maxConnectionCount;

    private PeerManager(){
        configuration = NetworkConfiguration.getConfiguration();
        peerNodeList = new ArrayList<>();
    }

    public static PeerManager build() {
         if (Instance == null) {
             Instance = new PeerManager();
         }
         return Instance;
    }

    public PeerNode createPeerNode(String address) throws Exception {
        return new PeerNode(this, address, blockchain.getBestBlockHeight());
    }

    public void addPeerNode(PeerNode peerNode) {
        this.peerNodeList.add(peerNode);
    }

    public void initialize() {

    }

    public void start() {

    }

    public void processPeerData(PeerNode node, byte[] data) {
        this.peerHandler.handlePeerData(node, data);
    }

    public void disconnectPeerNode(PeerNode node) {

    }

    public void setPeerHandler(PeerHandler peerHandler) {
        this.peerHandler = peerHandler;
    }

    public void setBlockchain(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    public int getMaxConnectionCount() {
        return maxConnectionCount;
    }

    public int getActivePeerCount() {
        return peerNodeList.size();
    }
}
