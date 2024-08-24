package sjq.bitcoin.graphics;

import sjq.bitcoin.constant.Appearance;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPanel {

    private static final int TOOLBAR_WIDTH = 1600;

    private static final int TOOLBAR_HEIGHT = 80;

    private static final int BUTTON_WIDTH = 100;

    private static final int BUTTON_HEIGHT = 80;

    private JButton queryBlockBtn;

    private JButton queryTransactionBtn;

    private JButton versionBtn;

    public Toolbar() {
        setSize(TOOLBAR_WIDTH, TOOLBAR_HEIGHT);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Appearance.MAIN_COLOR);

        queryBlockBtn = new JButton();
        queryBlockBtn.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        queryBlockBtn.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        queryBlockBtn.setBounds(0,0, BUTTON_WIDTH, BUTTON_HEIGHT);
        queryBlockBtn.setContentAreaFilled(true);
        queryBlockBtn.setOpaque(false);
        queryBlockBtn.setBorder(null);
        queryBlockBtn.setBorderPainted(false);
        queryBlockBtn.setBackground(new Color(2,2,2));
        queryBlockBtn.setIcon(new ImageIcon("res/images/icon_blockchain.png"));

        queryTransactionBtn = new JButton();
        queryTransactionBtn.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        queryTransactionBtn.setBounds(0,0, BUTTON_WIDTH, BUTTON_HEIGHT);
        queryTransactionBtn.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        queryTransactionBtn.setContentAreaFilled(true);
        queryTransactionBtn.setOpaque(false);
        queryTransactionBtn.setBorder(null);
        queryTransactionBtn.setBorderPainted(false);
        queryTransactionBtn.setBackground(new Color(2,2,2));
        queryTransactionBtn.setIcon(new ImageIcon("res/images/icon_transaction.png"));


        add(queryBlockBtn);
        add(queryTransactionBtn);
    }

}


