package sjq.bitcoin.network.client;

import sjq.bitcoin.utility.ThreadUtils;

import javax.net.SocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client implements Runnable {

    private static final int CONNECTION_TIMEOUT = 30000;

    private static final int BUFFER_SIZE = 1024*1024*2;

    private byte[] buffer = new byte[BUFFER_SIZE];

    private Callback callback;

    private Socket socket;

    private InetSocketAddress address;

    private boolean running = false;

    public Client(String host, int port, Callback clientCallback) throws IOException {
        callback = clientCallback;
        address = new InetSocketAddress(host, port);
        socket = SocketFactory.getDefault().createSocket();
    }

    public void startConnection() throws IOException {
        if (socket.isConnected()) {
            return;
        }

        socket.connect(address, CONNECTION_TIMEOUT);
        String threadName = String.format("Peer node client thread(%s:%d)",
                address.getHostName(),address.getPort());
        ThreadUtils.run(this, threadName);
    }

    public void sendData(byte[] data) throws IOException {
        OutputStream stream = socket.getOutputStream();
        stream.write(data);
        stream.flush();
    }

    public void run() {
        running = true;
        while(running) {
            try {
                InputStream stream = socket.getInputStream();
                int length = stream.read(buffer);
                byte[] data = new byte[length];
                System.arraycopy(buffer,0, data, 0,length);
                callback.receiveData(data);
            } catch (IOException e) {
                running = false;
                callback.connectionClose();
            }
        }
    }
}
