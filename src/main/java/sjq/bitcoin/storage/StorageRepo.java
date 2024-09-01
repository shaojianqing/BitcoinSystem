package sjq.bitcoin.storage;

import sjq.bitcoin.configuration.NetworkConfiguration;
import sjq.bitcoin.storage.dao.*;
import sjq.bitcoin.storage.dao.impl.*;
import sjq.bitcoin.storage.manager.DatabaseManager;

public class StorageRepo {

    private static StorageRepo Instance;

    private NetworkConfiguration configuration;

    private DatabaseManager databaseManager;

    private IBlockDao blockDao;

    private ITransactionDao transactionDao;

    private ITransactionInputDao transactionInputDao;

    private ITransactionOutputDao transactionOutputDao;

    private ITransactionBlockMapDao transactionBlockMapDao;

    private ITransactionAddressMapDao transactionAddressMapDao;

    private StorageRepo() {
        this.configuration = NetworkConfiguration.getConfiguration();
        this.databaseManager = new DatabaseManager(configuration);
        this.blockDao = new BlockDaoImpl(databaseManager);
        this.transactionDao = new TransactionDaoImpl(databaseManager);
        this.transactionInputDao = new TransactionInputDaoImpl(databaseManager);
        this.transactionOutputDao = new TransactionOutputDaoImpl(databaseManager);
        this.transactionBlockMapDao = new TransactionBlockMapDaoImpl(databaseManager);
        this.transactionAddressMapDao = new TransactionAddressMapDaoImpl(databaseManager);

        this.databaseManager.initDatabaseManager();
    }

    public static StorageRepo build() {
        if (Instance == null) {
            Instance = new StorageRepo();
        }
        return Instance;
    }
}
