package sjq.bitcoin.orm.transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
	
	private DataSource dataSource;

	public void initManager(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void beginTransaction() throws SQLException {
		Connection connection = this.dataSource.getConnection();
		connection.setAutoCommit(false);
	}
	
	public void commitTransaction() throws SQLException {
		Connection connection = this.dataSource.getConnection();
		connection.commit();
	}
	
	public void rollbackTransaction() throws SQLException {
		Connection connection = this.dataSource.getConnection();
		connection.rollback();
	}
	
	public void setInitialCommit() throws SQLException {
		Connection connection = this.dataSource.getConnection();
		connection.setAutoCommit(true);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
