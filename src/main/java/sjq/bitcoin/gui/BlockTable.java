package sjq.bitcoin.gui;


import org.apache.commons.collections4.CollectionUtils;
import sjq.bitcoin.blockchain.Blockchain;
import sjq.bitcoin.blockchain.param.BlockQueryRequest;
import sjq.bitcoin.constant.Appearance;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.gui.data.BlockData;
import sjq.bitcoin.gui.model.BlockDataModel;
import sjq.bitcoin.gui.widget.BlockTableCellRender;
import sjq.bitcoin.gui.widget.VerticalScrollBar;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.storage.domain.Block;
import sjq.bitcoin.utility.DateUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class BlockTable extends JPanel {

    private static final int BLOCK_TABLE_WIDTH = 1180;

    private static final int BLOCK_TABLE_HEIGHT = 590;

    private final JTable table;

    private final JScrollPane panel;

    private final SearchBar searchBar;

    private final VerticalScrollBar vScrollBar;

    private final BlockTableCellRender cellRender;

    private final BlockDataModel blockDataModel;

    @Autowire
    private Blockchain blockchain;

    public BlockTable() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Appearance.MAIN_COLOR);

        this.searchBar = new SearchBar(this);
        this.searchBar.setBounds(0,0, 1180, 36);

        this.blockDataModel = new BlockDataModel();
        this.cellRender = new BlockTableCellRender();
        this.table = new JTable(blockDataModel);
        this.table.getTableHeader().setPreferredSize(new Dimension(1180, 36));
        this.table.getTableHeader().setBackground(Appearance.MAIN_COLOR);
        this.table.getTableHeader().setForeground(new Color(220,220,220));

        this.table.getColumnModel().getColumn(0).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(500);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(200);

        this.table.setBounds(0,0, BLOCK_TABLE_WIDTH, BLOCK_TABLE_HEIGHT);
        this.table.setRowHeight(36);
        this.table.setPreferredSize(new Dimension(BLOCK_TABLE_WIDTH, BLOCK_TABLE_HEIGHT));
        this.table.setGridColor(Appearance.GRID_COLOR);
        this.table.setBackground(Appearance.MAIN_COLOR);
        this.table.setForeground(Appearance.FORE_TEXT_COLOR);
        this.table.setDefaultRenderer(Object.class, cellRender);
        this.table.setRowSelectionAllowed(true);
        this.table.setColumnSelectionAllowed(false);
        this.table.setCellEditor(null);
        this.table.setVisible(true);
        this.table.setFocusable(true);
        this.table.setAutoscrolls(true);

        this.vScrollBar = new VerticalScrollBar(JScrollBar.VERTICAL);
        this.vScrollBar.setUnitIncrement(8);
        this.panel = new JScrollPane(table);
        this.panel.setBackground(Appearance.MAIN_COLOR);
        this.panel.setBounds(10,0, BLOCK_TABLE_WIDTH, BLOCK_TABLE_HEIGHT);
        this.panel.setVerticalScrollBar(vScrollBar);
        this.panel.setFocusable(false);
        this.panel.setPreferredSize(new Dimension(1180, 590));
        this.panel.setBorder(BorderFactory.createLoweredBevelBorder());

        this.add(searchBar);
        this.add(panel);
    }

    public void initDataView() {
        BlockQueryRequest queryRequest = BlockQueryRequest.build();
        renderBlockDataListView(queryRequest);
    }

    public void searchBlockData(BlockQueryRequest queryRequest) {
        renderBlockDataListView(queryRequest);
    }

    private void renderBlockDataListView(BlockQueryRequest queryRequest) {
        try {
            java.util.List<Block> blockList = blockchain.queryBlockWithCondition(queryRequest);
            if (CollectionUtils.isNotEmpty(blockList)) {
                java.util.List<BlockData> blockDataList = blockList.stream().map(block -> {
                    String blockHeight = String.valueOf(block.getBlockHeight());
                    String blockHash = block.getBlockHash();
                    String blockNonce = String.format("0x%08X", block.getNonce());
                    String syncStatus = block.getSyncStatus();
                    String verifyStatus = block.getVerifyStatus();
                    String timestamp = DateUtils.formatNormalDate(new Date(block.getTimestamp()*1000));
                    return new BlockData(blockHeight, blockHash, blockNonce, syncStatus, verifyStatus, timestamp);
                }).toList();
                this.blockDataModel.initBlockDataList(blockDataList);
            } else {
                this.blockDataModel.initBlockDataList(new ArrayList<>());
            }
            this.blockDataModel.fireTableDataChanged();
            this.table.clearSelection();
        } catch (Exception e) {
            Logger.error("query block data with condition encounter error:%s", e);
        }
    }
}
