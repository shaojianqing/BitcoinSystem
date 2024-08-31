package sjq.bitcoin.message.base;

import java.io.IOException;

public interface Message {

    String getCommand();

    byte[] serialize() throws IOException;

    void deserializeMessage(byte[] data) throws Exception;
}
