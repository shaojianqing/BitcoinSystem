package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;

public class TransactionAddressMapDao {

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;
}
