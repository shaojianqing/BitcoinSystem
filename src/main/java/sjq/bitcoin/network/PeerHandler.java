package sjq.bitcoin.network;

import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.message.*;
import sjq.bitcoin.network.packet.BitcoinPacket;
import sjq.bitcoin.network.processor.*;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class PeerHandler {

    private Map<String, PeerProcessor> processorMap = new HashMap();

    private Blockchain blockchain;

    public void initialize() {
        processorMap.put(AddressMessage.COMMAND, new AddressMessageProcessor());
        processorMap.put(BlockMessage.COMMAND, new BlockMessageProcessor());
        processorMap.put(GetBlocksMessage.COMMAND, new GetBlocksMessageProcessor());
        processorMap.put(GetDataMessage.COMMAND, new GetDataMessageProcessor());
        processorMap.put(GetHeadersMessage.COMMAND, new GetHeadersMessageProcessor());
        processorMap.put(PingMessage.COMMAND, new PingMessageProcessor());
        processorMap.put(PongMessage.COMMAND, new PongMessageProcessor());
        processorMap.put(RejectMessage.COMMAND, new RejectMessageProcessor());
        processorMap.put(TransactionMessage.COMMAND, new TransactionMessageProcessor());
        processorMap.put(VersionAckMessage.COMMAND, new VersionAckMessageProcessor());
        processorMap.put(VersionReqMessage.COMMAND, new VersionReqMessageProcessor());
    }

    public void handlePeerData(byte[] data) {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(data);
            BitcoinPacket packet = BitcoinPacket.parse(buffer);

            if (!processorMap.containsKey(packet.getCommand())) {

            }

            PeerProcessor processor = processorMap.get(packet.getCommand());
            processor.processMessage(packet.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
