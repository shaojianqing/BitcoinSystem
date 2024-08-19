package sjq.bitcoin.network.processor;

import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.InventoryMessage;
import sjq.bitcoin.message.VersionReqMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.network.PeerProcessor;
import sjq.bitcoin.network.node.PeerNode;

public class InventoryMessageProcessor implements PeerProcessor {

    public void processMessage(PeerNode peerNode, Message message) {
        if (message instanceof InventoryMessage) {
            InventoryMessage inventoryMessage = (InventoryMessage)message;

            Logger.info("receiving inventoryMessage:%s", inventoryMessage);
        }
    }
}