package sjq.bitcoin.network.processor;

import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.TransactionMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.network.PeerProcessor;
import sjq.bitcoin.network.node.PeerNode;

public class TransactionMessageProcessor implements PeerProcessor {

    @Autowire
    private Blockchain blockchain;

    public void processMessage(PeerNode peerNode, Message message) {
        if (message instanceof TransactionMessage) {
            TransactionMessage transactionMessage = (TransactionMessage)message;
            Logger.info("receiving transactionMessage:%s", transactionMessage);
            blockchain.processTransaction(transactionMessage);
        }
    }
}