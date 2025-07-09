package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.storage.domain.TransactionInput;

public class TransactionInputDao {

    private final static String SAVE_TRANSACTION_INPUT = "sjq.bitcoin.storage.domain.Transaction.saveTransactionInput";

    private final static String COUNT_TRANSACTION_INPUT = "sjq.bitcoin.storage.domain.Transaction.countTransactionInput";

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    public boolean saveTransactionInput(TransactionInput transactionInput) throws Exception {
        int count = sqlMapClientTemplate.execute(SAVE_TRANSACTION_INPUT, transactionInput);
        return count == 1;
    }

    public boolean existTransactionInput(TransactionInput transactionInput) throws Exception {
        Long count = (Long)sqlMapClientTemplate.queryForObject(COUNT_TRANSACTION_INPUT, transactionInput);
        return (count == 1);
    }
}
