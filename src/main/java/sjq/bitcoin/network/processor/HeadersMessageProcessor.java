package sjq.bitcoin.network.processor;

import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.HeadersMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.data.BlockHeader;
import sjq.bitcoin.network.PeerProcessor;
import sjq.bitcoin.network.node.PeerNode;

import java.util.List;

public class HeadersMessageProcessor implements PeerProcessor {

    @Autowire
    private Blockchain blockchain;

    public void processMessage(PeerNode peerNode, Message message) {
        if (message instanceof HeadersMessage headersMessage) {
            Logger.info("received block headers message with headerCount:%d", headersMessage.getHeaderCount());
            List<BlockHeader> blockHeaderList = headersMessage.getHeaderList();

            // Here we take header first strategy, so block data is persisted with block header directly.
            // The related transaction data would be persisted in later async thread or task.
            blockchain.persistBlockHeader(blockHeaderList);
        }
    }
}
