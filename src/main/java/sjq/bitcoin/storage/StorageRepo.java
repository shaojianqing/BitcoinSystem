package sjq.bitcoin.storage;

public class StorageRepo {

    private static StorageRepo Instance;

    private StorageRepo() {

    }

    public static StorageRepo build() {
        if (Instance == null) {
            Instance = new StorageRepo();
        }
        return Instance;
    }
}
