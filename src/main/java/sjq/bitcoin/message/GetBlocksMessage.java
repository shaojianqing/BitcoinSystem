package sjq.bitcoin.message;

import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;

import java.io.IOException;

public class GetBlocksMessage extends BaseMessage implements Message {

    public static String COMMAND = "getblocks";

    public GetBlocksMessage() {
        super(COMMAND);
    }
}
