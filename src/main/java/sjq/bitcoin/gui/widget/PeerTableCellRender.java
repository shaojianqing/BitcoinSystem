package sjq.bitcoin.gui.widget;

import org.apache.commons.lang3.StringUtils;
import sjq.bitcoin.constant.Appearance;
import sjq.bitcoin.network.node.PeerNode;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class PeerTableCellRender extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof String status) {
            if (StringUtils.equals(PeerNode.ACKNOWLEDGE, status)) {
                this.setForeground(Appearance.PEER_ACKNOWLEDGE_COLOR);
            } else if (StringUtils.equals(PeerNode.CONNECTED, status)) {
                this.setForeground(Appearance.PEER_CONNECTION_COLOR);
            } else {
                this.setForeground(Appearance.FORE_TEXT_COLOR);
            }
        }
        return this;
    }
}
