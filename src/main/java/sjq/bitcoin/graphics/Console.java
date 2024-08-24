package sjq.bitcoin.graphics;

import sjq.bitcoin.constant.Appearance;

import javax.swing.*;
import java.awt.*;

public class Console extends JPanel {

    private static final int CONSOLE_TABLE_WIDTH = 1600;

    private static final int CONSOLE_TABLE_HEIGHT = 120;

    private JTextArea output;

    private JScrollPane panel;

    public Console() {

        setBackground(Appearance.MAIN_COLOR);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        output = new JTextArea();
        output.setBackground(Appearance.AREA_COLOR);
        output.setBounds(0,0, 1575, 120);
        output.setFocusable(false);

        panel = new JScrollPane(output);
        panel.setBackground(Appearance.AREA_COLOR);
        panel.setBounds(0,0, 1575, 120);
        panel.setFocusable(false);
        panel.setPreferredSize(new Dimension(1575, 120));
        panel.setBorder(BorderFactory.createLoweredBevelBorder());

        add(panel);
    }
}
