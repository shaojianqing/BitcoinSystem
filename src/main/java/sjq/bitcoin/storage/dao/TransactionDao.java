package sjq.bitcoin.storage.dao;

import sjq.bitcoin.storage.data.page.Page;
import sjq.bitcoin.storage.domain.Transaction;

public class TransactionDao {

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
