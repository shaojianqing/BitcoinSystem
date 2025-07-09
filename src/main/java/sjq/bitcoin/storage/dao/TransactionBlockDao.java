package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.storage.domain.TransactionBlock;

public class TransactionBlockDao {

    private final static String SAVE_TRANSACTION_BLOCK = "sjq.bitcoin.storage.domain.Transaction.saveTransactionBlock";

    private final static String COUNT_TRANSACTION_BLOCK = "sjq.bitcoin.storage.domain.Transaction.countTransactionBlock";

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    public boolean saveTransactionBlock(TransactionBlock transactionBlock) throws Exception {
        int count = sqlMapClientTemplate.execute(SAVE_TRANSACTION_BLOCK, transactionBlock);
        return count == 1;
    }

    public boolean existTransactionBlock(TransactionBlock transactionBlock) throws Exception {
        Long count = (Long)sqlMapClientTemplate.queryForObject(COUNT_TRANSACTION_BLOCK, transactionBlock);
        return (count == 1);
    }
}
