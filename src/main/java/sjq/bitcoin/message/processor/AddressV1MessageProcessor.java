package sjq.bitcoin.message.processor;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.context.Context;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.AddressV1Message;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.network.PeerDiscovery;
import sjq.bitcoin.network.PeerProcessor;
import sjq.bitcoin.network.node.PeerNode;

public class AddressV1MessageProcessor implements PeerProcessor {

    @Autowire
    private PeerDiscovery peerDiscovery;

    public void processMessage(PeerNode peerNode, Message message) {
        if (message instanceof AddressV1Message) {
            Logger.info("received AddressV1Message");
        }
    }
}
