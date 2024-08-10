package sjq.bitcoin.network.node;

import sjq.bitcoin.network.PeerManager;
import sjq.bitcoin.network.client.Callback;
import sjq.bitcoin.network.client.Client;

import java.io.IOException;

public class PeerNode implements Callback {

    public static final int PORT = 8333;

    public static final String ACTIVE = "active";

    public static final String INACTIVE = "inactive";

    private PeerManager manager;

    private String address;

    private String status;

    private Client client;

    public PeerNode(PeerManager manager, String address) throws IOException {
        this.manager = manager;
        this.address = address;

        this.status = INACTIVE;
        this.client = new Client(address, PORT, this);
    }

    public void start() throws IOException {
        this.client.startConnection();
        this.status = ACTIVE;
    }

    public void sendData(byte[] data) throws IOException {
        client.sendData(data);
    }

    public void receiveData(byte[] data) {
        manager.processPeerData(data);
    }

    public void connectionClose() {
        manager.disconnectPeerNode(this);
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }
}
