package sjq.bitcoin.application;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.context.Context;
import sjq.bitcoin.core.BitcoinCore;
import sjq.bitcoin.gui.GuiSystem;
import sjq.bitcoin.server.APIServer;
import sjq.bitcoin.storage.manager.DatabaseManager;
import sjq.bitcoin.wallet.WalletCore;

public class BitcoinSystem {

    @Autowire
    private GuiSystem guiSystem;

    @Autowire
    private APIServer apiServer;

    @Autowire
    private BitcoinCore bitcoinCore;

    @Autowire
    private WalletCore walletCore;

    @Autowire
    private DatabaseManager databaseManager;

    private void initialize() {
        this.bitcoinCore.initialize();
        this.databaseManager.initialize();
        this.guiSystem.initialize();
    }

    private void start() {
        this.guiSystem.start();
        this.apiServer.start();
        this.bitcoinCore.start();
    }

    public static void main(String[] args) {
        BitcoinSystem bitcoinSystem = Context.build(BitcoinSystem.class);
        bitcoinSystem.initialize();
        bitcoinSystem.start();
    }
}
