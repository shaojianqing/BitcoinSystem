package sjq.bitcoin.network.processor;

import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.VersionAckMessage;
import sjq.bitcoin.message.VersionReqMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.network.PeerProcessor;
import sjq.bitcoin.network.node.PeerNode;

public class VersionAckMessageProcessor implements PeerProcessor {

    public void processMessage(PeerNode peerNode, Message message) {
        if (message instanceof VersionAckMessage) {
            if (peerNode.getVersionReqMessage() == null) {
                Logger.error("receive versionAckMessage before sending versionReqMessage!");
            }

            if (PeerNode.ACKNOWLEDGE.equals(peerNode.getStatus())) {
                Logger.error("peer node is in ACKNOWLEDGE status before receiving versionAckMessage!");
            }

            peerNode.setStatus(PeerNode.ACKNOWLEDGE);
        }
    }
}