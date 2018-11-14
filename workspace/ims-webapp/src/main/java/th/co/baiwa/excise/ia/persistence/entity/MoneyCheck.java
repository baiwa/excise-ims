package th.co.baiwa.excise.ia.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the IA_MONEY_CHECK database table.
 * 
 */
@Entity
@Table(name="IA_MONEY_CHECK")
public class MoneyCheck extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7844053805186006567L;

	@Id
	@SequenceGenerator(name="IA_MONEY_CHECK_GEN", sequenceName="IA_MONEY_CHECK_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_MONEY_CHECK_GEN")
	private BigDecimal id;

	@Column(name="DEPOSIT_DATE")
	private Date depositDate;

	@Column(name="INCOME_CODE")
	private BigDecimal incomeCode;

	@Column(name="INCOME_NAME")
	private String incomeName;

	@Column(name="NETLOC_AMOUNT")
	private BigDecimal netlocAmount;

	@Column(name="NETTAX_AMOUNT")
	private BigDecimal nettaxAmount;

	@Column(name="OFFICE_RECEIVE")
	private BigDecimal officeReceive;

	@Column(name="OLDER_FUND_AMOUNT")
	private BigDecimal olderFundAmount;

	@Column(name="RECEIPT_DATE")
	private Date receiptDate;

	@Column(name="RECEIPT_NO")
	private String receiptNo;

	@Column(name="RECEIPT_NO_OLDER_FUND")
	private String receiptNoOlderFund;

	@Column(name="RECEIPT_NO_SPORT_FUND")
	private String receiptNoSportFund;

	@Column(name="RECEIPT_NO_SSS_FUND")
	private String receiptNoSssFund;

	@Column(name="RECEIPT_NO_TPBS_FUND")
	private String receiptNoTpbsFund;

	@Column(name="SEND_AMOUNT")
	private BigDecimal sendAmount;

	@Column(name="SPORT_FUND_AMOUNT")
	private BigDecimal sportFundAmount;

	@Column(name="SSS_FUND_AMOUNT")
	private BigDecimal sssFundAmount;

	@Column(name="STAMP_AMOUNT")
	private BigDecimal stampAmount;

	@Column(name="TPBS_FUND_AMOUNT")
	private BigDecimal tpbsFundAmount;

	@Column(name="TRN_DATE")
	private Date trnDate;
	
	@Transient
	private String depositDateStr;
	
	@Transient
	private String statusDate;
	
	@Transient
	private String statusMoney;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public BigDecimal getIncomeCode() {
		return incomeCode;
	}

	public void setIncomeCode(BigDecimal incomeCode) {
		this.incomeCode = incomeCode;
	}

	public String getIncomeName() {
		return incomeName;
	}

	public void setIncomeName(String incomeName) {
		this.incomeName = incomeName;
	}

	public BigDecimal getNetlocAmount() {
		return netlocAmount;
	}

	public void setNetlocAmount(BigDecimal netlocAmount) {
		this.netlocAmount = netlocAmount;
	}

	public BigDecimal getNettaxAmount() {
		return nettaxAmount;
	}

	public void setNettaxAmount(BigDecimal nettaxAmount) {
		this.nettaxAmount = nettaxAmount;
	}

	public BigDecimal getOfficeReceive() {
		return officeReceive;
	}

	public void setOfficeReceive(BigDecimal officeReceive) {
		this.officeReceive = officeReceive;
	}

	public BigDecimal getOlderFundAmount() {
		return olderFundAmount;
	}

	public void setOlderFundAmount(BigDecimal olderFundAmount) {
		this.olderFundAmount = olderFundAmount;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getReceiptNoOlderFund() {
		return receiptNoOlderFund;
	}

	public void setReceiptNoOlderFund(String receiptNoOlderFund) {
		this.receiptNoOlderFund = receiptNoOlderFund;
	}

	public String getReceiptNoSportFund() {
		return receiptNoSportFund;
	}

	public void setReceiptNoSportFund(String receiptNoSportFund) {
		this.receiptNoSportFund = receiptNoSportFund;
	}

	public String getReceiptNoSssFund() {
		return receiptNoSssFund;
	}

	public void setReceiptNoSssFund(String receiptNoSssFund) {
		this.receiptNoSssFund = receiptNoSssFund;
	}

	public String getReceiptNoTpbsFund() {
		return receiptNoTpbsFund;
	}

	public void setReceiptNoTpbsFund(String receiptNoTpbsFund) {
		this.receiptNoTpbsFund = receiptNoTpbsFund;
	}

	public BigDecimal getSendAmount() {
		return sendAmount;
	}

	public void setSendAmount(BigDecimal sendAmount) {
		this.sendAmount = sendAmount;
	}

	public BigDecimal getSportFundAmount() {
		return sportFundAmount;
	}

	public void setSportFundAmount(BigDecimal sportFundAmount) {
		this.sportFundAmount = sportFundAmount;
	}

	public BigDecimal getSssFundAmount() {
		return sssFundAmount;
	}

	public void setSssFundAmount(BigDecimal sssFundAmount) {
		this.sssFundAmount = sssFundAmount;
	}

	public BigDecimal getStampAmount() {
		return stampAmount;
	}

	public void setStampAmount(BigDecimal stampAmount) {
		this.stampAmount = stampAmount;
	}

	public BigDecimal getTpbsFundAmount() {
		return tpbsFundAmount;
	}

	public void setTpbsFundAmount(BigDecimal tpbsFundAmount) {
		this.tpbsFundAmount = tpbsFundAmount;
	}

	public Date getTrnDate() {
		return trnDate;
	}

	public void setTrnDate(Date trnDate) {
		this.trnDate = trnDate;
	}

	public String getDepositDateStr() {
		return depositDateStr;
	}

	public void setDepositDateStr(String depositDateStr) {
		this.depositDateStr = depositDateStr;
	}

	public String getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}

	public String getStatusMoney() {
		return statusMoney;
	}

	public void setStatusMoney(String statusMoney) {
		this.statusMoney = statusMoney;
	}

}