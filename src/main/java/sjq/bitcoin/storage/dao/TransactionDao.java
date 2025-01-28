package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.service.data.page.Page;
import sjq.bitcoin.storage.domain.Transaction;

public class TransactionDao {

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    public Transaction getTransactionByTrxHash(String transactionHash) {
        return null;
    }

    public Page<Transaction> queryTransactionPageByBlockHash(String blockHash) {
        return null;
    }

    public boolean saveTransaction(Transaction transaction) {
        return false;
    }
}
