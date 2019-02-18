package th.go.excise.ims.ia.vo;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class Int02FormVo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8289315891184941813L;
	private String budgetYear;
	private String startDate;
	private String endDate;
	
	public String getBudgetYear() {
		return budgetYear;
	}
	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
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
