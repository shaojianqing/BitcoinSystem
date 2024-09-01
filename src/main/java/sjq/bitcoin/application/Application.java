package sjq.bitcoin.application;

import sjq.bitcoin.core.BitcoinCore;
import sjq.bitcoin.graphics.GuiSystem;
import sjq.bitcoin.server.APIServer;
import sjq.bitcoin.storage.StorageRepo;

public class Application {

    private GuiSystem guiSystem;

    private APIServer apiServer;

    private BitcoinCore bitcoinCore;

    private StorageRepo storageRepo;

    public Application() {
        this.apiServer = APIServer.build();
        this.guiSystem = GuiSystem.build();
        this.bitcoinCore = BitcoinCore.build();
        this.storageRepo = StorageRepo.build();

        this.apiServer.setStorageRepo(storageRepo);
        this.guiSystem.setBitcoinCore(bitcoinCore);
        this.guiSystem.setStorageRepo(storageRepo);
        this.bitcoinCore.setStorageRepo(storageRepo);
    }

    public void start() {
        this.guiSystem.start();
        this.apiServer.start();
        this.bitcoinCore.start();
    }

    public static void main(String[] args){
        Application application = new Application();
        application.start();
    }
}
