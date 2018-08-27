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
@Table(name="IA_RISK_ASS_OTHER_DTL")
public class RiskAssOtherDtl extends BaseEntity{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6645937545715561474L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_ASS_OTHER_DTL_GEN")
	@SequenceGenerator(name = "IA_RISK_ASS_OTHER_DTL_GEN", sequenceName = "IA_RISK_ASS_OTHER_DTL_SEQ", allocationSize = 1)
	@Column(name="RISK_OTHER_DTL_ID")
	private Long riskOtherDtlId;

	@Column(name="RISK_HRD_ID")
	private Long riskHrdId;
	
	@Column(name="DEPARTMENT_NAME")
	private String departmentName;

	@Column(name="PROJECT_BASE")
	private String projectBase;

	@Column(name="RISK_COST")
	private BigDecimal riskCost;

	@Column(name="RL")
	private String rl;

	@Column(name="VALUE_TRANSLATION")
	private String valueTranslation;

	 
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getProjectBase() {
		return projectBase;
	}

	public void setProjectBase(String projectBase) {
		this.projectBase = projectBase;
	}

	public BigDecimal getRiskCost() {
		return riskCost;
	}

	public void setRiskCost(BigDecimal riskCost) {
		this.riskCost = riskCost;
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

	public Long getRiskHrdId() {
		return riskHrdId;
	}

	public void setRiskHrdId(Long riskHrdId) {
		this.riskHrdId = riskHrdId;
	}

	public Long getRiskOtherDtlId() {
		return riskOtherDtlId;
	}

	public void setRiskOtherDtlId(Long riskOtherDtlId) {
		this.riskOtherDtlId = riskOtherDtlId;
	}

	

	
	
}