package th.co.baiwa.excise.ia.persistence.vo;

import th.co.baiwa.excise.ia.persistence.entity.RentHouse;

public class Int061101FormVo extends RentHouse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4598720827083625068L;
	
	private String title;
//	private String name;
	private String lastname;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	

}
