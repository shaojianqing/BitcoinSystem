package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.storage.domain.TransactionOutput;

public class TransactionOutputDao {

    private final static String SAVE_TRANSACTION_OUTPUT = "sjq.bitcoin.storage.domain.Transaction.saveTransactionOutput";

    private final static String COUNT_TRANSACTION_OUTPUT = "sjq.bitcoin.storage.domain.Transaction.countTransactionOutput";

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    public boolean saveTransactionOutput(TransactionOutput transactionOutput) throws Exception {
        int count = sqlMapClientTemplate.execute(SAVE_TRANSACTION_OUTPUT, transactionOutput);
        return count == 1;
    }

    public boolean existTransactionOutput(TransactionOutput transactionOutput) throws Exception {
        Long count = (Long)sqlMapClientTemplate.queryForObject(COUNT_TRANSACTION_OUTPUT, transactionOutput);
        return (count == 1);
    }
}
