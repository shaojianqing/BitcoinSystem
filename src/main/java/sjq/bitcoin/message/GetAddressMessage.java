package sjq.bitcoin.message;

import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;

public class GetAddressMessage extends BaseMessage implements Message {

    public static String COMMAND = "getaddr";

    public String getCommand() {
        return COMMAND;
    }
}
