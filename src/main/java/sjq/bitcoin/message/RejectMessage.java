package sjq.bitcoin.message;

import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;

import java.io.IOException;

public class RejectMessage extends BaseMessage implements Message {

    public static String COMMAND = "reject";

    public RejectMessage() {
        super(COMMAND);
    }
}
