package th.co.baiwa.excise.ta.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


@Entity
@Table(name="TA_PLAN_TAX_AUDIT")
public class PlanTaxAudit extends BaseEntity{

	
	private static final long serialVersionUID = -2879628235933219792L;

	@Id
	@SequenceGenerator(name="TA_PLAN_TAX_AUDIT_GEN", sequenceName="TA_PLAN_TAX_AUDIT_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_PLAN_TAX_AUDIT_GEN")
	@Column(name="TA_PLAN_TAX_AUDIT_ID")
	private Long taPlanTaxAuditId;

	@Column(name="ANALYS_NUMBER")
	private String analysNumber;

	@Column(name="BUDGET_YEAR")
	private String budgetYear;

	@Column(name="MONTH_FROM")
	private String monthFrom;

	@Column(name="MONTH_TO")
	private String monthTo;

	@Column(name="STATUS_PLAN")
	private String statusPlan;

	public Long getTaPlanTaxAuditId() {
		return taPlanTaxAuditId;
	}

	public void setTaPlanTaxAuditId(Long taPlanTaxAuditId) {
		this.taPlanTaxAuditId = taPlanTaxAuditId;
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

	public String getMonthFrom() {
		return monthFrom;
	}

	public void setMonthFrom(String monthFrom) {
		this.monthFrom = monthFrom;
	}

	public String getMonthTo() {
		return monthTo;
	}

	public void setMonthTo(String monthTo) {
		this.monthTo = monthTo;
	}

	public String getStatusPlan() {
		return statusPlan;
	}

	public void setStatusPlan(String statusPlan) {
		this.statusPlan = statusPlan;
	}

	
}