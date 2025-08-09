package sjq.bitcoin.message.processor;

import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.GetHeadersMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.network.PeerProcessor;
import sjq.bitcoin.network.node.PeerNode;

public class GetHeadersMessageProcessor implements PeerProcessor {

    @Autowire
    private Blockchain blockchain;

    public void processMessage(PeerNode peerNode, Message message) {
        if (message instanceof GetHeadersMessage) {
            GetHeadersMessage getHeadersMessage = (GetHeadersMessage) message;
            Logger.info("received getHeadersMessage:%s!", getHeadersMessage);
        }
    }
}
