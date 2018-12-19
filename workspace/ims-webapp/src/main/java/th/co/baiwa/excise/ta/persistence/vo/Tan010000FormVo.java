package th.co.baiwa.excise.ta.persistence.vo;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Tan010000FormVo extends DataTableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2929537801186255747L;

	private String user;
	private String exciseId;
	private String dateCalendar;
	private String searchFlag;
	
	private String fiscalYear;
	private String status;
	
	private Long id;
	private String patternd;
	private String startDate;
	private String endDate;


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

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatternd() {
		return patternd;
	}

	public void setPatternd(String patternd) {
		this.patternd = patternd;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	

}
