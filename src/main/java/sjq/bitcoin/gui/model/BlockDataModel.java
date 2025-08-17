package sjq.bitcoin.gui.model;

import sjq.bitcoin.gui.data.BlockData;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class BlockDataModel extends DefaultTableModel {

    private static final int BLOCK_COLUMN_COUNT = 6;

    private static final String BLOCK_HEIGHT = "Block Height";

    private static final String BLOCK_HASH = "Block Hash";

    private static final String BLOCK_NONCE = "Nonce";

    private static final String SYNC_STATUS = "Sync Status";

    private static final String VERIFICATION_STATUS = "Verification Status";

    private static final String BLOCK_TIMESTAMP = "Block Timestamp";

    private static final String[] BLOCK_COLUMNS = new String[BLOCK_COLUMN_COUNT];

    private List<BlockData> blockDataList = new ArrayList<>();

    static {
        BLOCK_COLUMNS[0] = BLOCK_HEIGHT;
        BLOCK_COLUMNS[1] = BLOCK_HASH;
        BLOCK_COLUMNS[2] = BLOCK_NONCE;
        BLOCK_COLUMNS[3] = SYNC_STATUS;
        BLOCK_COLUMNS[4] = VERIFICATION_STATUS;
        BLOCK_COLUMNS[5] = BLOCK_TIMESTAMP;
    }

    public void initBlockDataList(List<BlockData> blockDataList) {
        this.blockDataList = blockDataList;
    }

    public int getColumnCount() {
        return BLOCK_COLUMN_COUNT;
    }

    public String getColumnName(int column) {
        return BLOCK_COLUMNS[column];
    }

    public Object getValueAt(int row, int column) {
        BlockData blockData = blockDataList.get(row);
        if (column==0) {
            return blockData.getBlockHeight();
        } else if (column==1) {
            return blockData.getBlockHash();
        } else if (column==2) {
            return blockData.getBlockNonce();
        } else if (column==3) {
            return blockData.getSyncStatus();
        } else if (column==4) {
            return blockData.getVerifyStatus();
        } else if (column==5) {
            return blockData.getBlockTimestamp();
        }
        return null;
    }

    public int getRowCount() {
        if (blockDataList==null) {
            return 0;
        }
        return blockDataList.size();
    }
}
