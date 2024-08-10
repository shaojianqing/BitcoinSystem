package sjq.bitcoin.network;

import sjq.bitcoin.message.Message;

public interface PeerProcessor {

    void processMessage(Message message);
}
