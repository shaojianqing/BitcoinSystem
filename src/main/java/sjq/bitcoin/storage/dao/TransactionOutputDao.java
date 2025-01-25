package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;

public class TransactionOutputDao {

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;
}
