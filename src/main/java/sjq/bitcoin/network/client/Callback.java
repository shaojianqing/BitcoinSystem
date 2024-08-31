package sjq.bitcoin.network.client;

import java.nio.ByteBuffer;

public interface Callback {

    int receiveData(ByteBuffer buffer);

    void connectionOpened();

    void connectionClose();
}
