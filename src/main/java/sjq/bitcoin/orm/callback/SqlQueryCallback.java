package sjq.bitcoin.orm.callback;

import java.sql.ResultSet;

public interface SqlQueryCallback {
	
	Object onExecuteResult(ResultSet resultSet) throws Exception;
}
