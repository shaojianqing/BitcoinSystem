package sjq.bitcoin.graphics;

import sjq.bitcoin.core.BitcoinCore;
import sjq.bitcoin.storage.StorageRepo;


public class GuiSystem {

    private BitcoinCore bitcoinCore;

    private StorageRepo storageRepo;

    private MainFrame mainFrame;

    private GuiSystem() {
        mainFrame = new MainFrame(this);
    }

    public static GuiSystem build() {
        return new GuiSystem();
    }

    public void start() {
        mainFrame.start();
    }

    public void setBitcoinCore(BitcoinCore bitcoinCore) {
        this.bitcoinCore = bitcoinCore;
    }

    public void setStorageRepo(StorageRepo storageRepo) {
        this.storageRepo = storageRepo;
    }
}
