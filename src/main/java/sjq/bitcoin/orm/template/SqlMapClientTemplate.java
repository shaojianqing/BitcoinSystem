package sjq.bitcoin.orm.template;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.orm.datamap.PropertyMap;
import sjq.bitcoin.orm.datamap.ResultDataMap;
import sjq.bitcoin.orm.exception.SqlTemplateException;
import sjq.bitcoin.orm.parser.SqlMapConfigParser;
import sjq.bitcoin.orm.statement.SqlStatement;
import sjq.bitcoin.orm.utility.ClassUtil;
import sjq.bitcoin.orm.utility.TypeUtil;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@SuppressWarnings("rawtypes")
public class SqlMapClientTemplate {
	
	private final SqlMapConfigParser parser = new SqlMapConfigParser();
	
	private final Map<String, SqlStatement> statementMap = new HashMap<>();

	private DataSource dataSource;
	
	public void initTemplate(DataSource dataSource, List<String> mapperList) throws Exception {
		Logger.info("start to initialize sql map client template.");
		if (mapperList!=null && mapperList.size()>0) {			
			for (String mapperConfigPath:mapperList) {
				InputStream inputStream = getClass().getClassLoader().getResourceAsStream(mapperConfigPath);
				SAXReader xmlReader = new SAXReader();
		        
		        Document document = xmlReader.read(inputStream);
		        Element root = document.getRootElement();

				this.dataSource = dataSource;
		        this.parser.buildSqlMapConfig(statementMap, root);
			}
		}
		Logger.info("initialize sql map client template successfully!");
	}
	
	public List queryForList(String statementName) throws Exception {
		return queryForList(statementName, null);
	}
	
	public List queryForList(String statementName, Object parameter) throws Exception {
		if (statementMap.containsKey(statementName)) {
			final SqlStatement sqlStatement = statementMap.get(statementName);
			return (List)sqlStatement.executeQuerySql(dataSource, parameter, resultSet -> {
				ResultDataMap resultDataMap = sqlStatement.getResultDataMap();
				String className = resultDataMap.getClassName();
				List resultList = new ArrayList();

				if (StringUtils.isNotBlank(className)) {
					Class<?> clazz = Class.forName(className);
					Map<String, Method> methodMap = ClassUtil.prepareMethodMap(clazz);
					List<PropertyMap> propertyMapList = resultDataMap.getPropertyList();
					while (resultSet.next()) {
						Object data = clazz.newInstance();
						for (PropertyMap propertyMap : propertyMapList) {
							prepareDataValue(resultSet, data, methodMap, propertyMap);
						}
						resultList.add(data);
					}
					return resultList;
				} else {
					throw new SqlTemplateException("Can not find Bean Class Name Definition!");
				}
			});
		} else {
			String message = String.format("%s statement does not exist!", statementName);
			throw new SqlTemplateException(message);
		}
	}
	
	public Object queryForObject(String statementName) throws Exception {		
		return queryForObject(statementName, null);
	}
	
	public Object queryForObject(String statementName, Object parameter) throws Exception {
		if (statementMap.containsKey(statementName)) {
			final SqlStatement sqlStatement = statementMap.get(statementName);

			return sqlStatement.executeQuerySql(dataSource, parameter, resultSet -> {
				ResultDataMap resultDataMap = sqlStatement.getResultDataMap();
				String className;
				if (resultDataMap != null) {
					className = resultDataMap.getClassName();
				} else if (StringUtils.isNotBlank(sqlStatement.getResultType())) {
					className = sqlStatement.getResultType();
				} else {
					throw new SqlTemplateException("Both ResultDataMap and ResultType are empty, Can not find Bean Class Name Definition!");
				}

				if (resultDataMap != null) {
					Class<?> clazz = Class.forName(className);
					Object data = clazz.newInstance();
					Map<String, Method> methodMap = ClassUtil.prepareMethodMap(clazz);
					List<PropertyMap> propertyMapList = resultDataMap.getPropertyList();
					if (resultSet.next()) {
						for (PropertyMap propertyMap : propertyMapList) {
							prepareDataValue(resultSet, data, methodMap, propertyMap);
						}
						return data;
					}
				} else if (StringUtils.isNotBlank(sqlStatement.getResultType())) {
					Object data = null;
					if (resultSet.next()) {
						data = prepareDataValue(resultSet, className);
					}
					return data;
				}

				return null;
			});
		} else {
			String message = String.format("%s statement does not exist!", statementName);
			throw new SqlTemplateException(message);
		}
	}
	
