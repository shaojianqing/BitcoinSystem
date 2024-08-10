package sjq.bitcoin.storage;

public class StorageRepo {

    private StorageRepo() {

    }

    public static StorageRepo build() {
        return new StorageRepo();
    }
}
