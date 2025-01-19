package sjq.bitcoin.message;

import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.data.NetworkAddress;

import java.io.IOException;

public class FeeFilterMessage extends BaseMessage implements Message {

    public static String COMMAND = "feefilter";

    public FeeFilterMessage() {
        super(COMMAND);
    }
}