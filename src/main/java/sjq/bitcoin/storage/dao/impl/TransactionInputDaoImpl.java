package sjq.bitcoin.storage.dao.impl;

import sjq.bitcoin.storage.dao.ITransactionInputDao;
import sjq.bitcoin.storage.manager.DatabaseManager;

public class TransactionInputDaoImpl implements ITransactionInputDao {

    private DatabaseManager databaseManager;

    public TransactionInputDaoImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
}
