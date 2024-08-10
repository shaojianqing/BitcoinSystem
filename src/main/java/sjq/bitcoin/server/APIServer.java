package sjq.bitcoin.server;

import sjq.bitcoin.storage.StorageRepo;

public class APIServer {

    private StorageRepo storageRepo;

    private APIServer(){

    }


    public static APIServer build() {
        return new APIServer();
    }

    public void start() {

    }

    public void setStorageRepo(StorageRepo storageRepo) {
        this.storageRepo = storageRepo;
    }
}
