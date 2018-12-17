package th.co.baiwa.excise.ta.persistence.vo;

import java.util.List;

import th.co.baiwa.excise.ta.persistence.entity.PlanCriteria;
import th.co.baiwa.excise.ta.persistence.entity.PlanTaxAudit;

public class PlanTaxAuditVo {
	
	private PlanTaxAudit planTaxAudit;
	private List<PlanCriteria> planCriteriaList;
	private String budgetYear;
	private String analysNumber;
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAnalysNumber() {
		return analysNumber;
	}
	public void setAnalysNumber(String analysNumber) {
		this.analysNumber = analysNumber;
	}
	public String getBudgetYear() {
		return budgetYear;
	}
	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}
	public PlanTaxAudit getPlanTaxAudit() {
		return planTaxAudit;
	}
	public void setPlanTaxAudit(PlanTaxAudit planTaxAudit) {
		this.planTaxAudit = planTaxAudit;
	}
	public List<PlanCriteria> getPlanCriteriaList() {
		return planCriteriaList;
	}
	public void setPlanCriteriaList(List<PlanCriteria> planCriteriaList) {
		this.planCriteriaList = planCriteriaList;
	}
	
	
	
	

}
