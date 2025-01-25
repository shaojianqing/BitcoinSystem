package sjq.bitcoin.orm.utility;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ClassUtil {
	
	private final static String SET_PREFIX = "set";
	
	private final static String GET_PREFIX = "get";
	
	private final static String IS_PREFIX = "is";
	
	public static Map<String, Method> prepareMethodMap(Class<?> clazz) {
		if (clazz!=null) {
			Map<String, Method> methodMap = new HashMap<String, Method>();
			Method methods[] = clazz.getMethods();
			for (Method method:methods) {
				methodMap.put(method.getName(), method);
			}
			return methodMap;
		}
		return null;
	}
	
	public static String getBooleanReadMethodName(String propertyName) {
		if (StringUtils.isNotBlank(propertyName)) {
			char string[] = propertyName.toCharArray();
			string[0] = Character.toUpperCase(string[0]);
			propertyName = new String(string);
			return IS_PREFIX + propertyName;
		}
		return null;
	}
	
	public static String getReadMethodName(String propertyName) {
		if (StringUtils.isNotBlank(propertyName)) {
			char string[] = propertyName.toCharArray();
			string[0] = Character.toUpperCase(string[0]);
			propertyName = new String(string);
			return GET_PREFIX + propertyName;
		}
		return null;
	}
	
	public static String getWriteMethodName(String propertyName) {
		if (StringUtils.isNotBlank(propertyName)) {
			char string[] = propertyName.toCharArray();
			string[0] = Character.toUpperCase(string[0]);
			propertyName = new String(string);
			return SET_PREFIX + propertyName;
		}
		return null;
	}
}
