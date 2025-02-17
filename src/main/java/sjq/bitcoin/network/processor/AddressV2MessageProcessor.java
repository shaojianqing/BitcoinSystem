package sjq.bitcoin.network.processor;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.AddressV2Message;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.data.NetworkAddress;
import sjq.bitcoin.network.PeerDiscovery;
import sjq.bitcoin.network.PeerProcessor;
import sjq.bitcoin.network.node.PeerNode;

import java.util.List;

public class AddressV2MessageProcessor implements PeerProcessor {

    @Autowire
    private PeerDiscovery peerDiscovery;


    public void processMessage(PeerNode peerNode, Message message) {
        if (message instanceof AddressV2Message) {
            AddressV2Message addressMessage = (AddressV2Message)message;
            Logger.info("received AddressV2Message, with address count:%d", addressMessage.getAddressList().size());
            List<NetworkAddress> addressList = addressMessage.getAddressList();
            peerDiscovery.sync(addressList);
        }
    }
}
