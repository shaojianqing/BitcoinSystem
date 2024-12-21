package sjq.bitcoin.network.client;

import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.utility.ThreadUtils;

import javax.net.SocketFactory;
import java.net.InetSocketAddress;
import java.net.Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;


public class SocketClient implements Runnable, Client {

    private static final int CONNECTION_TIMEOUT = 30000;

    private static final int BUFFER_SIZE_LOWER_BOUND = 4096;

    private static final int BUFFER_SIZE_UPPER_BOUND = 65536;

    private static final int MAX_MESSAGE_SIZE = 0x02000000;

    private Callback callback;

    private Socket socket;

    private InputStream inputstream;

    private OutputStream outputStream;

    private InetSocketAddress address;

    private AtomicBoolean running = new AtomicBoolean(false);

    public SocketClient(InetSocketAddress address, Callback callback) throws IOException {
        this.callback = callback;
        this.address = address;
        this.socket = SocketFactory.getDefault().createSocket();
    }

    public boolean openConnection() {
        boolean success;
        try {
            if (socket.isConnected()) {
                return true;
            }

            socket.connect(address, CONNECTION_TIMEOUT);
            inputstream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            running.compareAndSet(false, true);
            String threadName = String.format("Peer node client thread(%s:%d)", address.getHostName(), address.getPort());
            ThreadUtils.run(this, threadName);
            Logger.info("connect to peer successfully, remote peer address:%s, port:%d", address.getAddress(), address.getPort());
            callback.connectionOpened();
            success = true;
        } catch (Exception e) {
            running.compareAndSet(true, false);
            callback.connectionClose();
            Logger.error("connect to peer failure, remote peer address:%s, port:%d, error:%s", address.getAddress(), address.getPort(), e);
            success = false;
        }
        return success;
    }

    public void sendData(byte[] data) throws IOException {
        outputStream.write(data);
        outputStream.flush();
    }

    public void run() {

        try {
            ByteBuffer dbuf = ByteBuffer.allocateDirect(Math.min(Math.max(MAX_MESSAGE_SIZE, BUFFER_SIZE_LOWER_BOUND), BUFFER_SIZE_UPPER_BOUND));
            byte[] readBuff = new byte[dbuf.capacity()];
            while (true) {
                boolean bufferCheck = (dbuf.remaining() > 0 && dbuf.remaining() <= readBuff.length);
                if (!bufferCheck) {
                    throw new IllegalStateException();
                }

                int read = inputstream.read(readBuff, 0, Math.max(1, Math.min(dbuf.remaining(), inputstream.available())));
                if (read == -1) {
                    return;
                }
                dbuf.put(readBuff, 0, read);
                ((Buffer) dbuf).flip();
                int bytesConsumed = callback.receiveData(dbuf);

                if (dbuf.position() != bytesConsumed) {
                    throw new IllegalStateException();
                }
                dbuf.compact();
            }

        } catch (Exception e) {
            Logger.error("client receive bytes from peer error:%s, address:%s", e, address);
        } finally {
            try {
                socket.close();
            } catch (IOException e1) {
            }
            callback.connectionClose();
        }
    }

    public boolean isRunning() {
        return running.get();
    }
}
