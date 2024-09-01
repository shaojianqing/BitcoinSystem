package sjq.bitcoin.storage.dao.impl;

import sjq.bitcoin.storage.dao.ITransactionOutputDao;
import sjq.bitcoin.storage.manager.DatabaseManager;

public class TransactionOutputDaoImpl implements ITransactionOutputDao {

    private DatabaseManager databaseManager;

    public TransactionOutputDaoImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
}
