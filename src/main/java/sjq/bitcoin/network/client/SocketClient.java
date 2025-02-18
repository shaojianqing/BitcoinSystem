package sjq.bitcoin.network.client;

import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.utility.ThreadUtils;

import javax.net.SocketFactory;
import java.net.InetSocketAddress;
import java.net.Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;


public class SocketClient implements Runnable {

    private static final int CONNECTION_TIMEOUT = 30000;

    private static final int BUFFER_SIZE_LOWER_BOUND = 4096;

    private static final int BUFFER_SIZE_UPPER_BOUND = 65536;

    private static final int MAX_MESSAGE_SIZE = 0x02000000;

    private final Callback callback;

    private final Socket socket;

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
        try {
            if (socket.isConnected()) {
                return true;
            }
            socket.connect(address, CONNECTION_TIMEOUT);
            inputstream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            running.compareAndSet(false, true);
            callback.connectionOpened();
            String threadName = String.format("Peer node client thread(%s:%d)", address.getAddress(), address.getPort());
            ThreadUtils.run(this, threadName);
            Logger.info("connect to peer successfully, remote peer address:%s, port:%d", address.getAddress(), address.getPort());
        } catch (Exception e) {
            running.compareAndSet(true, false);
            callback.connectionClose();
            Logger.error("connect to peer failure, remote peer address:%s, port:%d, error:%s", address.getAddress(), address.getPort(), e);
        }
        return running.get();
    }

    public void sendData(byte[] data) throws IOException {
        outputStream.write(data);
        outputStream.flush();
    }

    public void run() {
        try {
            ByteBuffer dataBuf = ByteBuffer.allocateDirect(Math.min(Math.max(MAX_MESSAGE_SIZE, BUFFER_SIZE_LOWER_BOUND), BUFFER_SIZE_UPPER_BOUND));
            byte[] readBuff = new byte[dataBuf.capacity()];
            while (true) {
                boolean bufferCheck = (dataBuf.remaining() > 0 && dataBuf.remaining() <= readBuff.length);
                if (!bufferCheck) {
                    throw new IllegalStateException();
                }

                int read = inputstream.read(readBuff, 0, Math.max(1, Math.min(dataBuf.remaining(), inputstream.available())));
                if (read == -1) {
                    return;
                }
                dataBuf.put(readBuff, 0, read);
                dataBuf.flip();
                int bytesConsumed = callback.receiveData(dataBuf);

                if (dataBuf.position() != bytesConsumed) {
                    throw new IllegalStateException();
                }
                dataBuf.compact();
            }
        } catch (Exception e) {
            Logger.error("client receive bytes from peer error:%s, address:%s", e, address);
        } finally {
            try {
                outputStream.close();
                inputstream.close();
                socket.close();
            } catch (IOException e1) {
                Logger.error("client socket close error:%s, address:%s", e1, address);
            } finally {
                callback.connectionClose();
            }
        }
    }

    public void closeConnection() {
        try {
            if (running.compareAndSet(true, false)) {
                outputStream.close();
                inputstream.close();
                socket.close();
            }
        } catch (IOException e) {
            Logger.error("client socket close error:%s, address:%s", e, address);
        } finally {
            callback.connectionClose();
        }
    }

    public boolean isRunning() {
        return running.get();
    }
}
