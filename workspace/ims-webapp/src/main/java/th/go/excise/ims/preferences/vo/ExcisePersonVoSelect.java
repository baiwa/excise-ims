package th.go.excise.ims.preferences.vo;

import th.go.excise.ims.preferences.persistence.entity.ExcisePerson;

public class ExcisePersonVoSelect extends ExcisePerson {
	
	private String name;
	private String value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
