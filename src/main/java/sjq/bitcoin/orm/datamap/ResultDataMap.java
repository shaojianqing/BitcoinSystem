package sjq.bitcoin.orm.datamap;

import java.util.List;

public class ResultDataMap {
	
	private String id;
	
	private String className;
	
	private List<PropertyMap> propertyList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<PropertyMap> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<PropertyMap> propertyList) {
		this.propertyList = propertyList;
	}
}
