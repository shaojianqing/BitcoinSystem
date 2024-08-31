package sjq.bitcoin.network.processor;

import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.GetHeadersMessage;
import sjq.bitcoin.message.SendHeadersMessage;
import sjq.bitcoin.message.VersionAckMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.network.PeerProcessor;
import sjq.bitcoin.network.node.PeerNode;
import sjq.bitcoin.utility.HexUtils;

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

            GetHeadersMessage getHeadersMessage = new GetHeadersMessage();
            getHeadersMessage.setVersion(Constants.VERSION_CURRENT);
            byte[] hash = HexUtils.parseHex("00000000000000000000eadb324b65cc49e68c0f24cfe6b0b20dd76bdf4563f9");
            getHeadersMessage.addHash(Hash.wrap(hash));
            byte[] stopHash = HexUtils.parseHex("00000000000000000002676e1db371c5bd13a34bc1e41cc6b5166e064bdc2a0a");
            getHeadersMessage.setStopHash(Hash.wrap(stopHash));

            try {
                peerNode.sendMessage(new SendHeadersMessage());
                peerNode.sendMessage(getHeadersMessage);
            } catch (Exception e) {
                peerNode.connectionClose();
                Logger.error("peer sends getHeadersMessage error:%s", e);
            }
        }
    }
}