package sjq.bitcoin.network.processor;

import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.GetHeadersMessage;
import sjq.bitcoin.message.HeadersMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.network.PeerProcessor;
import sjq.bitcoin.network.node.PeerNode;

public class GetHeadersMessageProcessor implements PeerProcessor {

    private Blockchain blockchain;

    public GetHeadersMessageProcessor(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    public void processMessage(PeerNode peerNode, Message message) {
        if (message instanceof GetHeadersMessage) {
            GetHeadersMessage getHeadersMessage = (GetHeadersMessage) message;
            Logger.info("received getHeadersMessage!");
        }
    }
}
