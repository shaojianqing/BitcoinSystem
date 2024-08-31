package sjq.bitcoin.network.processor;

import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.AddressV1Message;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.network.PeerDiscovery;
import sjq.bitcoin.network.PeerProcessor;
import sjq.bitcoin.network.node.PeerNode;

public class AddressV1MessageProcessor implements PeerProcessor {

    private PeerDiscovery peerDiscovery;

    public AddressV1MessageProcessor(PeerDiscovery peerDiscovery) {
        this.peerDiscovery = peerDiscovery;
    }

    public void processMessage(PeerNode peerNode, Message message) {
        if (message instanceof AddressV1Message) {
            Logger.info("received AddressV1Message");
        }
    }
}
