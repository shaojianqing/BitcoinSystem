package sjq.bitcoin.graphics;

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

    public void start() {
        initMainView();
        setVisible(true);
    }

    public void initMainView() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle(Constants.SYSTEM_NAME);
        setBackground(Appearance.MAIN_COLOR);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);

        getContentPane().setLayout(layout);
        getContentPane().setBackground(Appearance.MAIN_COLOR);
        getContentPane().add(toolbar, BorderLayout.NORTH);
        getContentPane().add(blockTable, BorderLayout.CENTER);
        getContentPane().add(peerTable, BorderLayout.EAST);
        getContentPane().add(console, BorderLayout.SOUTH);
    }

    public void initDataView() {

    }
}
