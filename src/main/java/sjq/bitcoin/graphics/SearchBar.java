package sjq.bitcoin.graphics;

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

    private JButton searchBtn;

    public SearchBar() {
        setBackground(Appearance.MAIN_COLOR);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(1180, 38));
        setBorder(BorderFactory.createLineBorder(Appearance.SEARCHBAR_COLOR));

        blockHeightLbl = new JLabel();
        blockHashLbl = new JLabel();
        blockStatusLbl = new JLabel();

        blockHeightLbl.setForeground(Appearance.INPUT_TEXT_COLOR);
        blockHashLbl.setForeground(Appearance.INPUT_TEXT_COLOR);
        blockStatusLbl.setForeground(Appearance.INPUT_TEXT_COLOR);

        blockHeightLbl.setText("Block Height:");
        blockHashLbl.setText("Block Hash:");
        blockStatusLbl.setText("Block Status:");

        blockHeightTxt = new JTextField();
        blockHashTxt = new JTextField();
        blockStatusCmb = new JComboBox();

        blockHeightTxt.setPreferredSize(new Dimension(120, 24));
        blockHashTxt.setPreferredSize(new Dimension(400, 24));
        blockStatusCmb.setPreferredSize(new Dimension(160, 24));

        blockHeightTxt.setBorder(BorderFactory.createLineBorder(Appearance.BORDER_COLOR));
        blockHashTxt.setBorder(BorderFactory.createLineBorder(Appearance.BORDER_COLOR));

        blockHeightTxt.setBackground(Appearance.AREA_COLOR);
        blockHeightTxt.setCaretColor(Appearance.INPUT_TEXT_COLOR);
        blockHashTxt.setBackground(Appearance.AREA_COLOR);
        blockHashTxt.setCaretColor(Appearance.INPUT_TEXT_COLOR);
        blockStatusCmb.setBackground(Appearance.AREA_COLOR);

        blockHeightTxt.setForeground(Color.WHITE);
        blockHashTxt.setForeground(Color.WHITE);
        blockStatusCmb.setForeground(Color.WHITE);

        blockStatusCmb.addItem("Confirmed");
        blockStatusCmb.addItem("Unconfirmed");
        blockStatusCmb.setBorder(BorderFactory.createLineBorder(Appearance.BORDER_COLOR));
        blockStatusCmb.setForeground(Appearance.INPUT_TEXT_COLOR);
        blockStatusCmb.setBackground(Appearance.GRID_COLOR);

        searchBtn = new JButton();
        searchBtn.setText("Search");
        searchBtn.setEnabled(true);
        searchBtn.setBorder(BorderFactory.createLineBorder(Appearance.BORDER_COLOR));
        searchBtn.setPreferredSize(new Dimension(120, 24));
        searchBtn.setContentAreaFilled(false);
        searchBtn.setForeground(Appearance.INPUT_TEXT_COLOR);
        searchBtn.setBackground(Appearance.GRID_COLOR);

        add(blockHeightLbl);
        add(blockHeightTxt);

        add(blockHashLbl);
        add(blockHashTxt);

        add(blockStatusLbl);
        add(blockStatusCmb);

        add(searchBtn);
    }
}
