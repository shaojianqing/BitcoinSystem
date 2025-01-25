package sjq.bitcoin.orm.statement;

import org.apache.commons.lang3.StringUtils;
import sjq.bitcoin.orm.callback.SqlQueryCallback;
import sjq.bitcoin.orm.datamap.ResultDataMap;
import sjq.bitcoin.orm.utility.BeanUtil;
import sjq.bitcoin.orm.utility.SqlTemplateUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class SqlStatement {
	
	private DataSource dataSource;
	
	private String sqlTemplateString;
	
	private ResultDataMap resultDataMap;
	
	private String parameterType;
	
	private String resultType;
	
	public SqlStatement(String sqlTemplateString, DataSource dataSource) {
		this.sqlTemplateString = sqlTemplateString;
		this.dataSource = dataSource;
	}
	
	public Object executeQuerySql(Object parameter, SqlQueryCallback callback) throws Exception {
		String realSqlString = parseQuerySql(parameter);
		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(realSqlString);
		return callback.onExecuteResult(resultSet);
	}
	
	public int executeUpdateSql(Object parameter) throws Exception {
		String realSqlString = parseUpdateSql(parameter);
		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		return statement.executeUpdate(realSqlString);
	}

	@SuppressWarnings("unchecked")
	private String parseQuerySql(Object parameter) throws Exception {
		if (parameter!=null) {
			Map<String, Object> paramMap = null;
			if ((!(parameter instanceof Map)) && (!(parameter instanceof String)) ) {
				paramMap = BeanUtil.objectToMap(parameter);
			} else if (parameter instanceof Map) {
				paramMap = (Map<String, Object>)parameter;
			} else if (parameter instanceof String) {
				String key = SqlTemplateUtil.retriveFirstKey(sqlTemplateString);
				paramMap = new HashMap<String, Object>();
				paramMap.put(key, parameter);
			} else {
				paramMap = new HashMap<String, Object>();
			}
			
			Map<String, Object> replaceParamMap = new HashMap<String, Object>();
			Set<String> keySet = paramMap.keySet();
			for (String key:keySet) {
				String newKey = String.format("#%s#", key);
				Object value = paramMap.get(key);
				String realValue = String.valueOf(value);
				realValue = String.format("'%s'", realValue);
				replaceParamMap.put(newKey, realValue);
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
				String key = SqlTemplateUtil.retriveFirstKey(sqlTemplateString);
				paramMap = new HashMap<String, Object>();
				paramMap.put(key, parameter);
			} else {
				paramMap = new HashMap<String, Object>();
			}
			
			Map<String, Object> replaceParamMap = new HashMap<String, Object>();
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

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
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

	public static void main(String args[]) throws Exception {
		String sql = "SELECT * FROM CL_LOAN_REPAY WHERE user_id = #userId# and out_order_no = #outOrderNo# and username = #name#";
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("userId", "208080908230932");
		param.put("outOrderNo", "2222228888877777");
		param.put("name", "shaojianqing");
		
		SqlStatement statement = new SqlStatement(sql, null);
		statement.executeQuerySql(param, null);		
	}
}
