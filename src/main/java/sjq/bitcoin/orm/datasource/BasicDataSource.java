package sjq.bitcoin.orm.datasource;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class BasicDataSource implements DataSource {
	
	private Connection connection;
	
	private PrintWriter printWriter;
	
	private Logger logger;
	
	private int loginTimeout;
	
	private String driver;
	
	private String url;
	
	private String username;
	
	private String password;
	
	public BasicDataSource() {
		this.printWriter = new PrintWriter(System.out);
		this.logger = Logger.getLogger(BasicDataSource.class.getName()); 
	}

	public PrintWriter getLogWriter() throws SQLException {
		return this.printWriter;
	}

	public void setLogWriter(PrintWriter writer) throws SQLException {
		this.printWriter = writer;
	}

	public void setLoginTimeout(int seconds) throws SQLException {
		this.loginTimeout = seconds;
	}

	public int getLoginTimeout() throws SQLException {
		return this.loginTimeout;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return this.logger;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	public Connection getConnection() throws SQLException {
		if (connection==null) {
			try {
				Class.forName(driver);
				connection = DriverManager.getConnection(url , username , password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	public Connection getConnection(String username, String password) throws SQLException {
		return null;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
