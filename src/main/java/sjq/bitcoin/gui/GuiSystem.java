package sjq.bitcoin.gui;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.core.BitcoinCore;

public class GuiSystem {

    @Autowire
    private MainFrame mainFrame;

    @Autowire
    private BitcoinCore bitcoinCore;

    public void initialize() {
        this.mainFrame.initMainView();
    }

    public void start() {
        this.mainFrame.initDataView();
    }
}
