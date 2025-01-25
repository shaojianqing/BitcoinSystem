package sjq.bitcoin.orm.utility;

import sjq.bitcoin.orm.exception.SqlTemplateException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlTemplateUtil {

	public static String retriveFirstKey(String sqlStringTemplate) {
		char string[] = sqlStringTemplate.toCharArray();
		int i=0, start=0, end=0;
		boolean isOccur = false;
		while(i<string.length) {
			char c = string[i];
			if (c=='#') {
				if (isOccur) {
					end = i;
					if (start+1<end) {
						return new String(Arrays.copyOfRange(string, start+1, end));
					} else {
						isOccur = false;
						start = 0;
						end = 0;
					}
				} else {
					start = i;
					isOccur = true;
				}
			}
			++i;
		}
		
		return null;
	}
	
	public static List<String> split2List(String sqlStringTemplate) throws Exception {
		List<String> list = new ArrayList<String>();
		char string[] = sqlStringTemplate.toCharArray();
		
		int i=0, start=0, end=0;
		boolean isReOccur = false;
		
		while(i<string.length) {
			char c = string[i];			
			if (c=='#') {
				if (isReOccur) {
					++i;
					isReOccur = false;
				} else {
					isReOccur = true;
				}
				
				end = i;
				String element = new String(Arrays.copyOfRange(string, start, end));
				list.add(element);
				start = end;
			}
			++i;
		}
		
		if (end<string.length) {
			String element = new String(Arrays.copyOfRange(string, end, string.length));
			list.add(element);
		}
		
		if (isReOccur) {
			throw new SqlTemplateException(String.format("sql mapping format is not correct! the sql template is %s", sqlStringTemplate));
		}
		
		return list;
	}
}