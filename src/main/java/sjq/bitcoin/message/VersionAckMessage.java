package sjq.bitcoin.message;

import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;

import java.io.IOException;

public class VersionAckMessage extends BaseMessage implements Message {

    public static String COMMAND = "verack";

    @Override
    public int messageSize() {
        return 0;
    }

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

