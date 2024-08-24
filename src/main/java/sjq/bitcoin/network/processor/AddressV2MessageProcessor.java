package sjq.bitcoin.network.processor;

import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.AddressV2Message;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.network.PeerProcessor;
import sjq.bitcoin.network.node.PeerNode;

public class AddressV2MessageProcessor implements PeerProcessor {

    public void processMessage(PeerNode peerNode, Message message) {
        if (message instanceof AddressV2Message) {
            Logger.info("received AddressV2Message");
        }
    }
}