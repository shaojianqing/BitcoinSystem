package sjq.bitcoin.gui.widget;

import sjq.bitcoin.constant.Appearance;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class HorizontalScrollBarUI  extends BasicScrollBarUI {

    @Override
    protected JButton createDecreaseButton(int orientation)  {
        return new VerticalArrowButton(orientation, Appearance.MAIN_COLOR, Appearance.MAIN_COLOR, Color.GRAY,  Color.GRAY);
    }

    @Override
    protected JButton createIncreaseButton(int orientation)  {
        return new VerticalArrowButton(orientation, Appearance.MAIN_COLOR, Appearance.MAIN_COLOR, Color.GRAY,  Color.GRAY);
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(Appearance.TRACK_COLOR);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        // 绘制滑块时使用自定义背景色
        g.setColor(Appearance.MAIN_COLOR);
        g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
        g.setColor(Appearance.AREA_COLOR); // 滑块边缘颜色
        g.drawRect(thumbBounds.x+1, thumbBounds.y, thumbBounds.width - 2, thumbBounds.height);
    }
}
