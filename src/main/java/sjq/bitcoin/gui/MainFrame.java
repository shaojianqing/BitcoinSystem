package sjq.bitcoin.gui;

import sjq.bitcoin.constant.Appearance;
import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.context.Autowire;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final int FRAME_WIDTH = 1600;

    private static final int FRAME_HEIGHT = 900;

    @Autowire
    private GuiSystem guiSystem;

    @Autowire
    private BorderLayout layout;

    @Autowire
    private Toolbar toolbar;

    @Autowire
    private BlockTable blockTable;

    @Autowire
    private PeerTable peerTable;

    @Autowire
    private Console console;

    public void initMainView() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setTitle(Constants.SYSTEM_NAME);
        this.setBackground(Appearance.MAIN_COLOR);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setResizable(false);

        this.getContentPane().setLayout(layout);
        this.getContentPane().setBackground(Appearance.MAIN_COLOR);
        this.getContentPane().add(toolbar, BorderLayout.NORTH);
        this.getContentPane().add(blockTable, BorderLayout.CENTER);
        this.getContentPane().add(peerTable, BorderLayout.EAST);
        this.getContentPane().add(console, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public void initDataView() {
        this.peerTable.initDataView();
        this.blockTable.initDataView();
        this.console.initDataView();
    }
}
