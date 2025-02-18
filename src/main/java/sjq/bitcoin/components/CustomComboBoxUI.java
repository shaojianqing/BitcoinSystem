package sjq.bitcoin.components;

import sjq.bitcoin.constant.Appearance;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;

public class CustomComboBoxUI extends BasicComboBoxUI {

    @Override
    protected JButton createArrowButton() {
        JButton button = new VerticalArrowButton(BasicArrowButton.SOUTH,
                Appearance.MAIN_COLOR, Appearance.MAIN_COLOR, Color.GRAY,  Color.GRAY);
        button.setBorder(BorderFactory.createLineBorder(Appearance.BORDER_COLOR, 0));
        return button;
    }

    @Override
    protected ComboPopup createPopup() {
        ComboPopup popup = new BasicComboPopup(comboBox);
        popup.getList().setBackground(Appearance.MAIN_COLOR);
        popup.getList().setForeground(Appearance.INPUT_TEXT_COLOR);
        popup.getList().setSelectionBackground(Appearance.SELECTION_COLOR);
        return popup;
    }

    public void paintCurrentValue(Graphics g,Rectangle bounds,boolean hasFocus) {
        ListCellRenderer renderer = comboBox.getRenderer();
        Component c;

        if ( hasFocus && !isPopupVisible(comboBox) ) {
            c = renderer.getListCellRendererComponent( listBox,
                    comboBox.getSelectedItem(),
                    -1,
                    true,
                    false );
        }
        else {
            c = renderer.getListCellRendererComponent( listBox,
                    comboBox.getSelectedItem(),
                    -1,
                    false,
                    false );
            c.setBackground(Appearance.AREA_COLOR);
        }
        c.setFont(comboBox.getFont());
        if ( hasFocus && !isPopupVisible(comboBox) ) {
            c.setForeground(Appearance.INPUT_TEXT_COLOR);
            c.setBackground(Appearance.MAIN_COLOR);
        }
        else {
            if ( comboBox.isEnabled() ) {
                c.setForeground(comboBox.getForeground());
                c.setBackground(comboBox.getBackground());
            }
        }

        boolean shouldValidate = false;
        if (c instanceof JPanel)  {
            shouldValidate = true;
        }

        int x = bounds.x, y = bounds.y, w = bounds.width, h = bounds.height;
        if (padding != null) {
            x = bounds.x + padding.left;
            y = bounds.y + padding.top;
            w = bounds.width - (padding.left + padding.right);
            h = bounds.height - (padding.top + padding.bottom);
        }

        currentValuePane.paintComponent(g,c,comboBox,x,y,w,h,shouldValidate);
    }
}
