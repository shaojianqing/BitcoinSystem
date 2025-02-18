package sjq.bitcoin.gui.model;

import sjq.bitcoin.gui.data.PeerData;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class PeerDataModel extends DefaultTableModel {

    private static final String PEER_ADDRESS = "Peer Address";

    private static final String PEER_STATUS = "Peer Status";

    private static final String CONNECTION_TIME = "Connection Time";

    private static final int PEER_COLUMN_COUNT = 3;

    private static final String[] PEER_COLUMNS = new String[3];

    private List<PeerData> peerDataList = new ArrayList<>();

    static {
        PEER_COLUMNS[0] = PEER_ADDRESS;
        PEER_COLUMNS[1] = PEER_STATUS;
        PEER_COLUMNS[2] = CONNECTION_TIME;
    }

    public void initPeerDataList(List<PeerData> peerDataList) {
        this.peerDataList = peerDataList;
    }

    public int getColumnCount() {
        return PEER_COLUMN_COUNT;
    }

    public String getColumnName(int column) {
        return PEER_COLUMNS[column];
    }

    public Object getValueAt(int row, int column) {
        PeerData peerData = peerDataList.get(row);
        if (column==0) {
            return peerData.getAddress();
        } else if (column==1) {
            return peerData.getStatus();
        } else if (column==2) {
            return peerData.getConnectionTime();
        }
        return null;
    }

    public int getRowCount() {
        if (peerDataList==null) {
            return 0;
        }
        return peerDataList.size();
    }
}
