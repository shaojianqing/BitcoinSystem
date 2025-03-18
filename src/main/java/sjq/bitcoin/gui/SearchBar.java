package sjq.bitcoin.gui;

import sjq.bitcoin.gui.widget.CustomComboBoxUI;
import sjq.bitcoin.constant.Appearance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SearchBar extends JPanel {

    private JLabel blockHeightLbl;

    private JTextField blockHeightTxt;

    private JLabel blockHashLbl;

    private JTextField blockHashTxt;

    private JLabel blockStatusLbl;

    private JComboBox blockStatusCmb;

    private JLabel searchBtnLbl;

    private JButton searchBtn;

    public SearchBar() {
        setBackground(Appearance.MAIN_COLOR);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(1180, 38));
        setBorder(BorderFactory.createLineBorder(Appearance.SEARCHBAR_COLOR));

        blockHeightLbl = new JLabel();
        blockHashLbl = new JLabel();
        blockStatusLbl = new JLabel();
        searchBtnLbl = new JLabel();

        blockHeightLbl.setForeground(Appearance.INPUT_TEXT_COLOR);
        blockHashLbl.setForeground(Appearance.INPUT_TEXT_COLOR);
        blockHashLbl.setPreferredSize(new Dimension(100, 24));
        blockHashLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        blockStatusLbl.setForeground(Appearance.INPUT_TEXT_COLOR);
        blockStatusLbl.setPreferredSize(new Dimension(100, 24));
        blockStatusLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        searchBtnLbl.setPreferredSize(new Dimension(20, 24));
        searchBtnLbl.setHorizontalAlignment(SwingConstants.RIGHT);

        blockHeightLbl.setText("Block Height:");
        blockHashLbl.setText("Block Hash:");
        blockStatusLbl.setText("Block Status:");

        blockHeightTxt = new JTextField();
        blockHashTxt = new JTextField();
        blockStatusCmb = new JComboBox();

        blockHeightTxt.setPreferredSize(new Dimension(120, 24));
        blockHeightTxt.setBorder(BorderFactory.createLineBorder(Appearance.BORDER_COLOR));
        blockHeightTxt.setBackground(Appearance.AREA_COLOR);
        blockHeightTxt.setForeground(Color.WHITE);
        blockHeightTxt.setCaretColor(Appearance.INPUT_TEXT_COLOR);

        blockHashTxt.setPreferredSize(new Dimension(420, 24));
        blockHashTxt.setBorder(BorderFactory.createLineBorder(Appearance.BORDER_COLOR));
        blockHashTxt.setBackground(Appearance.AREA_COLOR);
        blockHashTxt.setCaretColor(Appearance.INPUT_TEXT_COLOR);
        blockHashTxt.setForeground(Color.WHITE);

        blockStatusCmb.setPreferredSize(new Dimension(160, 24));
        blockStatusCmb.setOpaque(true);
        blockStatusCmb.addItem("Confirmed");
        blockStatusCmb.addItem("Unconfirmed");
        blockStatusCmb.setUI(new CustomComboBoxUI());
        blockStatusCmb.setBorder(BorderFactory.createLineBorder(Appearance.BORDER_COLOR));
        blockStatusCmb.setForeground(Appearance.INPUT_TEXT_COLOR);
        blockStatusCmb.setBackground(Appearance.MAIN_COLOR);

        searchBtn = new JButton();
        searchBtn.setText("Search");
        searchBtn.setEnabled(true);
        searchBtn.setOpaque(true);
        searchBtn.setIconTextGap(10);
        searchBtn.setBorder(BorderFactory.createLineBorder(Appearance.BORDER_COLOR, 1));
        searchBtn.setPreferredSize(new Dimension(100, 24));
        searchBtn.setForeground(Appearance.INPUT_TEXT_COLOR);
        searchBtn.setBackground(Appearance.MAIN_COLOR);
        searchBtn.setIcon(new ImageIcon("res/images/icon_search_data.png"));

        searchBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchBtn.setBackground(Appearance.AREA_COLOR);
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

        add(blockHeightLbl);
        add(blockHeightTxt);

        add(blockHashLbl);
        add(blockHashTxt);

        add(blockStatusLbl);
        add(blockStatusCmb);

        add(searchBtnLbl);
        add(searchBtn);
    }
}
