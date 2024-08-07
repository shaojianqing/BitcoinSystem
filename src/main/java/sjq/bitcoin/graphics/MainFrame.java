package sjq.bitcoin.graphics;

import sjq.bitcoin.constant.Constants;

import javax.swing.*;

public class MainFrame extends JFrame {

    private static final int FRAME_WIDTH = 1600;

    private static final int FRAME_HEIGHT = 900;

    private GuiSystem guiSystem;

    public MainFrame(GuiSystem guiSystem) {

        this.guiSystem = guiSystem;

        this.initMainView();
        this.initDataView();
    }

    public void start() {
        this.setVisible(true);
    }

    private void initMainView() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(Constants.SYSTEM_NAME);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        JLabel label = new JLabel("Hello World");
        getContentPane().add(label);
    }

    private void initDataView() {

    }
}
