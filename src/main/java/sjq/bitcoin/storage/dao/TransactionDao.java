package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.service.data.page.Page;
import sjq.bitcoin.storage.domain.Transaction;

public class TransactionDao {

    private final static String SAVE_TRANSACTION = "sjq.bitcoin.storage.domain.Transaction.saveTransaction";

    private final static String COUNT_TRANSACTION = "sjq.bitcoin.storage.domain.Transaction.countTransaction";

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    public Transaction getTransactionByTrxHash(String transactionHash) {
        return null;
    }

    public Page<Transaction> queryTransactionPageByBlockHash(String blockHash) {
        return null;
    }

    public boolean saveTransaction(Transaction transaction) throws Exception {
        int count = sqlMapClientTemplate.execute(SAVE_TRANSACTION, transaction);
        return count == 1;
    }

    public boolean existTransaction(Transaction transaction) throws Exception {
        Long count = (Long)sqlMapClientTemplate.queryForObject(COUNT_TRANSACTION, transaction);
        return (count == 1);
    }
}
