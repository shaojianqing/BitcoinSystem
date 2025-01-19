package sjq.bitcoin.message;

import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;

import java.io.IOException;

public class PongMessage extends BaseMessage implements Message {

    public static String COMMAND = "pong";

    public PongMessage() {
        super(COMMAND);
    }
}

