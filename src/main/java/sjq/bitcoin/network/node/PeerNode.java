package sjq.bitcoin.network.node;

import sjq.bitcoin.configuration.NetworkConfiguration;
import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.VersionReqMessage;
import sjq.bitcoin.network.PeerManager;
import sjq.bitcoin.network.Services;
import sjq.bitcoin.network.client.Callback;
import sjq.bitcoin.network.client.Client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.Instant;

public class PeerNode implements Callback {

    public static final String ACTIVE = "active";

    public static final String INACTIVE = "inactive";

    public static final String ACKNOWLEDGE = "acknowledge";

    private PeerManager manager;

    private NetworkConfiguration configuration;

    private VersionReqMessage versionReqMessage;

    private InetSocketAddress address;

    private String status;

    private Client client;

    public PeerNode(PeerManager manager, String address, int bestBlockHeight) throws IOException {
        this.manager = manager;
        this.configuration = NetworkConfiguration.getConfiguration();
        this.address = new InetSocketAddress(address, configuration.getPort());
        this.versionReqMessage = prepareVersionReqMessage(bestBlockHeight);


        this.status = INACTIVE;
        this.client = new Client(address, configuration.getPort(), this);
    }

    public void start() {
        if (client.isRunning()) {
            Logger.info("peer node is currently running, address:%s", address);
        } else {
            this.client.startConnection();
        }
    }

    public void sendMessage(Message message) throws IOException {
        byte[] data = message.serialize();
        client.sendData(data);
        Logger.info("send %s message to peer address:%s", message.getCommand(), address);
    }

    public void receiveData(byte[] data) {
        manager.processPeerData(this, data);
    }

    public void connectionOpened() {
        Logger.info("peer node start connection successfully, address:%s", address);

        try {
            sendMessage(versionReqMessage);
        } catch (Exception e) {
            Logger.error("send versionReqMessage error:%s, address:%s", e, address);
        }
    }

    public void connectionClose() {
        manager.disconnectPeerNode(this);
    }

    private VersionReqMessage prepareVersionReqMessage(int bestBlockHeight) {
        VersionReqMessage versionReqMessage = new VersionReqMessage();
        versionReqMessage.setClientVersion(Constants.VERSION_CURRENT);
        versionReqMessage.setLocalServices(Services.none());
        versionReqMessage.setReceivingAddress(this.address);
        versionReqMessage.setReceivingServices(Services.none());
        versionReqMessage.setTimestamp(Instant.now().getEpochSecond());
        versionReqMessage.setUserAgent(Constants.SYSTEM_USER_AGENT);
        versionReqMessage.setBestBlockHeight(bestBlockHeight);
        versionReqMessage.setRelay(true);

        return versionReqMessage;
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }
}
