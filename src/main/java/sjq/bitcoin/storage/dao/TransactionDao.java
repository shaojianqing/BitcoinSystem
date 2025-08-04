package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.service.data.page.Page;
import sjq.bitcoin.storage.domain.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionDao {

    private final static String QUERY_TRANSACTION_BY_VERIFY_STATUS
            = "sjq.bitcoin.storage.domain.Transaction.queryTransactionByVerifyStatus";

    private final static String COUNT_TRANSACTION = "sjq.bitcoin.storage.domain.Transaction.countTransaction";

    private final static String SAVE_TRANSACTION = "sjq.bitcoin.storage.domain.Transaction.saveTransaction";

    private final static String UPDATE_TRANSACTION_VERIFY_STATUS
            = "sjq.bitcoin.storage.domain.Transaction.updateTransactionVerifyStatus";

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    public Transaction getTransactionByTrxHash(String transactionHash) {
        return null;
    }

    public Page<Transaction> queryTransactionPageByBlockHash(String blockHash) {
        return null;
    }

    public List<Transaction> queryTransactionByVerifyStatus(String status) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("verifyStatus", status);
        return (List<Transaction>)sqlMapClientTemplate.queryForList(QUERY_TRANSACTION_BY_VERIFY_STATUS, param);
    }

    public boolean existTransaction(Transaction transaction) throws Exception {
        Long count = (Long)sqlMapClientTemplate.queryForObject(COUNT_TRANSACTION, transaction);
        return (count == 1);
    }

    public boolean updateTransactionVerifyStatus(
            String transactionHash, String oldVerifyStatus, String newVerifyStatus) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("transactionHash", transactionHash);
        param.put("oldVerifyStatus", oldVerifyStatus);
        param.put("newVerifyStatus", newVerifyStatus);
        int count = sqlMapClientTemplate.execute(UPDATE_TRANSACTION_VERIFY_STATUS, param);
        return count == 1;
    }

    public boolean saveTransaction(Transaction transaction) throws Exception {
        int count = sqlMapClientTemplate.execute(SAVE_TRANSACTION, transaction);
        return count == 1;
    }
}
