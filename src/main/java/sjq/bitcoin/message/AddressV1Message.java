package sjq.bitcoin.message;

import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;

import java.io.IOException;

public class AddressV1Message extends BaseMessage implements Message {

    public static String COMMAND = "addr";

    @Override
    protected byte[] serializeMessage() throws IOException {
        return new byte[0];
    }

    @Override
    public void deserialize(byte[] data) throws Exception {

    }

    @Override
    public String getCommand() {
        return COMMAND;
    }
}
