package sjq.bitcoin.network.client;

import java.io.IOException;

public interface Client {

    boolean isRunning();

    boolean openConnection();

    void sendData(byte[] data) throws IOException;
}
