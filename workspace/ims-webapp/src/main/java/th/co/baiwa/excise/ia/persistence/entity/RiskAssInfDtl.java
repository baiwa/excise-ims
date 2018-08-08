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
public class RiskAssInfDtl extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2639482066713826661L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_ASS_INF_DTL_GEN")
	@SequenceGenerator(name = "IA_RISK_ASS_INF_DTL_GEN", sequenceName = "IA_RISK_ASS_INF_DTL_SEQ", allocationSize = 1)
	@Column(name = "RISK_ASS_INF_DTL_ID")
	private Long riskAssInfDtlId;
	
	@Column(name = "RISK_ASS_INF_DTL_NAME")
	private String riskAssInfDtlName;
	
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
	
	@Column(name = "SEPTEMBER")
	private BigDecimal september;
	
	@Column(name = "OCT")
	private BigDecimal oct;
	
	@Column(name = "NOV")
	private BigDecimal nov;
	
	@Column(name = "DEC")
	private BigDecimal dec;

	
	public Long getRiskAssInfDtlId() {
		return riskAssInfDtlId;
	}
	public void setRiskAssInfDtlId(Long riskAssInfDtlId) {
		this.riskAssInfDtlId = riskAssInfDtlId;
	}
	public String getRiskAssInfDtlName() {
		return riskAssInfDtlName;
	}
	public void setRiskAssInfDtlName(String riskAssInfDtlName) {
		this.riskAssInfDtlName = riskAssInfDtlName;
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
	public BigDecimal getSeptember() {
		return september;
	}
	public void setSeptember(BigDecimal september) {
		this.september = september;
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

}