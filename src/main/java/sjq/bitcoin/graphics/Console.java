package sjq.bitcoin.graphics;

import sjq.bitcoin.components.VerticalScrollBar;
import sjq.bitcoin.constant.Appearance;
import sjq.bitcoin.logger.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Console extends JPanel {

    private static final int CONSOLE_TABLE_WIDTH = 1570;

    private static final int CONSOLE_TABLE_HEIGHT = 120;

    private static final long TIME_TASK_DELAY = 1000;

    private static final long TIME_TASK_PEROID = 200;

    private JTextArea output;

    private JScrollPane panel;

    private VerticalScrollBar vScrollBar;

    private Timer logTasktimer;

    public Console() {
        initMainView();
        initDataView();
    }

    private void initMainView() {
        setBackground(Appearance.MAIN_COLOR);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        output = new JTextArea();
        output.setBackground(Appearance.AREA_COLOR);
        output.setBounds(0,0, CONSOLE_TABLE_WIDTH, CONSOLE_TABLE_HEIGHT);
        output.setForeground(Appearance.CONSOLE_TEXT_COLOR);
        output.setAutoscrolls(true);
        output.setFocusable(true);
        output.setEditable(false);

        vScrollBar = new VerticalScrollBar(JScrollBar.VERTICAL);

        panel = new JScrollPane(output);
        panel.setFocusable(false);
        panel.setVerticalScrollBar(vScrollBar);
        panel.setBackground(Appearance.AREA_COLOR);
        panel.setBounds(0,0, CONSOLE_TABLE_WIDTH, CONSOLE_TABLE_HEIGHT);
        panel.setPreferredSize(new Dimension(CONSOLE_TABLE_WIDTH, CONSOLE_TABLE_HEIGHT));
        panel.setBorder(BorderFactory.createLoweredBevelBorder());

        add(panel);
    }

    private void initDataView() {
        logTasktimer = new Timer();
        logTasktimer.schedule(new TimerTask() {
            @Override
            public void run() {
                output.setText(Logger.getContent());
            }
        }, TIME_TASK_DELAY, TIME_TASK_PEROID);
    }
}