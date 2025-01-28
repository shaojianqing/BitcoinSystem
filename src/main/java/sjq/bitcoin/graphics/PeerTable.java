package sjq.bitcoin.graphics;

import sjq.bitcoin.components.VerticalScrollBar;
import sjq.bitcoin.constant.Appearance;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.network.PeerManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PeerTable extends JPanel {

    private static final int PEER_TABLE_WIDTH = 380;

    private static final int PEER_TABLE_HEIGHT = 630;

    private final JTable table;

    private final JScrollPane panel;

    private final VerticalScrollBar vScrollBar;

    @Autowire
    private PeerManager peerManager;

    public PeerTable() {
        String[] columns = new String[]{"Peer Address", "Peer Status", "Connection Time"};
        String[][] tableData = new String[][]{{"112.18.16.188", "Connected", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Acknowledge", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Acknowledge", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Inactive", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Connecting", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Acknowledge", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Connecting", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Acknowledge", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Acknowledge", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Acknowledge", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Connecting", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Acknowledge", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Acknowledge", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Acknowledge", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Connected", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Connected", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Inactive", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Connected", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Connected", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Connected", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Inactive", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Connecting", "2020-08-09 16:32:28"},
                {"112.18.16.188", "Inactive", "2020-08-09 16:32:28"},
               };

        DefaultTableModel tableModel = new DefaultTableModel(tableData, columns);
        setBackground(Appearance.MAIN_COLOR);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        table = new JTable(tableModel);
        table.getTableHeader().setPreferredSize(new Dimension(380, 36));
        table.getTableHeader().setBackground(Appearance.MAIN_COLOR);
        table.getTableHeader().setForeground(new Color(220,220,220));

        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(160);

        table.setBounds(0,0, PEER_TABLE_WIDTH, PEER_TABLE_HEIGHT);
        table.setRowHeight(36);
        table.setPreferredSize(new Dimension(380, 630));
        table.setVisible(true);
        table.setGridColor(Appearance.GRID_COLOR);
        table.setBackground(Appearance.MAIN_COLOR);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        table.setFocusable(false);
        table.setForeground(new Color(220,220,220));

        vScrollBar = new VerticalScrollBar(JScrollBar.VERTICAL);
        vScrollBar.setUnitIncrement(8);
        panel = new JScrollPane(table);
        panel.setBackground(Appearance.MAIN_COLOR);
        panel.setBounds(0,0, 380, 630);
        panel.setVerticalScrollBar(vScrollBar);
        panel.setFocusable(false);
        panel.setPreferredSize(new Dimension(380, 630));
        panel.setBorder(BorderFactory.createLoweredBevelBorder());

        add(panel);
    }
}
