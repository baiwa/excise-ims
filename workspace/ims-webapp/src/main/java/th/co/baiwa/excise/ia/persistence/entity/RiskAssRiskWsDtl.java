package th.co.baiwa.excise.ia.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the IA_RISK_ASS_RISK_WS_DTL database table.
 * 
 */
@Entity
@Table(name="IA_RISK_ASS_RISK_WS_DTL")
public class RiskAssRiskWsDtl implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long riskAssRiskDtlId;
	private String projectBase;
	private String department;
	private BigDecimal budget;
	private BigDecimal localBudget;
	private BigDecimal otherMoney;
	private BigDecimal approveBudget;
	private BigDecimal sumMonth;
	private String rl;
	private String valueTranslation;
	private BigDecimal version;
	private String isDeleted;
	private String createdBy;
	private Timestamp createdDate;
	private String updatedBy;
	private Timestamp updatedDate;

	public RiskAssRiskWsDtl() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_ASS_RISK_WS_DTL_GEN")
	@SequenceGenerator(name = "IA_RISK_ASS_RISK_WS_DTL_GEN", sequenceName = "IA_RISK_ASS_RISK_WS_DTL_SEQ", allocationSize = 1)
	@Column(name="RISK_ASS_RISK_DTL_ID")
	public Long getRiskAssRiskDtlId() {
		return this.riskAssRiskDtlId;
	}

	public void setRiskAssRiskDtlId(Long riskAssRiskDtlId) {
		this.riskAssRiskDtlId = riskAssRiskDtlId;
	}


	@Column(name="APPROVE_BUDGET")
	public BigDecimal getApproveBudget() {
		return this.approveBudget;
	}

	public void setApproveBudget(BigDecimal approveBudget) {
		this.approveBudget = approveBudget;
	}


	public BigDecimal getBudget() {
		return this.budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}


	@Column(name="CREATED_BY")
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	@Column(name="CREATED_DATE")
	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}


	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}


	@Column(name="IS_DELETED")
	public String getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}


	@Column(name="LOCAL_BUDGET")
	public BigDecimal getLocalBudget() {
		return this.localBudget;
	}

	public void setLocalBudget(BigDecimal localBudget) {
		this.localBudget = localBudget;
	}


	@Column(name="OTHER_MONEY")
	public BigDecimal getOtherMoney() {
		return this.otherMoney;
	}

	public void setOtherMoney(BigDecimal otherMoney) {
		this.otherMoney = otherMoney;
	}


	@Column(name="PROJECT_BASE")
	public String getProjectBase() {
		return this.projectBase;
	}

	public void setProjectBase(String projectBase) {
		this.projectBase = projectBase;
	}


	public String getRl() {
		return this.rl;
	}

	public void setRl(String rl) {
		this.rl = rl;
	}


	@Column(name="SUM_MONTH")
	public BigDecimal getSumMonth() {
		return this.sumMonth;
	}

	public void setSumMonth(BigDecimal sumMonth) {
		this.sumMonth = sumMonth;
	}


	@Column(name="UPDATED_BY")
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}


	@Column(name="UPDATED_DATE")
	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}


	@Column(name="VALUE_TRANSLATION")
	public String getValueTranslation() {
		return this.valueTranslation;
	}

	public void setValueTranslation(String valueTranslation) {
		this.valueTranslation = valueTranslation;
	}


	@Column(name="\"VERSION\"")
	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

}