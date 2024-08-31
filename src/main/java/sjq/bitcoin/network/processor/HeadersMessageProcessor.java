package sjq.bitcoin.network.processor;

import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.HeadersMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.data.BlockHeader;
import sjq.bitcoin.network.PeerProcessor;
import sjq.bitcoin.network.node.PeerNode;

public class HeadersMessageProcessor implements PeerProcessor {

    private Blockchain blockchain;

    public HeadersMessageProcessor(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    public void processMessage(PeerNode peerNode, Message message) {
        if (message instanceof HeadersMessage) {
            HeadersMessage headersMessage = (HeadersMessage)message;
            Logger.info("received headersMessage, headerCount:%d", headersMessage.getHeaderCount());
            for (int i=0; i< headersMessage.getHeaderList().size(); ++i) {
                BlockHeader header = headersMessage.getHeaderList().get(i);
                //Logger.debug("received header hash:%s, merkle root:%s, parent hash:%s",
                        //header.getBlockHash(), header.getMerkleRoot(), header.getParentHash());
            }
        }
    }
}
