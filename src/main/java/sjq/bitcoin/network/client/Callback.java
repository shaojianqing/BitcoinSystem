package sjq.bitcoin.network.client;

public interface Callback {

    void receiveData(byte[] data);

    void connectionOpened();

    void connectionClose();
}
