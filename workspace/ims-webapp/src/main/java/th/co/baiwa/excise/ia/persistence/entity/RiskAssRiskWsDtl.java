package th.co.baiwa.excise.ia.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the IA_RISK_ASS_RISK_WS_DTL database table.
 * 
 */
@Entity
@Table(name="IA_RISK_ASS_RISK_WS_DTL")
public class RiskAssRiskWsDtl extends BaseEntity{
	
	private static final long serialVersionUID = 8256372668842679184L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_ASS_RISK_WS_DTL_GEN")
	@SequenceGenerator(name = "IA_RISK_ASS_RISK_WS_DTL_GEN", sequenceName = "IA_RISK_ASS_RISK_WS_DTL_SEQ", allocationSize = 1)
	@Column(name="RISK_ASS_RISK_DTL_ID")
	private Long riskAssRiskDtlId;
	
	@Column(name="PROJECT_BASE")
	private String projectBase;
	
	@Column(name="DEPARTMENT_NAME")
	private String departmentName;
	
	@Column(name="BUDGET")
	private BigDecimal budget;
	
	@Column(name="LOCAL_BUDGET")
	private BigDecimal localBudget;
	
	@Column(name="OTHER_MONEY")
	private BigDecimal otherMoney;
	
	@Column(name="APPROVE_BUDGET")
	private BigDecimal approveBudget;
	
	@Column(name="SUM_MONTH")
	private BigDecimal sumMonth;
	
	@Column(name="RL")
	private String rl;
	
	@Column(name="VALUE_TRANSLATION")
	private String valueTranslation;
	

	public RiskAssRiskWsDtl() {
	}


	public Long getRiskAssRiskDtlId() {
		return riskAssRiskDtlId;
	}


	public void setRiskAssRiskDtlId(Long riskAssRiskDtlId) {
		this.riskAssRiskDtlId = riskAssRiskDtlId;
	}


	public String getProjectBase() {
		return projectBase;
	}


	public void setProjectBase(String projectBase) {
		this.projectBase = projectBase;
	}


	public String getDepartmentName() {
		return departmentName;
	}


	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}


	public BigDecimal getBudget() {
		return budget;
	}


	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}


	public BigDecimal getLocalBudget() {
		return localBudget;
	}


	public void setLocalBudget(BigDecimal localBudget) {
		this.localBudget = localBudget;
	}


	public BigDecimal getOtherMoney() {
		return otherMoney;
	}


	public void setOtherMoney(BigDecimal otherMoney) {
		this.otherMoney = otherMoney;
	}


	public BigDecimal getApproveBudget() {
		return approveBudget;
	}


	public void setApproveBudget(BigDecimal approveBudget) {
		this.approveBudget = approveBudget;
	}


	public BigDecimal getSumMonth() {
		return sumMonth;
	}


	public void setSumMonth(BigDecimal sumMonth) {
		this.sumMonth = sumMonth;
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