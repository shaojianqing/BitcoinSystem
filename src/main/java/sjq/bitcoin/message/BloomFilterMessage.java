package sjq.bitcoin.message;

import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;

import java.io.IOException;

public class BloomFilterMessage extends BaseMessage implements Message {

    public static String COMMAND = "filterload";

    public String getCommand() {
        return COMMAND;
    }
}