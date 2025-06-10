package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.storage.domain.TransactionAddress;

public class TransactionAddressDao {

    private final static String SAVE_TRANSACTION_ADDRESS_MAP = "sjq.bitcoin.storage.domain.Transaction.saveTransactionAddressMap";

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    public boolean saveTransactionAddressMap(TransactionAddress transactionAddress) throws Exception {
        if (transactionAddress !=null) {
            int count = sqlMapClientTemplate.execute(SAVE_TRANSACTION_ADDRESS_MAP, transactionAddress);
            return count == 1;
        }
        return false;
    }
}
