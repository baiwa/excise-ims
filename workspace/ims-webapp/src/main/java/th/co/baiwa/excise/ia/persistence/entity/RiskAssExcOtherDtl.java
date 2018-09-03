package th.co.baiwa.excise.ia.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	@Column(name="RISK_OTHER_DTL_ID")
	private Long riskOtherDtlId;

	@Column(name="COLOR")
	private String color;

	@Column(name="EXCISE_AREA")
	private String exciseArea;

	@Column(name="EXCISE_BRANCH")
	private String exciseBranch;

	@Column(name="EXCISE_SETOR")
	private String exciseSetor;
	
	@Column(name="LOCATION_CODE")
	private String locationCode;

	@Column(name="RISK_HRD_ID")
	private Long riskHrdId;

	@Column(name="RL")
	private String rl;

	@Column(name="VALUE_TRANSLATION")
	private String valueTranslation;
	
	@Column(name="RISK_COST")
	private Long riskCost;



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


	public String getExciseArea() {
		return this.exciseArea;
	}

	public void setExciseArea(String exciseArea) {
		this.exciseArea = exciseArea;
	}

	public String getExciseBranch() {
		return this.exciseBranch;
	}

	public void setExciseBranch(String exciseBranch) {
		this.exciseBranch = exciseBranch;
	}

	public String getExciseSetor() {
		return this.exciseSetor;
	}

	public void setExciseSetor(String exciseSetor) {
		this.exciseSetor = exciseSetor;
	}


	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
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

	public Long getRiskCost() {
		return riskCost;
	}

	public void setRiskCost(Long riskCost) {
		this.riskCost = riskCost;
	}

	
}