package sjq.bitcoin.network;

import sjq.bitcoin.network.node.PeerNode;

import java.util.List;

public class PeerManager {

    private static PeerManager Instance;

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

    }


    public void disconnectPeerNode(PeerNode node) {

    }
}
