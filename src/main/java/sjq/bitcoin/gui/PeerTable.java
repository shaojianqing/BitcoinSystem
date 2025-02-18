package sjq.bitcoin.gui;

import sjq.bitcoin.components.VerticalScrollBar;
import sjq.bitcoin.constant.Appearance;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.gui.data.PeerData;
import sjq.bitcoin.gui.model.PeerDataModel;
import sjq.bitcoin.network.PeerManager;
import sjq.bitcoin.network.event.PeerChangeListener;
import sjq.bitcoin.network.node.PeerNode;
import sjq.bitcoin.utility.DateUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class PeerTable extends JPanel implements PeerChangeListener {

    private static final int PEER_TABLE_WIDTH = 380;

    private static final int PEER_TABLE_HEIGHT = 630;

    private static final int PEER_ROW_HEIGHT = 36;

    private final JTable peerTable;

    private final JScrollPane panel;

    private final VerticalScrollBar vScrollBar;

    @Autowire
    private PeerManager peerManager;

    private PeerDataModel peerDataModel;

    public PeerTable() {

        setBackground(Appearance.MAIN_COLOR);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        peerDataModel = new PeerDataModel();
        peerTable = new JTable(peerDataModel);
        peerTable.getTableHeader().setPreferredSize(new Dimension(PEER_TABLE_WIDTH, 36));
        peerTable.getTableHeader().setBackground(Appearance.MAIN_COLOR);
        peerTable.getTableHeader().setForeground(new Color(220,220,220));

        peerTable.getColumnModel().getColumn(0).setPreferredWidth(140);
        peerTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        peerTable.getColumnModel().getColumn(2).setPreferredWidth(160);

        peerTable.setBounds(0,0, PEER_TABLE_WIDTH, PEER_TABLE_HEIGHT);
        peerTable.setRowHeight(PEER_ROW_HEIGHT);
        peerTable.setPreferredSize(new Dimension(PEER_TABLE_WIDTH, PEER_TABLE_HEIGHT));
        peerTable.setVisible(true);
        peerTable.setGridColor(Appearance.GRID_COLOR);
        peerTable.setBackground(Appearance.MAIN_COLOR);
        peerTable.setForeground(Appearance.FORE_TEXT_COLOR);
        peerTable.setRowSelectionAllowed(false);
        peerTable.setColumnSelectionAllowed(false);
        peerTable.setFocusable(false);

        vScrollBar = new VerticalScrollBar(JScrollBar.VERTICAL);
        vScrollBar.setUnitIncrement(8);
        panel = new JScrollPane(peerTable);
        panel.setFocusable(false);
        panel.setVerticalScrollBar(vScrollBar);
        panel.setBackground(Appearance.MAIN_COLOR);
        panel.setBounds(0,0, PEER_TABLE_WIDTH, PEER_TABLE_HEIGHT);
        panel.setPreferredSize(new Dimension(PEER_TABLE_WIDTH, PEER_TABLE_HEIGHT));
        panel.setBorder(BorderFactory.createLoweredBevelBorder());

        add(panel);
    }

    public void initDataView() {
        this.peerManager.registerPeerChangeListener(this);
    }

    @Override
    public void onPeerChange(PeerManager manager, PeerNode node) {
        List<PeerNode> peerList = manager.getPeerNodeList();
        List<PeerData> peerDataList = peerList.stream().map(peer->{
            String status = peer.getStatus();
            String address = peer.getAddress().getHostString();
            String connectionTime = DateUtils.formatNormalDate(peer.getConnectionTime());
            return new PeerData(address, status, connectionTime);
        }).collect(Collectors.toList());

        peerDataModel.initPeerDataList(peerDataList);
        peerDataModel.fireTableDataChanged();
    }
}
