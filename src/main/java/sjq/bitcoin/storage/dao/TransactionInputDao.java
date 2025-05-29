package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.storage.domain.TransactionInput;

public class TransactionInputDao {

    private final static String SAVE_TRANSACTION_INPUT = "sjq.bitcoin.storage.domain.Transaction.saveTransactionInput";

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    public boolean saveTransactionInput(TransactionInput transactionInput) throws Exception {
        int count = sqlMapClientTemplate.execute(SAVE_TRANSACTION_INPUT, transactionInput);
        return count == 1;
    }
}
