package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.storage.domain.TransactionAddressMap;

public class TransactionAddressMapDao {

    private final static String SAVE_TRANSACTION_ADDRESS_MAP = "sjq.bitcoin.storage.domain.Transaction.saveTransactionAddressMap";

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    public boolean saveTransactionAddressMap(TransactionAddressMap transactionAddressMap) throws Exception {
        if (transactionAddressMap!=null) {
            int count = sqlMapClientTemplate.execute(SAVE_TRANSACTION_ADDRESS_MAP, transactionAddressMap);
            return count == 1;
        }
        return false;
    }
}
