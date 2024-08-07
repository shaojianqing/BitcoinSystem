package sjq.bitcoin.storage.dao;

public class StorageRepo {

    private StorageRepo() {

    }

    public static StorageRepo build() {
        return new StorageRepo();
    }
}
