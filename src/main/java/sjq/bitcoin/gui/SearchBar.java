package sjq.bitcoin.gui;

import sjq.bitcoin.blockchain.param.BlockQueryRequest;
import sjq.bitcoin.gui.widget.CustomComboBoxUI;
import sjq.bitcoin.constant.Appearance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SearchBar extends JPanel {

    private final JLabel blockHeightLbl;

    private final JTextField blockHeightTxt;

    private final JLabel blockHashLbl;

    private final JTextField blockHashTxt;

    private final JLabel blockStatusLbl;

    private final JComboBox<String> blockStatusCmb;

    private final JLabel searchBtnLbl;

    private final JButton searchBtn;

    private final BlockTable blockTable;

    public SearchBar(BlockTable blockTable) {
        setBackground(Appearance.MAIN_COLOR);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(1180, 38));
        setBorder(BorderFactory.createLineBorder(Appearance.SEARCHBAR_COLOR));

        this.blockTable = blockTable;
        this.blockHeightLbl = new JLabel();
        this.blockHashLbl = new JLabel();
        this.blockStatusLbl = new JLabel();
        this.searchBtnLbl = new JLabel();

        this.blockHeightLbl.setForeground(Appearance.INPUT_TEXT_COLOR);
        this.blockHashLbl.setForeground(Appearance.INPUT_TEXT_COLOR);
        this.blockHashLbl.setPreferredSize(new Dimension(100, 24));
        this.blockHashLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        this.blockStatusLbl.setForeground(Appearance.INPUT_TEXT_COLOR);
        this.blockStatusLbl.setPreferredSize(new Dimension(100, 24));
        this.blockStatusLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        this.searchBtnLbl.setPreferredSize(new Dimension(20, 24));
        this.searchBtnLbl.setHorizontalAlignment(SwingConstants.RIGHT);

        this.blockHeightLbl.setText("Block Height:");
        this.blockHashLbl.setText("Block Hash:");
        this.blockStatusLbl.setText("Verify Status:");

        this.blockHeightTxt = new JTextField();
        this.blockHashTxt = new JTextField();
        this.blockStatusCmb = new JComboBox<String>();

        this.blockHeightTxt.setPreferredSize(new Dimension(120, 24));
        this.blockHeightTxt.setBorder(BorderFactory.createLineBorder(Appearance.BORDER_COLOR));
        this.blockHeightTxt.setBackground(Appearance.AREA_COLOR);
        this.blockHeightTxt.setForeground(Color.WHITE);
        this.blockHeightTxt.setCaretColor(Appearance.INPUT_TEXT_COLOR);

        this.blockHashTxt.setPreferredSize(new Dimension(420, 24));
        this.blockHashTxt.setBorder(BorderFactory.createLineBorder(Appearance.BORDER_COLOR));
        this.blockHashTxt.setBackground(Appearance.AREA_COLOR);
        this.blockHashTxt.setCaretColor(Appearance.INPUT_TEXT_COLOR);
        this.blockHashTxt.setForeground(Color.WHITE);

        this.blockStatusCmb.setPreferredSize(new Dimension(160, 24));
        this.blockStatusCmb.setOpaque(true);
        this.blockStatusCmb.addItem("All");
        this.blockStatusCmb.addItem("UnVerifyHeader");
        this.blockStatusCmb.addItem("VerifyHeader");
        this.blockStatusCmb.addItem("VerifyTransaction");
        this.blockStatusCmb.setUI(new CustomComboBoxUI());
        this.blockStatusCmb.setBorder(BorderFactory.createLineBorder(Appearance.BORDER_COLOR));
        this.blockStatusCmb.setForeground(Appearance.INPUT_TEXT_COLOR);
        this.blockStatusCmb.setBackground(Appearance.MAIN_COLOR);

        this.searchBtn = new JButton();
        this.searchBtn.setText("Search");
        this.searchBtn.setEnabled(true);
        this.searchBtn.setOpaque(true);
        this.searchBtn.setIconTextGap(10);
        this.searchBtn.setBorder(BorderFactory.createLineBorder(Appearance.BORDER_COLOR, 1));
        this.searchBtn.setPreferredSize(new Dimension(100, 24));
        this.searchBtn.setForeground(Appearance.INPUT_TEXT_COLOR);
        this.searchBtn.setBackground(Appearance.MAIN_COLOR);
        this.searchBtn.setIcon(new ImageIcon("res/images/icon_search_data.png"));

        this.searchBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchBtn.setBackground(Appearance.AREA_COLOR);
                searchBlockDataList();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                searchBtn.setBackground(Appearance.MAIN_COLOR);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                searchBtn.setBackground(Appearance.MAIN_COLOR);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                searchBtn.setBackground(Appearance.AREA_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                searchBtn.setBackground(Appearance.MAIN_COLOR);
            }
        });

        this.add(blockHeightLbl);
        this.add(blockHeightTxt);

        this.add(blockHashLbl);
        this.add(blockHashTxt);

        this.add(blockStatusLbl);
        this.add(blockStatusCmb);

        this.add(searchBtnLbl);
        this.add(searchBtn);
    }

    private void searchBlockDataList() {
        String blockHeight = blockHeightTxt.getText();
        String blockHash = blockHashTxt.getText();
        String blockStatus = (String)blockStatusCmb.getSelectedItem();

        this.blockTable.searchBlockData(BlockQueryRequest.build(blockHeight, blockHash, blockStatus));
    }
}
