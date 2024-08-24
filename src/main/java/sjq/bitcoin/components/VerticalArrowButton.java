package sjq.bitcoin.components;

import sjq.bitcoin.constant.Appearance;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

public class VerticalArrowButton extends BasicArrowButton {

    public VerticalArrowButton(int direction, Color background, Color shadow, Color darkShadow, Color highlight) {
        super(direction, background, shadow, darkShadow, highlight);
        super.setBorder(BorderFactory.createLineBorder(Appearance.GRID_COLOR));
    }
}
