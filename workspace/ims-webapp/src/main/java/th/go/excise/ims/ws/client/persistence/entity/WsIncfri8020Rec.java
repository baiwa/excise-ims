
package th.go.excise.ims.ws.client.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
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
@Table(name = "WS_INCFRI8020_REC")
public class WsIncfri8020Rec extends BaseEntity {

	private static final long serialVersionUID = 6799396639819412409L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_INCFRI8020_REC_GEN")
	@SequenceGenerator(name = "WS_INCFRI8020_REC_GEN", sequenceName = "WS_INCFRI8020_REC_SEQ", allocationSize = 1)
	@Column(name = "IA_WS_INCFRI8020_REC_ID")
	private BigDecimal iaWsIncfri8020RecId;
	@Column(name = "DEPOSIT_DATE")
	private Date depositDate;
	@Column(name = "SEND_DATE")
	private Date sendDate;
	@Column(name = "RECEIPT_DATE")
	private Date receiptDate;
	@Column(name = "INCOME_NAME")
	private String incomeName;
	@Column(name = "RECEIPT_NO")
	private String receiptNo;
	@Column(name = "NET_TAX_AMOUNT")
	private BigDecimal netTaxAmount;
	@Column(name = "NET_LOC_AMOUNT")
	private BigDecimal netLocAmount;
	@Column(name = "LOC_OTH_AMOUNT")
	private BigDecimal locOthAmount;
	@Column(name = "LOC_EXP_AMOUNT")
	private BigDecimal locExpAmount;
	@Column(name = "OLDER_FUND_AMOUNT")
	private BigDecimal olderFundAmount;
	@Column(name = "TPBS_FUND_AMOUNT")
	private BigDecimal tpbsFundAmount;
	@Column(name = "SEND_AMOUNT")
	private BigDecimal sendAmount;
	@Column(name = "STAMP_AMOUNT")
	private BigDecimal stampAmount;
	@Column(name = "CUSTOM_AMOUNT")
	private BigDecimal customAmount;
	@Column(name = "TRN_DATE")
	private Date trnDate;
	@Column(name = "OFFICE_RECEIVE")
	private String officeReceive;
	@Column(name = "INCOME_CODE")
	private String incomeCode;
	@Column(name = "RECEIPT_NO_OLDER_FUND")
	private String receiptNoOlderFund;
	@Column(name = "RECEIPT_NO_TPBS_FUND")
	private String receiptNoTpbsFund;
	@Column(name = "RECEIPT_NO_SSS_FUND")
	private String receiptNoSssFund;
	@Column(name = "RECEIPT_NO_SPORT_FUND")
	private String receiptNoSportFund;
	@Column(name = "SPORT_FUND_AMOUNT")
	private BigDecimal sportFundAmount;
	@Column(name = "PIN_NID_ID")
	private String pinNidId;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "CUS_NAME")
	private String cusName;
	@Column(name = "FAC_NAME")
	private String facName;
	@Column(name = "IS_DELETED")
	private String isDeleted;
	@Column(name = "VERSION")
	private BigDecimal version;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	public BigDecimal getIaWsIncfri8020RecId() {
		return iaWsIncfri8020RecId;
	}

	public void setIaWsIncfri8020RecId(BigDecimal iaWsIncfri8020RecId) {
		this.iaWsIncfri8020RecId = iaWsIncfri8020RecId;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getIncomeName() {
		return incomeName;
	}

	public void setIncomeName(String incomeName) {
		this.incomeName = incomeName;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public BigDecimal getNetTaxAmount() {
		return netTaxAmount;
	}

	public void setNetTaxAmount(BigDecimal netTaxAmount) {
		this.netTaxAmount = netTaxAmount;
	}

	public BigDecimal getNetLocAmount() {
		return netLocAmount;
	}

	public void setNetLocAmount(BigDecimal netLocAmount) {
		this.netLocAmount = netLocAmount;
	}

	public BigDecimal getLocOthAmount() {
		return locOthAmount;
	}

	public void setLocOthAmount(BigDecimal locOthAmount) {
		this.locOthAmount = locOthAmount;
	}

	public BigDecimal getLocExpAmount() {
		return locExpAmount;
	}

	public void setLocExpAmount(BigDecimal locExpAmount) {
		this.locExpAmount = locExpAmount;
	}

	public BigDecimal getOlderFundAmount() {
		return olderFundAmount;
	}

	public void setOlderFundAmount(BigDecimal olderFundAmount) {
		this.olderFundAmount = olderFundAmount;
	}

	public BigDecimal getTpbsFundAmount() {
		return tpbsFundAmount;
	}

	public void setTpbsFundAmount(BigDecimal tpbsFundAmount) {
		this.tpbsFundAmount = tpbsFundAmount;
	}

	public BigDecimal getSendAmount() {
		return sendAmount;
	}

	public void setSendAmount(BigDecimal sendAmount) {
		this.sendAmount = sendAmount;
	}

	public BigDecimal getStampAmount() {
		return stampAmount;
	}

	public void setStampAmount(BigDecimal stampAmount) {
		this.stampAmount = stampAmount;
	}

	public BigDecimal getCustomAmount() {
		return customAmount;
	}

	public void setCustomAmount(BigDecimal customAmount) {
		this.customAmount = customAmount;
	}

	public Date getTrnDate() {
		return trnDate;
	}

	public void setTrnDate(Date trnDate) {
		this.trnDate = trnDate;
	}

	public String getOfficeReceive() {
		return officeReceive;
	}

	public void setOfficeReceive(String officeReceive) {
		this.officeReceive = officeReceive;
	}

	public String getIncomeCode() {
		return incomeCode;
	}

	public void setIncomeCode(String incomeCode) {
		this.incomeCode = incomeCode;
	}

	public String getReceiptNoOlderFund() {
		return receiptNoOlderFund;
	}

	public void setReceiptNoOlderFund(String receiptNoOlderFund) {
		this.receiptNoOlderFund = receiptNoOlderFund;
	}

	public String getReceiptNoTpbsFund() {
		return receiptNoTpbsFund;
	}

	public void setReceiptNoTpbsFund(String receiptNoTpbsFund) {
		this.receiptNoTpbsFund = receiptNoTpbsFund;
	}

	public String getReceiptNoSssFund() {
		return receiptNoSssFund;
	}

	public void setReceiptNoSssFund(String receiptNoSssFund) {
		this.receiptNoSssFund = receiptNoSssFund;
	}

	public String getReceiptNoSportFund() {
		return receiptNoSportFund;
	}

	public void setReceiptNoSportFund(String receiptNoSportFund) {
		this.receiptNoSportFund = receiptNoSportFund;
	}

	public BigDecimal getSportFundAmount() {
		return sportFundAmount;
	}

	public void setSportFundAmount(BigDecimal sportFundAmount) {
		this.sportFundAmount = sportFundAmount;
	}

	public String getPinNidId() {
		return pinNidId;
	}

	public void setPinNidId(String pinNidId) {
		this.pinNidId = pinNidId;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getFacName() {
		return facName;
	}

	public void setFacName(String facName) {
		this.facName = facName;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
