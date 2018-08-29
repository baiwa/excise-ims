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
@Table(name = "IA_RISK_ASS_INF_DTL")
public class RiskAssInfDtl extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2639482066713826661L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_ASS_INF_DTL_GEN")
	@SequenceGenerator(name = "IA_RISK_ASS_INF_DTL_GEN", sequenceName = "IA_RISK_ASS_INF_DTL_SEQ", allocationSize = 1)
	@Column(name = "RISK_ASS_INF_DTL_ID")
	private Long riskAssInfDtlId;

	@Column(name = "RISK_INF_HRD_ID")
	private Long riskInfHrdId;

	@Column(name = "INF_NAME")
	private String infName;

	@Column(name = "JAN")
	private BigDecimal jan;

	@Column(name = "FEB")
	private BigDecimal feb;

	@Column(name = "MAR")
	private BigDecimal mar;

	@Column(name = "APRIL")
	private BigDecimal april;

	@Column(name = "MAY")
	private BigDecimal may;

	@Column(name = "JUN")
	private BigDecimal jun;

	@Column(name = "JUL")
	private BigDecimal jul;

	@Column(name = "AUG")
	private BigDecimal aug;

	@Column(name = "SEP")
	private BigDecimal sep;

	@Column(name = "OCT")
	private BigDecimal oct;

	@Column(name = "NOV")
	private BigDecimal nov;

	@Column(name = "DEC")
	private BigDecimal dec;

	@Column(name = "TOTAL")
	private BigDecimal total;

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

	public Long getRiskAssInfDtlId() {
		return riskAssInfDtlId;
	}

	public void setRiskAssInfDtlId(Long riskAssInfDtlId) {
		this.riskAssInfDtlId = riskAssInfDtlId;
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

	public BigDecimal getJan() {
		return jan;
	}

	public void setJan(BigDecimal jan) {
		this.jan = jan;
	}

	public BigDecimal getFeb() {
		return feb;
	}

	public void setFeb(BigDecimal feb) {
		this.feb = feb;
	}

	public BigDecimal getMar() {
		return mar;
	}

	public void setMar(BigDecimal mar) {
		this.mar = mar;
	}

	public BigDecimal getApril() {
		return april;
	}

	public void setApril(BigDecimal april) {
		this.april = april;
	}

	public BigDecimal getMay() {
		return may;
	}

	public void setMay(BigDecimal may) {
		this.may = may;
	}

	public BigDecimal getJun() {
		return jun;
	}

	public void setJun(BigDecimal jun) {
		this.jun = jun;
	}

	public BigDecimal getJul() {
		return jul;
	}

	public void setJul(BigDecimal jul) {
		this.jul = jul;
	}

	public BigDecimal getAug() {
		return aug;
	}

	public void setAug(BigDecimal aug) {
		this.aug = aug;
	}

	public BigDecimal getSep() {
		return sep;
	}

	public void setSep(BigDecimal sep) {
		this.sep = sep;
	}

	public BigDecimal getOct() {
		return oct;
	}

	public void setOct(BigDecimal oct) {
		this.oct = oct;
	}

	public BigDecimal getNov() {
		return nov;
	}

	public void setNov(BigDecimal nov) {
		this.nov = nov;
	}

	public BigDecimal getDec() {
		return dec;
	}

	public void setDec(BigDecimal dec) {
		this.dec = dec;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
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