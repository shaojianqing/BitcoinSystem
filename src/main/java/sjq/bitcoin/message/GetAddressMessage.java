package sjq.bitcoin.message;

import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;

import java.io.IOException;

public class GetAddressMessage extends BaseMessage implements Message {

    public static String COMMAND = "getaddr";

    @Override
    protected byte[] serializeMessage() throws IOException {
        return new byte[0];
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }

    @Override
    public void deserialize(byte[] data) throws Exception {

    }
}
