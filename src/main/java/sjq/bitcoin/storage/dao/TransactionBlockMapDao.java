package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.storage.domain.TransactionBlockMap;

public class TransactionBlockMapDao {

    private final static String SAVE_TRANSACTION_BLOCK_MAP = "sjq.bitcoin.storage.domain.Transaction.saveTransactionBlockMap";

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    public boolean saveTransactionBlockMap(TransactionBlockMap transactionBlockMap) throws Exception {
        int count = sqlMapClientTemplate.execute(SAVE_TRANSACTION_BLOCK_MAP, transactionBlockMap);
        return count == 1;
    }
}
