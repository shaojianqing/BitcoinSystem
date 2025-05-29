package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.storage.domain.TransactionOutput;

public class TransactionOutputDao {

    private final static String SAVE_TRANSACTION_OUTPUT = "sjq.bitcoin.storage.domain.Transaction.saveTransactionOutput";

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    public boolean saveTransactionOutput(TransactionOutput transactionOutput) throws Exception {
        int count = sqlMapClientTemplate.execute(SAVE_TRANSACTION_OUTPUT, transactionOutput);
        return count == 1;
    }
}
