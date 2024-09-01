package sjq.bitcoin.storage.dao.impl;

import sjq.bitcoin.storage.dao.ITransactionDao;
import sjq.bitcoin.storage.data.Page;
import sjq.bitcoin.storage.domain.Transaction;
import sjq.bitcoin.storage.manager.DatabaseManager;

public class TransactionDaoImpl implements ITransactionDao {

    private DatabaseManager databaseManager;

    public TransactionDaoImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public Transaction getTransactionByTrxHash(String transactionHash) {
        return null;
    }

    public Page<Transaction> queryTransactionPageByBlockhash(String blockHash) {
        return null;
    }

    public boolean saveTransaction(Transaction transaction) {
        return false;
    }
}
