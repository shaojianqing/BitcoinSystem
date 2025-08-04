package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.storage.domain.TransactionOutput;

import java.util.HashMap;
import java.util.Map;

public class TransactionOutputDao {

    private final static String QUERY_TRANSACTION_OUTPUT_BY_TRANSACTION_INPUT =
            "sjq.bitcoin.storage.domain.Transaction.queryTransactionOutputByTransactionInput";

    private final static String COUNT_TRANSACTION_OUTPUT = "sjq.bitcoin.storage.domain.Transaction.countTransactionOutput";

    private final static String SAVE_TRANSACTION_OUTPUT = "sjq.bitcoin.storage.domain.Transaction.saveTransactionOutput";

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    public TransactionOutput queryTransactionOutputByTransactionInput(
            String fromTransactionHash, Long transactionOutputIndex) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("fromTransactionHash", fromTransactionHash);
        param.put("transactionOutputIndex", transactionOutputIndex);
        return  (TransactionOutput)sqlMapClientTemplate.queryForObject(QUERY_TRANSACTION_OUTPUT_BY_TRANSACTION_INPUT, param);
    }

    public boolean saveTransactionOutput(TransactionOutput transactionOutput) throws Exception {
        int count = sqlMapClientTemplate.execute(SAVE_TRANSACTION_OUTPUT, transactionOutput);
        return count == 1;
    }

    public boolean existTransactionOutput(TransactionOutput transactionOutput) throws Exception {
        Long count = (Long)sqlMapClientTemplate.queryForObject(COUNT_TRANSACTION_OUTPUT, transactionOutput);
        return (count == 1);
    }
}
