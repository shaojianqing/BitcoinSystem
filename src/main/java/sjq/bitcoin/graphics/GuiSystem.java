package sjq.bitcoin.graphics;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.context.Context;
import sjq.bitcoin.core.BitcoinCore;


public class GuiSystem {

    @Autowire
    private MainFrame mainFrame;

    @Autowire
    private BitcoinCore bitcoinCore;

    public void start() {
        mainFrame.start();

    }
}
