package sjq.bitcoin.gui;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.gui.widget.HorizontalLine;
import sjq.bitcoin.constant.Appearance;
import sjq.bitcoin.logger.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Toolbar extends JPanel {

    private static final int TOOLBAR_WIDTH = 1600;

    private static final int TOOLBAR_HEIGHT = 80;

    private static final int BUTTON_WIDTH = 100;

    private static final int BUTTON_HEIGHT = 80;

    private final HorizontalLine separateLine;

    private final JButton queryTransactionBtn;

    private final JButton sendTransactionBtn;

    private final JButton walletCoreBtn;

    private final JButton minerCoreBtn;

    private final JButton cryptoToolBtn;

    private final JButton versionBtn;

    @Autowire
    private MainFrame mainFrame;

    public Toolbar() {
        this.setSize(TOOLBAR_WIDTH, TOOLBAR_HEIGHT);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Appearance.MAIN_COLOR);

        this.queryTransactionBtn = constructToolButton("res/images/icon_transaction.png");
        this.sendTransactionBtn = constructToolButton("res/images/icon_send.png");
        this.walletCoreBtn = constructToolButton("res/images/icon_wallet.png");
        this.minerCoreBtn = constructToolButton("res/images/icon_mining.png");
        this.cryptoToolBtn = constructToolButton("res/images/icon_crypto.png");
        this.versionBtn = constructToolButton("res/images/icon_version.png");

        this.separateLine = new HorizontalLine();
        this.separateLine.setSize(TOOLBAR_WIDTH, 2);

        this.add(queryTransactionBtn);
        this.add(sendTransactionBtn);
        this.add(walletCoreBtn);
        this.add(minerCoreBtn);
        this.add(cryptoToolBtn);
        this.add(versionBtn);

        this.initEventListener();
    }

    private void initEventListener() {
        this.queryTransactionBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

        this.walletCoreBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Logger.info("wallet core button has been clicked!");
            }
        });

        this.sendTransactionBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

        this.minerCoreBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

        this.cryptoToolBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

        this.versionBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
    }

    private JButton constructToolButton(String iconPath) {
        JButton button = new JButton();
        button.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setBounds(0,0, BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setContentAreaFilled(true);
        button.setOpaque(false);
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setFocusable(false);
        button.setBackground(new Color(2,2,2));
        button.setIcon(new ImageIcon(iconPath));
        return button;
    }
}


