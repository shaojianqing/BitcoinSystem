package sjq.bitcoin.network.node;

import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.VersionReqMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.network.PeerManager;
import sjq.bitcoin.network.Services;
import sjq.bitcoin.network.client.Callback;
import sjq.bitcoin.network.client.SocketClient;
import sjq.bitcoin.network.socket.SocketHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Date;

public class PeerNode implements Callback {

    public static final String CONNECTED = "Connected";

    public static final String ACKNOWLEDGE = "Acknowledge";

    private final PeerManager manager;

    private final VersionReqMessage versionReqMessage;

    private final SocketHandler socketHandler;

    private final Services requiredServices;

    private final InetSocketAddress address;

    private final SocketClient client;

    private VersionReqMessage peerVersionReqMessage;

    private Date connectionTime;

    private String status;

    public PeerNode(PeerManager manager, InetSocketAddress address, Services requiredServices, Long bestBlockHeight) throws Exception {
        this.manager = manager;
        this.address = address;
        this.requiredServices = requiredServices;
        this.socketHandler = new SocketHandler(this);
        this.versionReqMessage = prepareVersionReqMessage(bestBlockHeight);

        this.client = new SocketClient(address, this);
    }

    public boolean start() {
        try {
            if (client.isRunning()) {
                return true;
            }
            return client.openConnection();
        } catch (Exception e) {
            Logger.error("try to connect peer error:%s, address:%s", e, address);
        }
        return false;
    }

    public void sendMessage(Message message) {
        try {
            byte[] data = message.serialize();
            client.sendData(data);
            Logger.info("send %s message to peer address:%s", message.getCommand(), address);
        } catch (IOException e) {
            client.closeConnection();
        }
    }

    public void sendVersionReqMessage() {
        sendMessage(versionReqMessage);
    }

    public int receiveData(ByteBuffer buffer) {
        return socketHandler.handleSocketData(buffer);
    }

    public void processMessage(Message message) {
        manager.processPeerData(this, message);
    }

    public void connectionOpened() {
        Logger.info("peer node open connection successfully, address:%s", address);
        try {
            sendMessage(versionReqMessage);
        } catch (Exception e) {
            Logger.error("send versionReqMessage error:%s, address:%s", e, address);
        }
    }

    public void connectionClose() {
        manager.disconnectPeerNode(this);
    }

    private VersionReqMessage prepareVersionReqMessage(Long bestBlockHeight) {
        VersionReqMessage versionReqMessage = new VersionReqMessage();
        versionReqMessage.setClientVersion(Constants.VERSION_CURRENT);
        versionReqMessage.setLocalServices(Services.none());
        versionReqMessage.setReceivingAddress(this.address);
        versionReqMessage.setReceivingServices(Services.none());
        versionReqMessage.setTimestamp(System.currentTimeMillis()/1000);
        versionReqMessage.setUserAgent(Constants.SYSTEM_USER_AGENT);
        versionReqMessage.setBestBlockHeight(bestBlockHeight.intValue());
        versionReqMessage.setRelay(true);

        return versionReqMessage;
    }

    public VersionReqMessage getVersionReqMessage() {
        return versionReqMessage;
    }

    public VersionReqMessage getPeerVersionReqMessage() {
        return peerVersionReqMessage;
    }

    public void setPeerVersionReqMessage(VersionReqMessage peerVersionReqMessage) {
        this.peerVersionReqMessage = peerVersionReqMessage;
    }

    public Services getRequiredServices() {
        return requiredServices;
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(Date connectionTime) {
        this.connectionTime = connectionTime;
    }
}
