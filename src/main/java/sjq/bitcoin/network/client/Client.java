package sjq.bitcoin.network.client;

import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.utility.ThreadUtils;

import javax.net.SocketFactory;
import java.net.InetSocketAddress;
import java.net.Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicBoolean;


public class Client implements Runnable {

    private static final int CONNECTION_TIMEOUT = 30000;

    private static final int BUFFER_SIZE = 1024*1024*2;

    private byte[] buffer = new byte[BUFFER_SIZE];

    private Callback callback;

    private Socket socket;

    private InetSocketAddress address;

    private AtomicBoolean running = new AtomicBoolean(false);

    public Client(String host, int port, Callback callback) throws IOException {
        this.callback = callback;
        this.address = new InetSocketAddress(host, port);
        this.socket = SocketFactory.getDefault().createSocket();
    }

    public void startConnection() {
        try {
            if (socket.isConnected()) {
                return;
            }

            socket.connect(address, CONNECTION_TIMEOUT);
            running.compareAndSet(false, true);
            String threadName = String.format("Peer node client thread(%s:%d)", address.getHostName(), address.getPort());
            ThreadUtils.run(this, threadName);
            Logger.info("connect to peer successfully, remote peer address:%s, port:%d", address.getAddress(), address.getPort());
            callback.connectionOpened();
        } catch (Exception e) {
            running.compareAndSet(true, false);
            callback.connectionClose();
            Logger.error("connect to peer failure, remote peer address:%s, port:%d, error:%s", address.getAddress(), address.getPort(), e);
        }
    }

    public void sendData(byte[] data) throws IOException {
        OutputStream stream = socket.getOutputStream();
        stream.write(data);
        stream.flush();
    }

    public void run() {
        while(running.get()) {
            try {
                InputStream stream = socket.getInputStream();
                int length = stream.read(buffer);
                if (length<=0) {
                    continue;
                }
                byte[] data = new byte[length];
                System.arraycopy(buffer,0, data, 0,length);
                callback.receiveData(data);
            } catch (Exception e) {
                running.compareAndSet(true, false);
                callback.connectionClose();
                Logger.error("receive data from peer error:%s", e);
            }
        }
    }

    public boolean isRunning() {
        return running.get();
    }
}
