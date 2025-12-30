package sjq.bitcoin.network;

import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.*;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.processor.*;
import sjq.bitcoin.network.node.PeerNode;

import java.util.HashMap;
import java.util.Map;

public class PeerHandler {

    private final Map<String, PeerProcessor> processorMap;

    @Autowire
    private PeerManager peerManager;

    @Autowire
    private PeerDiscovery peerDiscovery;

    @Autowire
    private Blockchain blockchain;

    @Autowire
    private AddressV1MessageProcessor addressV1MessageProcessor;

    @Autowire
    private AddressV2MessageProcessor addressV2MessageProcessor;

    @Autowire
    private BlockMessageProcessor blockMessageProcessor;

    @Autowire
    private BloomFilterMessageProcessor bloomFilterMessageProcessor;

    @Autowire
    private FeeFilterMessageProcessor feeFilterMessageProcessor;

    @Autowire
    private FilteredBlockMessageProcessor filteredBlockMessageProcessor;

    @Autowire
    private GetAddressMessageProcessor getAddressMessageProcessor;

    @Autowire
    private GetBlocksMessageProcessor getBlocksMessageProcessor;

    @Autowire
    private GetDataMessageProcessor getDataMessageProcessor;

    @Autowire
    private GetHeadersMessageProcessor getHeadersMessageProcessor;

    @Autowire
    private HeadersMessageProcessor headersMessageProcessor;

    @Autowire
    private InventoryMessageProcessor inventoryMessageProcessor;

    @Autowire
    private MemoryPoolMessageProcessor memoryPoolMessageProcessor;

    @Autowire
    private NotFoundMessageProcessor notFoundMessageProcessor;

    @Autowire
    private PingMessageProcessor pingMessageProcessor;

    @Autowire
    private PongMessageProcessor pongMessageProcessor;

    @Autowire
    private RejectMessageProcessor rejectMessageProcessor;

    @Autowire
    private SendAddrV2MessageProcessor sendAddrV2MessageProcessor;

    @Autowire
    private SendHeadersMessageProcessor sendHeadersMessageProcessor;

    @Autowire
    private UnknownMessageProcessor unknownMessageProcessor;

    @Autowire
    private TransactionMessageProcessor transactionMessageProcessor;

    @Autowire
    private VersionAckMessageProcessor versionAckMessageProcessor;

    @Autowire
    private VersionReqMessageProcessor versionReqMessageProcessor;

    public PeerHandler() {
        processorMap = new HashMap<>();
    }

    public void initialize() {
        processorMap.put(AddressV1Message.COMMAND, addressV1MessageProcessor);
        processorMap.put(AddressV2Message.COMMAND, addressV2MessageProcessor);
        processorMap.put(BlockMessage.COMMAND, blockMessageProcessor);
        processorMap.put(BloomFilterMessage.COMMAND, bloomFilterMessageProcessor);
        processorMap.put(FeeFilterMessage.COMMAND, feeFilterMessageProcessor);
        processorMap.put(FilteredBlockMessage.COMMAND, filteredBlockMessageProcessor);
        processorMap.put(GetAddressMessage.COMMAND, getAddressMessageProcessor);
        processorMap.put(GetBlocksMessage.COMMAND, getBlocksMessageProcessor);
        processorMap.put(GetDataMessage.COMMAND, getDataMessageProcessor);
        processorMap.put(GetHeadersMessage.COMMAND, getHeadersMessageProcessor);
        processorMap.put(HeadersMessage.COMMAND, headersMessageProcessor);
        processorMap.put(InventoryMessage.COMMAND, inventoryMessageProcessor);
        processorMap.put(MemoryPoolMessage.COMMAND, memoryPoolMessageProcessor);
        processorMap.put(NotFoundMessage.COMMAND, notFoundMessageProcessor);
        processorMap.put(PingMessage.COMMAND, pingMessageProcessor);
        processorMap.put(PongMessage.COMMAND, pongMessageProcessor);
        processorMap.put(RejectMessage.COMMAND, rejectMessageProcessor);
        processorMap.put(SendAddrV2Message.COMMAND, sendAddrV2MessageProcessor);
        processorMap.put(SendHeadersMessage.COMMAND, sendHeadersMessageProcessor);
        processorMap.put(UnknownMessage.COMMAND, unknownMessageProcessor);
        processorMap.put(TransactionMessage.COMMAND, transactionMessageProcessor);
        processorMap.put(VersionAckMessage.COMMAND, versionAckMessageProcessor);
        processorMap.put(VersionReqMessage.COMMAND, versionReqMessageProcessor);
    }

    public void handlePeerData(PeerNode node, Message message) {
        try {
            Logger.info("received %s message from peer address:%s", message.getCommand(), node.getAddress());
            PeerProcessor processor = processorMap.get(message.getCommand());
            processor.processMessage(node, message);
            Logger.info("finish processing %s message from peer address:%s", message.getCommand(), node.getAddress());
        } catch (Exception e) {
            Logger.error("processing peer message error:%s, peer address:%s", e, node.getAddress());
        }
    }
}
