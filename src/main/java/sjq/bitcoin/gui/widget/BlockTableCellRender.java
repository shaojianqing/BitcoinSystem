package sjq.bitcoin.gui.widget;

import org.apache.commons.lang3.StringUtils;
import sjq.bitcoin.constant.Appearance;
import sjq.bitcoin.storage.domain.Block;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class BlockTableCellRender extends DefaultTableCellRenderer {

    private static final int COLUMN_INDEX_SYNC_STATUS = 3;

    private static final int COLUMN_INDEX_VERIFY_STATUS = 4;

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (column == COLUMN_INDEX_SYNC_STATUS) {
            renderSyncStatusComponent(value);
        } else if (column == COLUMN_INDEX_VERIFY_STATUS) {
            renderVerifyStatusComponent(value);
        } else {
            this.setForeground(Appearance.FORE_TEXT_COLOR);
        }
        return this;
    }

    private void renderVerifyStatusComponent(Object value) {
        if (value instanceof String status) {
            if (StringUtils.equals(Block.STATUS_UN_VERIFY_HEADER, status)) {
                this.setForeground(Appearance.BLOCK_UNVERIFIED_HEADER_COLOR);
            } else if (StringUtils.equals(Block.STATUS_VERIFY_HEADER, status)) {
                this.setForeground(Appearance.BLOCK_VERIFIED_HEADER_COLOR);
            } else if (StringUtils.equals(Block.STATUS_VERIFY_TRANSACTION, status)){
                this.setForeground(Appearance.BLOCK_VERIFIED_TRANSACTION_COLOR);
            } else {
                this.setForeground(Appearance.FORE_TEXT_COLOR);
            }
        }
    }

    private void renderSyncStatusComponent(Object value) {
        if (value instanceof String status) {
            if (StringUtils.equals(Block.STATUS_SYNC_HEADER, status)) {
                this.setForeground(Appearance.BLOCK_SYNC_HEADER_COLOR);
            } else if (StringUtils.equals(Block.STATUS_SYNC_TRANSACTION, status)) {
                this.setForeground(Appearance.BLOCK_SYNC_TRANSACTION_COLOR);
            } else {
                this.setForeground(Appearance.FORE_TEXT_COLOR);
            }
        }
    }
}
