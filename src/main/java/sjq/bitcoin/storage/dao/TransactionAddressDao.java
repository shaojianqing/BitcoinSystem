package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.storage.domain.TransactionAddress;

public class TransactionAddressDao {

    private final static String SAVE_TRANSACTION_ADDRESS = "sjq.bitcoin.storage.domain.Transaction.saveTransactionAddress";

    private final static String COUNT_TRANSACTION_ADDRESS = "sjq.bitcoin.storage.domain.Transaction.countTransactionAddress";

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    public boolean saveTransactionAddress(TransactionAddress transactionAddress) throws Exception {
        if (transactionAddress !=null) {
            int count = sqlMapClientTemplate.execute(SAVE_TRANSACTION_ADDRESS, transactionAddress);
            return count == 1;
        }
        return false;
    }

    public boolean existTransactionAddress(TransactionAddress transactionAddress) throws Exception {
        Long count = (Long)sqlMapClientTemplate.queryForObject(COUNT_TRANSACTION_ADDRESS, transactionAddress);
        return (count == 1);
    }
}
