package th.co.baiwa.excise.ta.persistence.vo;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Tsl010600FormVo extends DataTableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2929537801186255747L;

	private String user;
	private String exciseId;
	private String dateCalendar;
	private String searchFlag;

	public String getUser() {
		return user;
	}

	public String getExciseId() {
		return exciseId;
	}

	public String getDateCalendar() {
		return dateCalendar;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public void setDateCalendar(String dateCalendar) {
		this.dateCalendar = dateCalendar;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

}
