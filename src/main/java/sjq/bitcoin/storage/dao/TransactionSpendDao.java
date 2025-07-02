package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.storage.domain.TransactionSpend;

public class TransactionSpendDao {

    private final static String SAVE_TRANSACTION_SPEND_MAP = "sjq.bitcoin.storage.domain.Transaction.saveTransactionSpendMap";

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    public boolean saveTransactionSpend(TransactionSpend transactionSpend) throws Exception {
        if (transactionSpend != null) {
            int count = sqlMapClientTemplate.execute(SAVE_TRANSACTION_SPEND_MAP, transactionSpend);
            return count == 1;
        }
        return false;
    }
}
