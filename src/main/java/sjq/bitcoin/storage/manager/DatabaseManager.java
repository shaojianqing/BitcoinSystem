package sjq.bitcoin.storage.manager;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.lang3.StringUtils;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.orm.transaction.TransactionManager;
import sjq.bitcoin.orm.transaction.TransactionTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String DATABASE_URL = "BITCOIN_DATABASE_URL";

    private static final String DB_USERNAME = "BITCOIN_USERNAME";

    private static final String DB_PASSWORD = "BITCOIN_PASSWORD";

    private static final String DATABASE_NAME = "BitcoinDB";

    private static final String CONN_FORMAT = "jdbc:mysql://%s:3306/%s";

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    @Autowire
    private TransactionManager transactionManager;

    @Autowire
    private TransactionTemplate transactionTemplate;

    private List<String> sqlMapConfigList;

    private DataSource dataSource;

    public void initialize() {
        try {
            initDatasource();
            initSqlMapConfigList();
            initSqlMapClientTemplate();
            initTransactionTemplate();
        } catch (Exception e) {
            Logger.fatal("database initialization fatal error:%s", e);
            System.exit(-1);
        }
    }

    private void initDatasource() throws Exception {
        String dbUrl = System.getenv(DATABASE_URL);
        String username = System.getenv(DB_USERNAME);
        String password = System.getenv(DB_PASSWORD);

        checkDatabaseConfiguration(dbUrl, username, password);

        String connectionUrl = String.format(CONN_FORMAT, dbUrl, DATABASE_NAME);

        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL(connectionUrl);
        mysqlDataSource.setUser(username);
        mysqlDataSource.setPassword(password);

        dataSource = mysqlDataSource;
    }

    private void initSqlMapConfigList() {
        sqlMapConfigList = new ArrayList<>();
        sqlMapConfigList.add("configuration/mapper/Block.xml");
        sqlMapConfigList.add("configuration/mapper/Transaction.xml");
        sqlMapConfigList.add("configuration/mapper/TransactionBlock.xml");
        sqlMapConfigList.add("configuration/mapper/TransactionAddress.xml");
        sqlMapConfigList.add("configuration/mapper/TransactionInput.xml");
        sqlMapConfigList.add("configuration/mapper/TransactionOutput.xml");
        sqlMapConfigList.add("configuration/mapper/TransactionWitness.xml");
    }

    private void initSqlMapClientTemplate() throws Exception {
        sqlMapClientTemplate.initTemplate(dataSource, sqlMapConfigList);
    }

    private void initTransactionTemplate() throws Exception {
        transactionManager.initManager(dataSource);
        transactionTemplate.setTransactionManager(transactionManager);
    }

    private void checkDatabaseConfiguration(String dbUrl, String username, String password) {
        if (StringUtils.isAnyBlank(dbUrl, username, password)) {
            Logger.fatal("database configuration is illegal or blank, " +
                    "dbUrl:%s, username:%s, password:%s", dbUrl, username, password);
            System.exit(-1);
        }
    }
}
