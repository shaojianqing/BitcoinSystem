package sjq.bitcoin.message;

import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;

import java.io.IOException;

public class SendAddrV2Message extends BaseMessage implements Message {

    public static String COMMAND = "sendaddrv2";

    public SendAddrV2Message() {
        super(COMMAND);
    }
}