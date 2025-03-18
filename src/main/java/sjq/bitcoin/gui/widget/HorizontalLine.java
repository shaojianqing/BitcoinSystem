package sjq.bitcoin.gui.widget;

import javax.swing.*;
import java.awt.*;

public class HorizontalLine extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
    }
}
