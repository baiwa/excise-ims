package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

import javax.persistence.Column;

public class Int0301FormVo {
	
	
	private BigDecimal id;	
	private String budgetYear;	
	private String factorsLevel ;	
	private BigDecimal inspectionWork;
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getBudgetYear() {
		return budgetYear;
	}
	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}
	public String getFactorsLevel() {
		return factorsLevel;
	}
	public void setFactorsLevel(String factorsLevel) {
		this.factorsLevel = factorsLevel;
	}
	public BigDecimal getInspectionWork() {
		return inspectionWork;
	}
	public void setInspectionWork(BigDecimal inspectionWork) {
		this.inspectionWork = inspectionWork;
	}
	
	
	

}
