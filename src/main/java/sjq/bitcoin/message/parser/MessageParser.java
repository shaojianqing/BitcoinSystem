package sjq.bitcoin.message.parser;

import sjq.bitcoin.message.*;

import java.util.HashMap;
import java.util.Map;

public class MessageParser {

    private static Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();

    static {
        classMap.put(AddressMessage.COMMAND, AddressMessage.class);
        classMap.put(BlockMessage.COMMAND, BlockMessage.class);
        classMap.put(GetBlocksMessage.COMMAND, GetBlocksMessage.class);
        classMap.put(GetDataMessage.COMMAND, GetDataMessage.class);
        classMap.put(GetHeadersMessage.COMMAND, GetHeadersMessage.class);
        classMap.put(PingMessage.COMMAND, PingMessage.class);
        classMap.put(PongMessage.COMMAND, PongMessage.class);
        classMap.put(RejectMessage.COMMAND, RejectMessage.class);
        classMap.put(TransactionMessage.COMMAND, TransactionMessage.class);
        classMap.put(VersionAckMessage.COMMAND, VersionAckMessage.class);
        classMap.put(VersionReqMessage.COMMAND, VersionReqMessage.class);
    }

    public static Message newMessageInstance(String command) throws Exception {
        Class clazz = classMap.get(command);
        return (Message)clazz.getDeclaredConstructor().newInstance();
    }
}
