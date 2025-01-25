package sjq.bitcoin.storage.manager;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String DATABASE_URL = "BITCOIN_DATABASE_URL";

    private static final String DB_USERNAME = "BITCOIN_USERNAME";

    private static final String DB_PASSWORD = "BITCOIN_PASSWORD";

    private static final String DATABASE_NAME = "BitcoinDB";

    private static final String CONN_FORMAT = "jdbc:mysql://%s:3306/%s";

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private BasicDataSource dataSource;

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    private List<String> sqlMapConfigList;

    public void initialize() {
        try {
            initDatasource();
            initSqlMapConfigList();
            initSqlMapClientTemplate();
        } catch (Exception e) {
            Logger.fatal("database initialization fatal error:%s", e);
            System.exit(-1);
        }
    }

    private void initDatasource() {
        String dbUrl = System.getenv(DATABASE_URL);
        String username = System.getenv(DB_USERNAME);
        String password = System.getenv(DB_PASSWORD);

        checkDatabaseConfiguration(dbUrl, username, password);

        String connectionString = String.format(CONN_FORMAT, dbUrl, DATABASE_NAME);

        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(JDBC_DRIVER);
        dataSource.setUrl(connectionString);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);
        dataSource.setMaxIdle(10);
        dataSource.setMinIdle(2);
    }

    private void initSqlMapConfigList() {
        sqlMapConfigList = new ArrayList<>();
        sqlMapConfigList.add("configuration/mapper/Block.xml");
        sqlMapConfigList.add("configuration/mapper/Transaction.xml");
        sqlMapConfigList.add("configuration/mapper/TransactionAddressMap.xml");
        sqlMapConfigList.add("configuration/mapper/TransactionInput.xml");
        sqlMapConfigList.add("configuration/mapper/TransactionOutput.xml");
    }

    private void initSqlMapClientTemplate() throws Exception {
        sqlMapClientTemplate.initTemplate(dataSource, sqlMapConfigList);
    }

    private void checkDatabaseConfiguration(String dbUrl, String username, String password) {
        if (StringUtils.isAnyBlank(dbUrl, username, password)) {
            Logger.fatal("database configuration is illegal or blank, " +
                    "dbUrl:%s, username:%s, password:%s", dbUrl, username, password);
            System.exit(-1);
        }
    }
}
