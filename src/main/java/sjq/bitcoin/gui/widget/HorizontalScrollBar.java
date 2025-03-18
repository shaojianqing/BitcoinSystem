package sjq.bitcoin.gui.widget;

import javax.swing.*;

public class HorizontalScrollBar extends JScrollBar {

    public HorizontalScrollBar(int orientation) {
        super(orientation);
        setUI(new HorizontalScrollBarUI());
    }
}
