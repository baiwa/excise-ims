package th.co.baiwa.excise.combobox.entity;

public class Combobox {
	
	private String value;
	private String description;

	public Combobox(String value, String description ) {
		this.value = value;
		this.description = description;
	}
	public Combobox() {
		
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	} 
}
