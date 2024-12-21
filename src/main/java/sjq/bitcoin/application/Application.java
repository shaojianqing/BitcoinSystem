package sjq.bitcoin.application;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.context.Context;
import sjq.bitcoin.core.BitcoinCore;
import sjq.bitcoin.graphics.GuiSystem;
import sjq.bitcoin.server.APIServer;

public class Application {

    @Autowire
    private GuiSystem guiSystem;

    @Autowire
    private APIServer apiServer;

    @Autowire
    private BitcoinCore bitcoinCore;

    public void initialize() {
        bitcoinCore.initialize();
    }

    public void start() {
        this.guiSystem.start();
        this.apiServer.start();
        this.bitcoinCore.start();
    }

    public static void main(String[] args){
        Application application = Context.build(Application.class);
        application.initialize();
        application.start();
    }
}
