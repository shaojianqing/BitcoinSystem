package sjq.bitcoin.graphics;

import sjq.bitcoin.constant.Appearance;
import sjq.bitcoin.constant.Constants;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final int FRAME_WIDTH = 1600;

    private static final int FRAME_HEIGHT = 900;

    private GuiSystem guiSystem;

    private MainLayout layout;

    private Toolbar toolbar;

    private BlockTable blockTable;

    private PeerTable peerTable;

    private Console console;

    public MainFrame(GuiSystem guiSystem) {

        this.guiSystem = guiSystem;

        this.initMainView();
        this.initDataView();
    }

    public void start() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        setVisible(true);
    }

    private void initMainView() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle(Constants.SYSTEM_NAME);
        setBackground(Appearance.MAIN_COLOR);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);


        layout = new MainLayout();
        toolbar = new Toolbar();
        blockTable = new BlockTable();
        peerTable = new PeerTable();
        console = new Console();

        getContentPane().setLayout(layout);
        getContentPane().setBackground(Appearance.MAIN_COLOR);
        getContentPane().add(toolbar, BorderLayout.NORTH);
        getContentPane().add(blockTable, BorderLayout.CENTER);
        getContentPane().add(peerTable, BorderLayout.EAST);
        getContentPane().add(console, BorderLayout.SOUTH);
    }

    private void initDataView() {

    }
}
