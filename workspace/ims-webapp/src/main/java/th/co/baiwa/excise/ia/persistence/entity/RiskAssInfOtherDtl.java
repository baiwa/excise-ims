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
@Table(name = "IA_RISK_ASS_INF_OTHER_DTL")
public class RiskAssInfOtherDtl extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4983004780526239379L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_ASS_INF_OTHER_DTL_GEN")
	@SequenceGenerator(name = "IA_RISK_ASS_INF_OTHER_DTL_GEN", sequenceName = "IA_RISK_ASS_INF_OTHER_DTL_SEQ", allocationSize = 1)
	@Column(name = "RISK_ASS_INF_OTHER_ID")
	private Long riskAssInfOtherId;

	@Column(name = "RISK_INF_HRD_ID")
	private Long riskInfHrdId;

	@Column(name = "INF_NAME")
	private String infName;

	@Column(name = "RISK_COST")
	private BigDecimal riskCost;

	@Column(name = "RL")
	private String rl;

	@Column(name = "VALUE_TRANSLATION")
	private String valueTranslation;

	@Column(name = "COLOR")
	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getRiskAssInfOtherId() {
		return riskAssInfOtherId;
	}

	public void setRiskAssInfOtherId(Long riskAssInfOtherId) {
		this.riskAssInfOtherId = riskAssInfOtherId;
	}

	public Long getRiskInfHrdId() {
		return riskInfHrdId;
	}

	public void setRiskInfHrdId(Long riskInfHrdId) {
		this.riskInfHrdId = riskInfHrdId;
	}

	public String getInfName() {
		return infName;
	}

	public void setInfName(String infName) {
		this.infName = infName;
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
	

}
