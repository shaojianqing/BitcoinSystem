package sjq.bitcoin.network;

import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.network.node.PeerNode;

public interface PeerProcessor {

    void processMessage(PeerNode peerNode, Message message);
}
