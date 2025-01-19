package sjq.bitcoin.message;

import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;

import java.io.IOException;

public class NotFoundMessage extends BaseMessage implements Message {

    public static String COMMAND = "notfound";

    public NotFoundMessage() {
        super(COMMAND);
    }
}