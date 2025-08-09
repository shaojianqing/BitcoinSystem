package sjq.bitcoin.message.processor;

import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.BlockMessage;
import sjq.bitcoin.message.TransactionMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.network.PeerProcessor;
import sjq.bitcoin.network.node.PeerNode;

import java.util.List;

public class BlockMessageProcessor implements PeerProcessor {

    @Autowire
    private Blockchain blockchain;

    public void processMessage(PeerNode peerNode, Message message) {
        if (message instanceof BlockMessage blockMessage) {
            List<TransactionMessage> transactions = blockMessage.getTransactions();
            Logger.info("received blockMessage:%s, with transaction count:%d!",
                    blockMessage.getBlockHash(), transactions.size());
            blockchain.persistBlockBody(blockMessage);
        }
    }
}

