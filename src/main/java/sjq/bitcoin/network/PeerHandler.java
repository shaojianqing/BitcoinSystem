package sjq.bitcoin.network;

import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.*;
import sjq.bitcoin.network.node.PeerNode;
import sjq.bitcoin.network.packet.BitcoinPacket;
import sjq.bitcoin.network.processor.*;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class PeerHandler {

    private static PeerHandler Instance;

    private Map<String, PeerProcessor> processorMap = new HashMap();

    private PeerManager peerManager;

    private Blockchain blockchain;

    private PeerHandler() {
    }

    public static PeerHandler build() {
        if (Instance == null) {
            Instance = new PeerHandler();
        }
        return Instance;
    }

    public void initialize() {
        processorMap.put(AddressV1Message.COMMAND, new AddressV1MessageProcessor());
        processorMap.put(AddressV2Message.COMMAND, new AddressV2MessageProcessor());
        processorMap.put(BlockMessage.COMMAND, new BlockMessageProcessor());
        processorMap.put(BloomFilterMessage.COMMAND, new BloomFilterMessageProcessor());
        processorMap.put(FeeFilterMessage.COMMAND, new FeeFilterMessageProcessor());
        processorMap.put(FilteredBlockMessage.COMMAND, new FilteredBlockMessageProcessor());
        processorMap.put(GetAddressMessage.COMMAND, new GetAddressMessageProcessor());
        processorMap.put(GetBlocksMessage.COMMAND, new GetBlocksMessageProcessor());
        processorMap.put(GetDataMessage.COMMAND, new GetDataMessageProcessor());
        processorMap.put(GetHeadersMessage.COMMAND, new GetHeadersMessageProcessor());
        processorMap.put(HeadersMessage.COMMAND, new HeadersMessageProcessor());
        processorMap.put(InventoryMessage.COMMAND, new InventoryMessageProcessor());
        processorMap.put(MemoryPoolMessage.COMMAND, new MemoryPoolMessageProcessor());
        processorMap.put(NotFoundMessage.COMMAND, new NotFoundMessageProcessor());
        processorMap.put(PingMessage.COMMAND, new PingMessageProcessor());
        processorMap.put(PongMessage.COMMAND, new PongMessageProcessor());
        processorMap.put(RejectMessage.COMMAND, new RejectMessageProcessor());
        processorMap.put(SendAddrV2Message.COMMAND, new SendAddrV2MessageProcessor());
        processorMap.put(SendHeadersMessage.COMMAND, new SendHeadersMessageProcessor());
        processorMap.put(UnknownMessage.COMMAND, new UnknownMessageProcessor());
        processorMap.put(TransactionMessage.COMMAND, new TransactionMessageProcessor());
        processorMap.put(VersionAckMessage.COMMAND, new VersionAckMessageProcessor());
        processorMap.put(VersionReqMessage.COMMAND, new VersionReqMessageProcessor());
    }

    public void handlePeerData(PeerNode node, byte[] data) {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(data);
            BitcoinPacket packet = BitcoinPacket.parse(buffer);
            Logger.info("received %s message from peer address:%s", packet.getCommand(), node.getAddress());

            PeerProcessor processor = processorMap.get(packet.getCommand());
            processor.processMessage(node, packet.getMessage());
            Logger.info("finish processing %s message from peer address:%s", packet.getCommand(), node.getAddress());
        } catch (Exception e) {
            Logger.error("processing peer message error:%s, peer address:%s", e, node.getAddress());
        }
    }

    public void setPeerManager(PeerManager peerManager) {
        this.peerManager = peerManager;
    }

    public void setBlockchain(Blockchain blockchain) {
        this.blockchain = blockchain;
    }
}
