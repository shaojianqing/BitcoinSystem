package sjq.bitcoin.storage.dao;

import sjq.bitcoin.storage.data.Page;
import sjq.bitcoin.storage.domain.Transaction;

public interface ITransactionDao {

    Transaction getTransactionByTrxHash(String transactionHash);

    Page<Transaction> queryTransactionPageByBlockhash(String blockHash);

    boolean saveTransaction(Transaction transaction);
}
