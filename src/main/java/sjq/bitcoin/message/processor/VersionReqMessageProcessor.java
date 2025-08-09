package sjq.bitcoin.message.processor;

import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.GetAddressMessage;
import sjq.bitcoin.message.SendAddrV2Message;
import sjq.bitcoin.message.VersionAckMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.VersionReqMessage;
import sjq.bitcoin.network.PeerProcessor;
import sjq.bitcoin.network.Services;
import sjq.bitcoin.network.node.PeerNode;

public class VersionReqMessageProcessor implements PeerProcessor {

    public void processMessage(PeerNode peerNode, Message message) {
        if (message instanceof VersionReqMessage) {
            VersionReqMessage versionReqMessage = (VersionReqMessage)message;
            if (peerNode.getPeerVersionReqMessage() != null) {
                Logger.error("versionReqMessage has been received already!");
                return;
            }

            peerNode.setPeerVersionReqMessage(versionReqMessage);
            Services peerServices = versionReqMessage.getLocalServices();
            if (!peerServices.any(Services.NODE_NETWORK | Services.NODE_NETWORK_LIMITED) ||
                    versionReqMessage.getBestBlockHeight() == 0) {
                peerNode.connectionClose();
                Logger.warn("peer node is not the expected peer service type! peer services:%d", peerServices.getDefinition());
                return;
            }

            if (!peerServices.has(peerNode.getRequiredServices())) {
                peerNode.connectionClose();
                Logger.warn("peer node is not supported for the required services! " +
                        "peer services:%d, required services:%d", peerServices.getDefinition(), peerNode.getRequiredServices());
                return;
            }

            if (peerServices.has(Services.NODE_BITCOIN_CASH)) {
                peerNode.connectionClose();
                Logger.warn("peer node has bitcoin cash services which is incompatible blockchain!");
                return;
            }

            if (versionReqMessage.getBestBlockHeight() < 0) {
                Logger.error("best block height is not correct in peer node, best block height:%d!", versionReqMessage.getBestBlockHeight());
                return;
            }

            try {
                peerNode.sendMessage(new SendAddrV2Message());
                peerNode.sendMessage(new VersionAckMessage());
            } catch (Exception e) {
                peerNode.connectionClose();
                Logger.error("peer sends sendAddrV2Message or versionAckMessage error:%s", e);
                return;
            }

            Logger.info("peer node finish version handshake already. address:%s", peerNode.getAddress());
        }
    }
}
