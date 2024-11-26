package sjq.bitcoin.graphics;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.context.Context;
import sjq.bitcoin.core.BitcoinCore;


public class GuiSystem {

    private final MainFrame mainFrame;

    @Autowire
    private BitcoinCore bitcoinCore;

    public GuiSystem() {
        mainFrame = new MainFrame(this);
    }

    public void start() {
        mainFrame.start();
    }
}
