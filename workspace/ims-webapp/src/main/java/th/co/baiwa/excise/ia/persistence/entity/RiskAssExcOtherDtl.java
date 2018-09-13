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


/**
 * The persistent class for the IA_RISK_ASS_EXC_OTHER_DTL database table.
 * 
 */
@Entity
@Table(name="IA_RISK_ASS_EXC_OTHER_DTL")
public class RiskAssExcOtherDtl extends BaseEntity{


	private static final long serialVersionUID = 8610100576522683110L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_ASS_EXC_OTHER_DTL_GEN")
	@SequenceGenerator(name = "IA_RISK_ASS_EXC_OTHER_DTL_GEN", sequenceName = "IA_RISK_ASS_EXC_OTHER_DTL_SEQ", allocationSize = 1)
	@Column(name="RISK_OTHER_DTL_ID")
	private Long riskOtherDtlId;

	@Column(name="COLOR")
	private String color;

	@Column(name="DEPARTMENT_NAME")
	private String departmentName;

	@Column(name="RISK_HRD_ID")
	private Long riskHrdId;

	@Column(name="RL")
	private String rl;

	@Column(name="VALUE_TRANSLATION")
	private String valueTranslation;
	
	@Column(name="RISK_COST")
	private BigDecimal riskCost;

	@Column(name="OTHER")
	private BigDecimal other;
	


	public Long getRiskOtherDtlId() {
		return this.riskOtherDtlId;
	}

	public void setRiskOtherDtlId(Long riskOtherDtlId) {
		this.riskOtherDtlId = riskOtherDtlId;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String getRl() {
		return this.rl;
	}

	public void setRl(String rl) {
		this.rl = rl;
	}


	public String getValueTranslation() {
		return this.valueTranslation;
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

	public BigDecimal getRiskCost() {
		return riskCost;
	}

	public void setRiskCost(BigDecimal riskCost) {
		this.riskCost = riskCost;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public BigDecimal getOther() {
		return other;
	}

	public void setOther(BigDecimal other) {
		this.other = other;
	}

	
}