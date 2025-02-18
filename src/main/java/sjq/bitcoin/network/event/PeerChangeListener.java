package sjq.bitcoin.network.event;

import sjq.bitcoin.network.PeerManager;
import sjq.bitcoin.network.node.PeerNode;

public interface PeerChangeListener {

    void onPeerChange(PeerManager manager, PeerNode node);
}
