package sjq.bitcoin.gui;


import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.gui.widget.VerticalScrollBar;
import sjq.bitcoin.constant.Appearance;
import sjq.bitcoin.context.Autowire;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BlockTable extends JPanel {

    private static final int BLOCK_TABLE_WIDTH = 1180;

    private static final int BLOCK_TABLE_HEIGHT = 590;

    private final JTable table;

    private final JScrollPane panel;

    private final SearchBar searchBar;

    private final VerticalScrollBar vScrollBar;

    @Autowire
    private Blockchain blockchain;

    public BlockTable() {

        String[] columns = new String[]{"Block Height", "Block Hash", "Nonce", "Transaction Count", "Block Status", "Block Created Time"};
        String[][] tableData = new String[][]{
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"},
                {"858286", "000000000000000000016f1d06eb6fab1148742554025992be71e067f71f9194", "0x483aa03f", "2898", "Confirmed", "2024-08-24 21:59:32"}};

        DefaultTableModel tableModel = new DefaultTableModel(tableData, columns);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(Appearance.MAIN_COLOR);

        searchBar = new SearchBar();
        searchBar.setBounds(0,0, 1180, 36);

        table = new JTable(tableModel);
        table.getTableHeader().setPreferredSize(new Dimension(1180, 36));
        table.getTableHeader().setBackground(Appearance.MAIN_COLOR);
        table.getTableHeader().setForeground(new Color(220,220,220));

        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);

        table.setBounds(0,0, BLOCK_TABLE_WIDTH, BLOCK_TABLE_HEIGHT);
        table.setRowHeight(36);
        table.setPreferredSize(new Dimension(BLOCK_TABLE_WIDTH, BLOCK_TABLE_HEIGHT));
        table.setVisible(true);
        table.setGridColor(Appearance.GRID_COLOR);
        table.setBackground(Appearance.MAIN_COLOR);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        table.setFocusable(false);
        table.setForeground(Appearance.FORE_TEXT_COLOR);
        table.setAutoscrolls(true);

        vScrollBar = new VerticalScrollBar(JScrollBar.VERTICAL);
        vScrollBar.setUnitIncrement(8);
        panel = new JScrollPane(table);
        panel.setBackground(Appearance.MAIN_COLOR);
        panel.setBounds(10,0, BLOCK_TABLE_WIDTH, BLOCK_TABLE_HEIGHT);
        panel.setVerticalScrollBar(vScrollBar);
        panel.setFocusable(false);
        panel.setPreferredSize(new Dimension(1180, 590));
        panel.setBorder(BorderFactory.createLoweredBevelBorder());

        add(searchBar);
        add(panel);
    }

    public void initDataView() {

    }
}
