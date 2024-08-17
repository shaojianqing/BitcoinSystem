package sjq.bitcoin.message.base;

import java.io.IOException;

public interface Message {

    String getCommand();

    int messageSize();

    byte[] serialize() throws IOException;

    void deserialize(byte[] data);
}
