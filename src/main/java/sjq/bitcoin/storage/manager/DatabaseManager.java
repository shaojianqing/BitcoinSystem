package sjq.bitcoin.storage.manager;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import sjq.bitcoin.configuration.NetworkConfiguration;
import sjq.bitcoin.logger.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DATABASE_URL = "BITCOIN_DATABASE_URL";

    private static final String DB_USERNAME = "BITCOIN_USERNAME";

    private static final String DB_PASSWORD = "BITCOIN_PASSWORD";

    private static final String DATABASE_NAME = "bitcoin";

    private static final String CONN_FORMAT = "jdbc:mysql://%s:3306/%s";

    private BasicDataSource dataSource;

    public void initDatabaseManager() {
        String dbUrl = System.getenv(DATABASE_URL);
        String username = System.getenv(DB_USERNAME);
        String password = System.getenv(DB_PASSWORD);

        checkDatabaseConfiguration(dbUrl, username, password);

        String connectionString = String.format(CONN_FORMAT, dbUrl, DATABASE_NAME);

        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(connectionString);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);
        dataSource.setMaxIdle(10);
        dataSource.setMinIdle(2);
    }

    private void checkDatabaseConfiguration(String dbUrl, String username, String password) {
        if (StringUtils.isAnyBlank(dbUrl, username, password)) {
            Logger.fatal("database configuration is illegal or blank, " +
                    "dbUrl:%s, username:%s, password:%s", dbUrl, username, password);
            System.exit(-1);
        }
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public int executeUpdate(String sql) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        return statement.executeUpdate(sql);
    }
}
