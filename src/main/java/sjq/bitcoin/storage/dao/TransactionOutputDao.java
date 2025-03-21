package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.storage.domain.TransactionOutput;

public class TransactionOutputDao {

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    public boolean saveTransactionOutput(TransactionOutput transactionOutput) {
        return false;
    }
}
