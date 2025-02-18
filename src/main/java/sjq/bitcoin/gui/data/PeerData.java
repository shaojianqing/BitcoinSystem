package sjq.bitcoin.gui.data;

public class PeerData {

    private String address;

    private String status;

    private String connectionTime;

    public PeerData(String address, String status, String connectionTime) {
        this.address = address;
        this.status = status;
        this.connectionTime = connectionTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(String connectionTime) {
        this.connectionTime = connectionTime;
    }
}
