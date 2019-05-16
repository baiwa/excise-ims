
package th.go.excise.ims.ws.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "WS_LICFRI6010")
public class WsLicfri6010 extends BaseEntity {

	 
	private static final long serialVersionUID = -4710798706189415508L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_LICFRI6010_GEN")
	@SequenceGenerator(name = "WS_LICFRI6010_GEN", sequenceName = "WS_LICFRI6010_SEQ", allocationSize = 1)
	@Column(name = "WS_LICFRI6010_ID")
	private Long wsLicfri6010Id;
	@Column(name = "OFFCODE")
	private String offcode;
	@Column(name = "LIC_TYPE")
	private String licType;
	@Column(name = "LIC_NO")
	private String licNo;
	@Column(name = "LIC_NAME")
	private String licName;
	@Column(name = "LIC_FEE")
	private BigDecimal licFee;
	@Column(name = "LIC_INTERIOR")
	private BigDecimal licInterior;
	@Column(name = "LIC_PRICE")
	private BigDecimal licPrice;
	@Column(name = "LIC_DATE")
	private String licDate;
	@Column(name = "START_DATE")
	private String startDate;
	@Column(name = "EXP_DATE")
	private String expDate;
	@Column(name = "SEND_DATE")
	private String sendDate;
	@Column(name = "PRINT_COUNT")
	private BigDecimal printCount;
	@Column(name = "NID")
	private String nid;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "CUS_FULLNAME")
	private String cusFullname;
	@Column(name = "FAC_FULLNAME")
	private String facFullname;
	@Column(name = "INC_CODE")
	private String incCode;

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public Long getWsLicfri6010Id() {
		return wsLicfri6010Id;
	}

	public void setWsLicfri6010Id(Long wsLicfri6010Id) {
		this.wsLicfri6010Id = wsLicfri6010Id;
	}

	public String getOffcode() {
		return offcode;
	}

	public void setOffcode(String offcode) {
		this.offcode = offcode;
	}

	public String getLicType() {
		return licType;
	}

	public void setLicType(String licType) {
		this.licType = licType;
	}

	public String getLicNo() {
		return licNo;
	}

	public void setLicNo(String licNo) {
		this.licNo = licNo;
	}

	public String getLicName() {
		return licName;
	}

	public void setLicName(String licName) {
		this.licName = licName;
	}

	public BigDecimal getLicFee() {
		return licFee;
	}

	public void setLicFee(BigDecimal licFee) {
		this.licFee = licFee;
	}

	public BigDecimal getLicInterior() {
		return licInterior;
	}

	public void setLicInterior(BigDecimal licInterior) {
		this.licInterior = licInterior;
	}

	public BigDecimal getLicPrice() {
		return licPrice;
	}

	public void setLicPrice(BigDecimal licPrice) {
		this.licPrice = licPrice;
	}

	public String getLicDate() {
		return licDate;
	}

	public void setLicDate(String licDate) {
		this.licDate = licDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public BigDecimal getPrintCount() {
		return printCount;
	}

	public void setPrintCount(BigDecimal printCount) {
		this.printCount = printCount;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getCusFullname() {
		return cusFullname;
	}

	public void setCusFullname(String cusFullname) {
		this.cusFullname = cusFullname;
	}

	public String getFacFullname() {
		return facFullname;
	}

	public void setFacFullname(String facFullname) {
		this.facFullname = facFullname;
	}

	public String getIncCode() {
		return incCode;
	}

	public void setIncCode(String incCode) {
		this.incCode = incCode;
	}


}
