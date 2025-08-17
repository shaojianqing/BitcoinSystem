package sjq.bitcoin.orm.statement;

import org.apache.commons.lang3.StringUtils;
import sjq.bitcoin.orm.callback.SqlQueryCallback;
import sjq.bitcoin.orm.datamap.ResultDataMap;
import sjq.bitcoin.orm.utility.BeanUtil;
import sjq.bitcoin.orm.utility.SqlTemplateUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class SqlStatement {
	
	private String sqlTemplateString;
	
	private ResultDataMap resultDataMap;
	
	private String parameterType;
	
	private String resultType;
	
	public SqlStatement(String sqlTemplateString) throws SQLException {
		this.sqlTemplateString = sqlTemplateString;
	}
	
	public Object executeQuerySql(DataSource datasource, Object parameter, SqlQueryCallback callback) throws Exception {
		String realSqlString = parseQuerySql(parameter);
		Connection connection = datasource.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(realSqlString);
		Object result = callback.onExecuteResult(resultSet);
		resultSet.close();
		statement.close();
		connection.close();
		return result;
	}
	
	public int executeUpdateSql(DataSource datasource, Object parameter) throws Exception {
		String realSqlString = parseUpdateSql(parameter);
		Connection connection = datasource.getConnection();
		Statement statement = connection.createStatement();
		int result = statement.executeUpdate(realSqlString);
		statement.close();
		connection.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	private String parseQuerySql(Object parameter) throws Exception {
		if (parameter!=null) {
			Map<String, Object> paramMap;
			if ((!(parameter instanceof Map)) && (!(parameter instanceof String)) ) {
				paramMap = BeanUtil.objectToMap(parameter);
			} else if (parameter instanceof Map) {
				paramMap = (Map<String, Object>)parameter;
			} else {
				String key = SqlTemplateUtil.retrieveFirstKey(sqlTemplateString);
				paramMap = new HashMap<>();
				paramMap.put(key, parameter);
			}
			
			Map<String, Object> replaceParamMap = new HashMap<>();
			Set<String> keySet = paramMap.keySet();
			for (String key:keySet) {
				String newKey1 = String.format("#%s#", key);
				Object value1 = paramMap.get(key);
				String realValue1 = String.valueOf(value1);
				realValue1 = String.format("'%s'", realValue1);
				replaceParamMap.put(newKey1, realValue1);

				String newKey2 = String.format("$%s$", key);
				Object value2 = paramMap.get(key);
				String realValue2 = String.valueOf(value2);
				realValue2 = String.format("%s", realValue2);
				replaceParamMap.put(newKey2, realValue2);
			}
			
			List<String> sqlElementList = SqlTemplateUtil.split2List(sqlTemplateString);
			StringBuilder sqlBuilder = new StringBuilder();
			for (String element:sqlElementList) {
				if (replaceParamMap.containsKey(element)) {
					Object elementValue = replaceParamMap.get(element);
					sqlBuilder.append(elementValue);
				} else {
					sqlBuilder.append(element);
				}
			}
			
			return sqlBuilder.toString();
		} else {
			return sqlTemplateString;
		}
	}
	
	@SuppressWarnings("unchecked")
	private String parseUpdateSql(Object parameter) throws Exception {
		if (parameter!=null) {
			Map<String, Object> paramMap = null;
			if ((!(parameter instanceof Map)) && (!(parameter instanceof String)) ) {
				paramMap = BeanUtil.objectToMap(parameter);
			} else if (parameter instanceof Map) {
				paramMap = (Map<String, Object>)parameter;
			} else if (parameter instanceof String) {
				String key = SqlTemplateUtil.retrieveFirstKey(sqlTemplateString);
				paramMap = new HashMap<>();
				paramMap.put(key, parameter);
			} else {
				paramMap = new HashMap<>();
			}
			
			Map<String, Object> replaceParamMap = new HashMap<>();
			Set<String> keySet = paramMap.keySet();
			for (String key:keySet) {
				String newKey = String.format("#%s#", key);
				Object value = paramMap.get(key);
				if (value!=null) {
					String realValue = String.valueOf(value);
					realValue = String.format("'%s'", realValue);
					replaceParamMap.put(newKey, realValue);
				} else {
					replaceParamMap.put(newKey, null);
				}
			}
			
			List<String> sqlElementList = SqlTemplateUtil.split2List(sqlTemplateString);
			Set<String> sqlKeySet = new HashSet<String>();
			for (String element:sqlElementList) {
				if (isSqlStatementKey(element)) {
					sqlKeySet.add(element);
				}
			}

			StringBuilder sqlBuilder = new StringBuilder();
			for (String element:sqlElementList) {
				if (sqlKeySet.contains(element)) {
					if (replaceParamMap.containsKey(element)) {
						Object elementValue = replaceParamMap.get(element);
						if (elementValue!=null) {
							sqlBuilder.append(elementValue);
						} else {
							sqlBuilder.append("NULL");
						}
					} else {
						sqlBuilder.append("NULL");
					}
				} else {
					sqlBuilder.append(element);
				}
			}
			return sqlBuilder.toString();
		} else {
			return sqlTemplateString;
		}
	}
	
	private boolean isSqlStatementKey(String element) {
		if (StringUtils.isNotBlank(element) && element.length()>2) {
			char array[] = element.toCharArray();
			if (array[0]=='#' && array[array.length-1]=='#') {
				return true;
			}
		}
		return false;
	}

	public String getSqlTemplateString() {
		return sqlTemplateString;
	}

	public void setSqlTemplateString(String sqlTemplateString) {
		this.sqlTemplateString = sqlTemplateString;
	}
	
	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public ResultDataMap getResultDataMap() {
		return resultDataMap;
	}

	public void setResultDataMap(ResultDataMap resultDataMap) {
		this.resultDataMap = resultDataMap;
	}
}
