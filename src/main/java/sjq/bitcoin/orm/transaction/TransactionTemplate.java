package sjq.bitcoin.orm.transaction;

import java.sql.SQLException;

public class TransactionTemplate {
	
	private TransactionManager transactionManager;
	
	public <T> T execute(TransactionCallback<T> callback) throws Exception {
		T result = null;
		try {
			transactionManager.beginTransaction();
			result = callback.doTransaction();
			transactionManager.commitTransaction();
		} catch (Exception e) {
			try {
				transactionManager.rollbacTransaction();
				transactionManager.setInitialCommit();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw e1;
			}
			throw e;
		}
		return result;
	}

	public TransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
}