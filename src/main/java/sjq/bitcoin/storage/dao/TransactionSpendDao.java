package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.storage.domain.TransactionSpend;

public class TransactionSpendDao {

    private final static String SAVE_TRANSACTION_SPEND = "sjq.bitcoin.storage.domain.Transaction.saveTransactionSpend";

    private final static String COUNT_TRANSACTION_SPEND = "sjq.bitcoin.storage.domain.Transaction.countTransactionSpend";

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    public boolean saveTransactionSpend(TransactionSpend transactionSpend) throws Exception {
        if (transactionSpend != null) {
            int count = sqlMapClientTemplate.execute(SAVE_TRANSACTION_SPEND, transactionSpend);
            return count == 1;
        }
        return false;
    }

    public boolean existTransactionSpend(TransactionSpend transactionSpend) throws Exception {
        Long count = (Long)sqlMapClientTemplate.queryForObject(COUNT_TRANSACTION_SPEND, transactionSpend);
        return (count == 1);
    }
}
