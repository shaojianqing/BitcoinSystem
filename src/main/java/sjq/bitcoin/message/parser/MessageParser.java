package sjq.bitcoin.message.parser;

import sjq.bitcoin.message.*;
import sjq.bitcoin.message.base.Message;

import java.util.HashMap;
import java.util.Map;

public class MessageParser {

    private static Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();

    static {
        classMap.put(AddressV1Message.COMMAND, AddressV1Message.class);
        classMap.put(AddressV2Message.COMMAND, AddressV2Message.class);
        classMap.put(BlockMessage.COMMAND, BlockMessage.class);
        classMap.put(BloomFilterMessage.COMMAND, BloomFilterMessage.class);
        classMap.put(FeeFilterMessage.COMMAND, FeeFilterMessage.class);
        classMap.put(FilteredBlockMessage.COMMAND, FilteredBlockMessage.class);
        classMap.put(GetAddressMessage.COMMAND, GetAddressMessage.class);
        classMap.put(GetBlocksMessage.COMMAND, GetBlocksMessage.class);
        classMap.put(GetDataMessage.COMMAND, GetDataMessage.class);
        classMap.put(GetHeadersMessage.COMMAND, GetHeadersMessage.class);
        classMap.put(HeadersMessage.COMMAND, HeadersMessage.class);
        classMap.put(InventoryMessage.COMMAND, InventoryMessage.class);
        classMap.put(MemoryPoolMessage.COMMAND, MemoryPoolMessage.class);
        classMap.put(NotFoundMessage.COMMAND, NotFoundMessage.class);
        classMap.put(PingMessage.COMMAND, PingMessage.class);
        classMap.put(PongMessage.COMMAND, PongMessage.class);
        classMap.put(RejectMessage.COMMAND, RejectMessage.class);
        classMap.put(SendAddrV2Message.COMMAND, SendAddrV2Message.class);
        classMap.put(SendHeadersMessage.COMMAND, SendHeadersMessage.class);
        classMap.put(UnknownMessage.COMMAND, UnknownMessage.class);
        classMap.put(TransactionMessage.COMMAND, TransactionMessage.class);
        classMap.put(VersionAckMessage.COMMAND, VersionAckMessage.class);
        classMap.put(VersionReqMessage.COMMAND, VersionReqMessage.class);
    }

    public static Message newMessageInstance(String command) throws Exception {
        Class clazz = classMap.get(command);
        return (Message)clazz.getDeclaredConstructor().newInstance();
    }
}
