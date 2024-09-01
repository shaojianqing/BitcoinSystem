package sjq.bitcoin.storage.dao.impl;

import sjq.bitcoin.storage.dao.ITransactionAddressMapDao;
import sjq.bitcoin.storage.manager.DatabaseManager;

public class TransactionAddressMapDaoImpl implements ITransactionAddressMapDao {

    private DatabaseManager databaseManager;

    public TransactionAddressMapDaoImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
}
