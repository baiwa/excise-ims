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
 * The persistent class for the IA_RISK_ASS_RISK_WS_DTL database table.
 * 
 */
@Entity
@Table(name="IA_RISK_ASS_EXC_AREA_DTL")
public class RiskAssExcAreaDtl extends BaseEntity{
	
	private static final long serialVersionUID = 8256372668842679184L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_ASS_EXC_AREA_DTL_GEN")
	@SequenceGenerator(name = "IA_RISK_ASS_EXC_AREA_DTL_GEN", sequenceName = "IA_RISK_ASS_EXC_AREA_DTL_SEQ", allocationSize = 1)
	@Column(name="RISK_ASS_EXC_AREA_DTL_ID")
	private Long riskAssExcAreaDtlId;
	
	
	@Column(name = "RISK_HRD_ID")
	private Long riskHrdId; 
	

	@Column(name="DEPARTMENT_NAME")
	private String departmentName;
	
	@Column(name="CHECK_OUT_DATE")
	private String checkOutDate;
	
	
	@Column(name="CLOSE_DATE")
	private String closeDate;
	
	
	@Column(name="YEARS")
	private BigDecimal years;
	
	@Column(name="COLOR")
	private String color;
	
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


	public String getCheckOutDate() {
		return checkOutDate;
	}


	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}


	public String getCloseDate() {
		return closeDate;
	}


	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}


	public BigDecimal getYears() {
		return years;
	}


	public void setYears(BigDecimal years) {
		this.years = years;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public Long getRiskAssExcAreaDtlId() {
		return riskAssExcAreaDtlId;
	}


	public void setRiskAssExcAreaDtlId(Long riskAssExcAreaDtlId) {
		this.riskAssExcAreaDtlId = riskAssExcAreaDtlId;
	}

	
}