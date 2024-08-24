package sjq.bitcoin.components;

import javax.swing.*;

public class VerticalScrollBar extends JScrollBar {

    public VerticalScrollBar(int orientation) {
        super(orientation);
        setUI(new VerticalScrollBarUI());
    }
}
