package sjq.bitcoin.storage.dao.impl;

import sjq.bitcoin.storage.dao.ITransactionBlockMapDao;
import sjq.bitcoin.storage.manager.DatabaseManager;

public class TransactionBlockMapDaoImpl implements ITransactionBlockMapDao {

    private DatabaseManager databaseManager;

    public TransactionBlockMapDaoImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
}
