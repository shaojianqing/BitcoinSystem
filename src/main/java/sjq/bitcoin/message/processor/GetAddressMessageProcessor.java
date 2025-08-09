package sjq.bitcoin.message.processor;

import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.GetAddressMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.network.PeerProcessor;
import sjq.bitcoin.network.node.PeerNode;

public class GetAddressMessageProcessor implements PeerProcessor {

    public void processMessage(PeerNode peerNode, Message message) {
        if (message instanceof GetAddressMessage) {
            Logger.info("received GetAddressMessage");
        }
    }
}
