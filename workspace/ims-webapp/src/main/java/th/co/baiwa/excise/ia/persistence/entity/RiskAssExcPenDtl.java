package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;



@Entity
@Table(name="IA_RISK_ASS_EXC_PEN_DTL")
public class RiskAssExcPenDtl extends BaseEntity{

	private static final long serialVersionUID = -7112366338796807793L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_ASS_EXC_PEN_DTL_GEN")
	@SequenceGenerator(name = "IA_RISK_ASS_EXC_PEN_DTL_GEN", sequenceName = "IA_RISK_ASS_EXC_PEN_DTL_SEQ", allocationSize = 1)
	@Column(name="RISK_DTL_ID")
	private Long riskDtlId;

	@Column(name="RISK_HRD_ID")
	private Long riskHrdId;
	
	@Column(name="BUDGET_DIFF")
	private BigDecimal budgetDiff;

	@Column(name="BUDGET_INCOME")
	private BigDecimal budgetIncome;

	@Column(name="COLOR")
	private String color;

	@Column(name="DEPARTMENT_NAME")
	private String departmentName;

	@Column(name="PERCEN_DIFF")
	private BigDecimal percenDiff;

	@Column(name="RESULTS_INCOME")
	private BigDecimal resultsIncome;

	@Column(name="RL")
	private String rl;

	@Column(name="VALUE_TRANSLATION")
	private String valueTranslation;

	public BigDecimal getBudgetDiff() {
		return budgetDiff;
	}

	public void setBudgetDiff(BigDecimal budgetDiff) {
		this.budgetDiff = budgetDiff;
	}


	public BigDecimal getBudgetIncome() {
		return budgetIncome;
	}


	public void setBudgetIncome(BigDecimal budgetIncome) {
		this.budgetIncome = budgetIncome;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getDepartmentName() {
		return departmentName;
	}


	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}


	public BigDecimal getPercenDiff() {
		return percenDiff;
	}


	public void setPercenDiff(BigDecimal percenDiff) {
		this.percenDiff = percenDiff;
	}


	public BigDecimal getResultsIncome() {
		return resultsIncome;
	}


	public void setResultsIncome(BigDecimal resultsIncome) {
		this.resultsIncome = resultsIncome;
	}


	public Long getRiskDtlId() {
		return riskDtlId;
	}


	public void setRiskDtlId(Long riskDtlId) {
		this.riskDtlId = riskDtlId;
	}


	public Long getRiskHrdId() {
		return riskHrdId;
	}


	public void setRiskHrdId(Long riskHrdId) {
		this.riskHrdId = riskHrdId;
	}


	public String getRl() {
		return rl;
	}


	public void setRl(String rl) {
		this.rl = rl;
	}


	public String getValueTranslation() {
		return valueTranslation;
	}


	public void setValueTranslation(String valueTranslation) {
		this.valueTranslation = valueTranslation;
	}



}