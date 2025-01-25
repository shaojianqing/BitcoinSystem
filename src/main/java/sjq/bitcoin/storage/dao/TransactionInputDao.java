package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;

public class TransactionInputDao {

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;
}
