package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.storage.domain.TransactionWitness;

public class TransactionWitnessDao {

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    public boolean saveTransactionWitness(TransactionWitness transactionWitness) {
        return false;
    }

    public boolean exist(TransactionWitness transactionWitness) {
        return false;
    }
}
