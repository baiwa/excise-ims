
package th.co.baiwa.excise.ia.persistence.entity.tax;

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
@Table(name = "IA_TAX_RECEIPT")
public class TaxReceipt extends BaseEntity {

	private static final long serialVersionUID = -1413517835451948526L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_TAX_RECEIPT_GEN")
	@SequenceGenerator(name = "IA_TAX_RECEIPT_GEN", sequenceName = "IA_TAX_RECEIPT_SEQ", allocationSize = 1)

	@Column(name = "TAX_RECEIPT_ID")
	private Long taxReceiptId;

	@Column(name = "RECEIPT_DATE")
	private String receiptDate;

	@Column(name = "DEPOSIT_DATE")
	private String depositDate;

	@Column(name = "SEND_DATE")
	private String sendDate;

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

	@Column(name = "OFFICE_CODE")
	private String officeCode;
	
	@Column(name = "DATE_TYPE")
	private String dateType;
	
	@Column(name = "CHECKED_AMOUNT")
	private BigDecimal checkedAmount;
	
	@Column(name = "TAX_PRINT_NO")
	private String taxPrintNo;
	
	@Column(name = "INCOME_CODE")
	private String incomeCode;

	public Long getTaxReceiptId() {
		return taxReceiptId;
	}

	public void setTaxReceiptId(Long taxReceiptId) {
		this.taxReceiptId = taxReceiptId;
	}

	public String getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(String depositDate) {
		this.depositDate = depositDate;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
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

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public BigDecimal getCheckedAmount() {
		return checkedAmount;
	}

	public void setCheckedAmount(BigDecimal checkedAmount) {
		this.checkedAmount = checkedAmount;
	}

	public String getTaxPrintNo() {
		return taxPrintNo;
	}

	public void setTaxPrintNo(String taxPrintNo) {
		this.taxPrintNo = taxPrintNo;
	}

	public String getIncomeCode() {
		return incomeCode;
	}

	public void setIncomeCode(String incomeCode) {
		this.incomeCode = incomeCode;
	}
	
}
