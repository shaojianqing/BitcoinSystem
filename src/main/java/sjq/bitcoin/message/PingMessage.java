package sjq.bitcoin.message;

import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;

import java.io.IOException;

public class PingMessage extends BaseMessage implements Message {

    public static String COMMAND = "ping";

    public String getCommand() {
        return COMMAND;
    }
}

