package sjq.bitcoin.network.processor;

import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.VersionReqMessage;
import sjq.bitcoin.network.PeerProcessor;
import sjq.bitcoin.network.node.PeerNode;

public class VersionReqMessageProcessor implements PeerProcessor {

    public void processMessage(PeerNode peerNode, Message message) {
        if (message instanceof VersionReqMessage) {
            VersionReqMessage versionReqMessage = (VersionReqMessage)message;


        }

    }
}