	public int execute(String statementName, Object parameter) throws Exception {
		if (statementMap.containsKey(statementName)) {
			SqlStatement sqlStatement = statementMap.get(statementName);
			return sqlStatement.executeUpdateSql(dataSource, parameter);
		} else {
			String message = String.format("%s statement does not exist!", statementName);
			throw new SqlTemplateException(message);
		}
	}

	private void prepareDataValue(ResultSet resultSet, Object data, Map<String, Method> methodMap,
			PropertyMap propertyMap) throws SQLException, IllegalAccessException, InvocationTargetException {
		if (TypeUtil.TYPE_STRING.equals(propertyMap.getJavaType())) {
			String value = resultSet.getString(propertyMap.getColumnName());
			setDataValue(value, data, methodMap, propertyMap);
		} else if (TypeUtil.TYPE_DATE.equals(propertyMap.getJavaType())) {
			Date value = resultSet.getDate(propertyMap.getColumnName());
			setDataValue(value, data, methodMap, propertyMap);
		} else if (TypeUtil.TYPE_DOUBLE.equals(propertyMap.getJavaType())) {
			Double value = resultSet.getDouble(propertyMap.getColumnName());
			setDataValue(value, data, methodMap, propertyMap);
		} else if (TypeUtil.TYPE_SHORT.equals(propertyMap.getJavaType())) {
			Short value = resultSet.getShort(propertyMap.getColumnName());
			setDataValue(value, data, methodMap, propertyMap);
		} else if (TypeUtil.TYPE_FLOAT.equals(propertyMap.getJavaType())) {
			Float value = resultSet.getFloat(propertyMap.getColumnName());
			setDataValue(value, data, methodMap, propertyMap);
		} else if (TypeUtil.TYPE_INTEGER.equals(propertyMap.getJavaType())) {
			Integer value = resultSet.getInt(propertyMap.getColumnName());
			setDataValue(value, data, methodMap, propertyMap);
		} else if (TypeUtil.TYPE_LONG.equals(propertyMap.getJavaType())) {
			Long value = resultSet.getLong(propertyMap.getColumnName());
			setDataValue(value, data, methodMap, propertyMap);
		} else if (TypeUtil.TYPE_TIMESTAMP.equals(propertyMap.getJavaType())) {
			Timestamp value = resultSet.getTimestamp(propertyMap.getColumnName());
			setDataValue(value, data, methodMap, propertyMap);
		}
	}

	private Object prepareDataValue(ResultSet resultSet, String javaType) throws SQLException, SqlTemplateException {
		if (TypeUtil.TYPE_STRING.equals(javaType)) {
			return resultSet.getString(1);
		} else if (TypeUtil.TYPE_DATE.equals(javaType)) {
			return resultSet.getDate(1);
		} else if (TypeUtil.TYPE_DOUBLE.equals(javaType)) {
			return resultSet.getDouble(1);
		} else if (TypeUtil.TYPE_SHORT.equals(javaType)) {
			return resultSet.getShort(1);
		} else if (TypeUtil.TYPE_FLOAT.equals(javaType)) {
			return resultSet.getFloat(1);
		} else if (TypeUtil.TYPE_INTEGER.equals(javaType)) {
			return resultSet.getInt(1);
		} else if (TypeUtil.TYPE_LONG.equals(javaType)) {
			return resultSet.getLong(1);
		} else if (TypeUtil.TYPE_TIMESTAMP.equals(javaType)) {
			return resultSet.getTimestamp(1);
		}
		throw new SqlTemplateException(String.format("Can not map the correct java type:%s", javaType));
	}

	private void setDataValue(Object value, Object data, Map<String, Method> methodMap, PropertyMap propertyMap)
			throws IllegalAccessException, InvocationTargetException {
		String writerMethodName = ClassUtil.getWriteMethodName(propertyMap.getPropertyName());
		if (methodMap.containsKey(writerMethodName)) {
			Method method = methodMap.get(writerMethodName);
			method.invoke(data, value);
		}
	}
}
