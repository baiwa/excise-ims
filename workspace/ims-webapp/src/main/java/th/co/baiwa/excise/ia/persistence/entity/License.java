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
 * The persistent class for the IA_LICENSE database table.
 * 
 */
@Entity
@Table(name="IA_LICENSE")
public class License  extends BaseEntity {
 
	private static final long serialVersionUID = -1452200423762763954L;

	@Id
	@SequenceGenerator(name="IA_LICENSE_GEN", sequenceName="IA_LICENSE_SEQ" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_LICENSE_GEN")
	@Column(name="LIC_ID")
	
	private long licId;

	@Column(name="CUS_FULL_NAME")
	private String cusFullName;

	@Column(name="EXP_DATE")
	private String expDate;

	@Column(name="FAC_FULL_NAME")
	private String facFullName;

	@Column(name="LIC_CODE")
	private String licCode;

	@Column(name="LIC_DATE")
	private String licDate;

	@Column(name="LIC_FEE")
	private String licFee;

	@Column(name="LIC_INTERIOR")
	private String licInterior;

	@Column(name="LIC_NAME")
	private String licName;

	@Column(name="LIC_NO")
	private String licNo;

	@Column(name="LIC_PRICE")
	private String licPrice;

	@Column(name="LIC_TYPE")
	private String licType;

	@Column(name="NEWREG_ID")
	private String newregId;

	private String nid;

	@Column(name="OFF_CODE")
	private String offCode;

	@Column(name="PRINT_COUNT")
	private String printCount;
	
	@Column(name="PRINT_CODE")
	private String printCode;

	@Column(name="SEND_DATE")
	private String sendDate;
	
	@Column(name="LIC_AMOUNT")
	private BigDecimal licAmount;

	@Column(name="START_DATE")
	private String startDate;

	public long getLicId() {
		return licId;
	}

	public void setLicId(long licId) {
		this.licId = licId;
	}

	public String getCusFullName() {
		return cusFullName;
	}

	public void setCusFullName(String cusFullName) {
		this.cusFullName = cusFullName;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getFacFullName() {
		return facFullName;
	}

	public void setFacFullName(String facFullName) {
		this.facFullName = facFullName;
	}

	public String getLicCode() {
		return licCode;
	}

	public void setLicCode(String licCode) {
		this.licCode = licCode;
	}

	public String getLicDate() {
		return licDate;
	}

	public void setLicDate(String licDate) {
		this.licDate = licDate;
	}

	public String getLicFee() {
		return licFee;
	}

	public void setLicFee(String licFee) {
		this.licFee = licFee;
	}

	public String getLicInterior() {
		return licInterior;
	}

	public void setLicInterior(String licInterior) {
		this.licInterior = licInterior;
	}

	public String getLicName() {
		return licName;
	}

	public void setLicName(String licName) {
		this.licName = licName;
	}

	public String getLicNo() {
		return licNo;
	}

	public void setLicNo(String licNo) {
		this.licNo = licNo;
	}

	public String getLicPrice() {
		return licPrice;
	}

	public void setLicPrice(String licPrice) {
		this.licPrice = licPrice;
	}

	public String getLicType() {
		return licType;
	}

	public void setLicType(String licType) {
		this.licType = licType;
	}

	public String getNewregId() {
		return newregId;
	}

	public void setNewregId(String newregId) {
		this.newregId = newregId;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getOffCode() {
		return offCode;
	}

	public void setOffCode(String offCode) {
		this.offCode = offCode;
	}

	public String getPrintCount() {
		return printCount;
	}

	public void setPrintCount(String printCount) {
		this.printCount = printCount;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getPrintCode() {
		return printCode;
	}

	public void setPrintCode(String printCode) {
		this.printCode = printCode;
	}

	public BigDecimal getLicAmount() {
		return licAmount;
	}

	public void setLicAmount(BigDecimal licAmount) {
		this.licAmount = licAmount;
	}




}