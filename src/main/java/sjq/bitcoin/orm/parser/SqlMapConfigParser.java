package sjq.bitcoin.orm.parser;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import org.apache.commons.lang3.StringUtils;
import sjq.bitcoin.orm.datamap.PropertyMap;
import sjq.bitcoin.orm.datamap.ResultDataMap;
import sjq.bitcoin.orm.exception.SqlTemplateException;
import sjq.bitcoin.orm.statement.SqlStatement;


public class SqlMapConfigParser {
	
	private Map<String, ResultDataMap> resultDataMapTable = new HashMap<>();
	
	public void buildSqlMapConfig(Map<String, SqlStatement> statementMap, Element root, DataSource dataSource) throws Exception {
		
		if (statementMap!=null && root!=null) {
			
			this.buildResultDataMap(statementMap, root);
			this.buildSelectSqlList(statementMap, root, dataSource);
			this.buildInsertSqlList(statementMap, root, dataSource);
			this.buildUpdateSqlList(statementMap, root, dataSource);
			this.buildDeleteSqlList(statementMap, root, dataSource);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void buildResultDataMap(Map<String, SqlStatement> statementMap, Element root) throws Exception {
		List<Element> resultDataMapList = (List<Element>)root.elements("resultDataMap");
		if (resultDataMapList!=null && resultDataMapList.size()>0) {
			for (Element resultMapNode:resultDataMapList) {
				
				String id = resultMapNode.attributeValue("id");
				String className = resultMapNode.attributeValue("class");
				
				if (StringUtils.isBlank(id)) {
					throw new SqlTemplateException("ResultDataMap id Can not be Empty! ResultDataMap Id:%s");
				}
				
				if (StringUtils.isBlank(className)) {
					String message = String.format("ResultDataMap class Can not be Empty! ResultDataMap Id:%s", id);
					throw new SqlTemplateException(message);
				}
				
				ResultDataMap resultDataMap = new ResultDataMap();
				resultDataMap.setId(resultMapNode.attributeValue("id"));
				resultDataMap.setClassName(resultMapNode.attributeValue("class"));
				
				List<Element> results = resultMapNode.elements("result");
				
				if (results!=null && results.size()>0) {					
					List<PropertyMap> propertyMapList = new ArrayList<PropertyMap>();
					for (Element resultItemNode:results) {
						
						String javaType = resultItemNode.attributeValue("javaType");
						String property = resultItemNode.attributeValue("property");
						String column = resultItemNode.attributeValue("column");
						
						if (StringUtils.isBlank(javaType)) {
							String message = String.format("ResultDataMap result item javaType Configuration Can not be Empty! ResultDataMap Id:%s", resultDataMap.getId());
							throw new SqlTemplateException(message);
						}
						
						if (StringUtils.isBlank(property)) {
							String message = String.format("ResultDataMap result item property Configuration Can not be Empty! ResultDataMap Id:%s", resultDataMap.getId());
							throw new SqlTemplateException(message);
						}
						
						if (StringUtils.isBlank(column)) {
							String message = String.format("ResultDataMap result item column Configuration Can not be Empty! ResultDataMap Id:%s", resultDataMap.getId());
							throw new SqlTemplateException(message);
						}
						
						PropertyMap propertyMap = new PropertyMap();
						propertyMap.setColumnName(column);
						propertyMap.setJavaType(javaType);
						propertyMap.setPropertyName(property);
						
						propertyMapList.add(propertyMap);
					}
				
					resultDataMap.setPropertyList(propertyMapList);
					resultDataMapTable.put(resultDataMap.getId(), resultDataMap);
				} else {
					String message = String.format("ResultDataMap Result List Can not be Empty! ResultDataMap Id:%s", resultDataMap.getId());
					throw new SqlTemplateException(message);
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void buildSelectSqlList(Map<String, SqlStatement> statementMap, Element root, DataSource dataSource) throws Exception {
		List<Element> selectSqlList = root.elements("select");
		
		if (selectSqlList!=null && selectSqlList.size()>0) {
			for (Element sqlNode:selectSqlList) {
				String sql = sqlNode.getText();
				String id = sqlNode.attributeValue("id");
				String resultType = sqlNode.attributeValue("resultType");
				String resultDataMapKey = sqlNode.attributeValue("resultDataMap");
				String parameterType = sqlNode.attributeValue("parameterType");
				
				if (StringUtils.isBlank(id)) {
					throw new SqlTemplateException("Select Statement id can not be empty!");
				}
				
				if (StringUtils.isBlank(sql)) {
					String message = String.format("Select Statement Sql can not be empty! statement id:%s", id);
					throw new SqlTemplateException(message);
				}
				
				if (StringUtils.isBlank(parameterType)) {
					String message = String.format("Select Statement parameterType can not be empty! statement id:%s", id);
					throw new SqlTemplateException(message);
				}
				
				SqlStatement sqlStatement = new SqlStatement(sql, dataSource);
				if (StringUtils.isNotBlank(resultDataMapKey)) {
					if (resultDataMapTable.containsKey(resultDataMapKey)) {
						ResultDataMap resultDataMap = resultDataMapTable.get(resultDataMapKey);
						sqlStatement.setResultDataMap(resultDataMap);
					}
				} else if (StringUtils.isNotBlank(resultType)) {
					sqlStatement.setResultType(resultType);
				} else {
					String message = String.format("Select Statement Must Have at least one of the resultType and parameterType! statement id:%s", id);
					throw new SqlTemplateException(message);
				}
				
				sqlStatement.setParameterType(parameterType);
				statementMap.put(id, sqlStatement);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void buildInsertSqlList(Map<String, SqlStatement> statementMap, Element root, DataSource dataSource) throws Exception {
		List<Element> updateDataMapList = root.elements("insert");
		
		if (updateDataMapList!=null && updateDataMapList.size()>0) {
			for (Element sqlNode:updateDataMapList) {
				String sql = sqlNode.getText();
				String id = sqlNode.attributeValue("id");
				String parameterType = sqlNode.attributeValue("parameterType");
				
				if (StringUtils.isBlank(id)) {
					throw new SqlTemplateException("Insert Statement id can not be empty!");
				}
				
				if (StringUtils.isBlank(sql)) {
					String message = String.format("Insert Statement Sql can not be empty! statement id:%s", id);
					throw new SqlTemplateException(message);
				}
				
				if (StringUtils.isBlank(parameterType)) {
					String message = String.format("Insert Statement parameterType can not be empty! statement id:%s", id);
					throw new SqlTemplateException(message);
				}
				
				SqlStatement sqlStatement = new SqlStatement(sql, dataSource);				
				sqlStatement.setParameterType(parameterType);
				statementMap.put(id, sqlStatement);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void buildUpdateSqlList(Map<String, SqlStatement> statementMap, Element root, DataSource dataSource) throws Exception {
		List<Element> updateDataMapList = root.elements("update");
		
		if (updateDataMapList!=null && updateDataMapList.size()>0) {
			for (Element sqlNode:updateDataMapList) {
				String sql = sqlNode.getText();
				String id = sqlNode.attributeValue("id");
				String parameterType = sqlNode.attributeValue("parameterType");
				
				if (StringUtils.isBlank(id)) {
					throw new SqlTemplateException("Update Statement id can not be empty!");
				}
				
				if (StringUtils.isBlank(sql)) {
					String message = String.format("Update Statement Sql can not be empty! statement id:%s", id);
					throw new SqlTemplateException(message);
				}
				
				if (StringUtils.isBlank(parameterType)) {
					String message = String.format("Update Statement parameterType can not be empty! statement id:%s", id);
					throw new SqlTemplateException(message);
				}
				
				SqlStatement sqlStatement = new SqlStatement(sql, dataSource);				
				sqlStatement.setParameterType(parameterType);
				statementMap.put(id, sqlStatement);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void buildDeleteSqlList(Map<String, SqlStatement> statementMap, Element root, DataSource dataSource) throws Exception {
		List<Element> deleteDataMapList = root.elements("delete");
		
		if (deleteDataMapList!=null && deleteDataMapList.size()>0) {
			for (Element sqlNode:deleteDataMapList) {
				String sql = sqlNode.getText();
				String id = sqlNode.attributeValue("id");
				String parameterType = sqlNode.attributeValue("parameterType");
				
				if (StringUtils.isBlank(id)) {
					throw new SqlTemplateException("Select Statement id can not be empty!");
				}
				
				if (StringUtils.isBlank(sql)) {
					String message = String.format("Select Statement Sql can not be empty! statement id:%s", id);
					throw new SqlTemplateException(message);
				}
				
				if (StringUtils.isBlank(parameterType)) {
					String message = String.format("Select Statement parameterType can not be empty! statement id:%s", id);
					throw new SqlTemplateException(message);
				}
				
				SqlStatement sqlStatement = new SqlStatement(sql, dataSource);				
				sqlStatement.setParameterType(parameterType);
				statementMap.put(id, sqlStatement);
			}
		}
	}
}
