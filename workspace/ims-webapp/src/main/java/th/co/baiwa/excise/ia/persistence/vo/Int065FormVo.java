package th.co.baiwa.excise.ia.persistence.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Int065FormVo extends DataTableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5691257239831690464L;
	private String sector;
	private String area;
	private String branch;
	private String OfficeCode;
	private String dateFrom;
	private String dateTo;
	private String searchFlag;
	private String budgetType;

	public String getSector() {
		return sector;
	}

	public String getArea() {
		return area;
	}

	public String getBranch() {
		return branch;
	}

	public String getOfficeCode() {
		return OfficeCode;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public String getBudgetType() {
		return budgetType;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public void setOfficeCode(String officeCode) {
		OfficeCode = officeCode;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}

}
