package th.co.baiwa.excise.ta.persistence.vo;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Tsl010700FormVo extends DataTableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6196334074976287593L;

	private String exciseId;
	private String dateCalendar;
	private String searchFlag;

	public String getExciseId() {
		return exciseId;
	}

	public String getDateCalendar() {
		return dateCalendar;
	}

	public String getSearchFlag() {
		return searchFlag;
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
