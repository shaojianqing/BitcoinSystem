package sjq.bitcoin.network;

import sjq.bitcoin.network.node.PeerNode;

import java.util.List;

public class PeerManager {

    private static PeerManager Instance;

    private PeerHandler peerHandler;

    private List<PeerNode> peerNodeList;

    private PeerManager(){
    }

    private PeerManager build() {
         if (Instance == null) {
             Instance = new PeerManager();
         }
         return Instance;
    }

    public void initialize() {

    }


    public void processPeerData(byte[] data) {
        this.peerHandler.handlePeerData(data);
    }

    public void refreshPeerStatement() {

    }


    public void disconnectPeerNode(PeerNode node) {

    }

    public void setPeerHandler(PeerHandler peerHandler) {
        this.peerHandler = peerHandler;
    }
}
