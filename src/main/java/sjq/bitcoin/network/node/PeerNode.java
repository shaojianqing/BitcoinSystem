package sjq.bitcoin.network.node;

import sjq.bitcoin.configuration.NetworkConfiguration;
import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.VersionReqMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.network.PeerManager;
import sjq.bitcoin.network.Services;
import sjq.bitcoin.network.client.Callback;
import sjq.bitcoin.network.client.SocketClient;
import sjq.bitcoin.network.socket.SocketHandler;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

public class PeerNode implements Callback {

    public static final String ACTIVE = "active";

    public static final String INACTIVE = "inactive";

    public static final String ACKNOWLEDGE = "acknowledge";

    private PeerManager manager;

    private NetworkConfiguration configuration;

    private VersionReqMessage versionReqMessage;

    private VersionReqMessage peerVersionReqMessage;

    private SocketHandler socketHandler;

    private Services requiredServices;

    private InetSocketAddress address;

    private String status;

    private SocketClient client;

    public PeerNode(PeerManager manager, String url, Services requiredServices, Long bestBlockHeight) throws Exception {
        this.manager = manager;
        this.requiredServices = requiredServices;
        this.configuration = NetworkConfiguration.getConfiguration();
        this.address = new InetSocketAddress(url, configuration.getPort());
        this.socketHandler = new SocketHandler(this);
        this.versionReqMessage = prepareVersionReqMessage(bestBlockHeight);

        this.status = INACTIVE;
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

    public void sendMessage(Message message) throws Exception {
        byte[] data = message.serialize();
        client.sendData(data);
        Logger.info("send %s message to peer address:%s", message.getCommand(), address);
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

    public String getIPAddress() {
        return address.getAddress().getHostAddress();
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
}
