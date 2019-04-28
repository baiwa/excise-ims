package th.go.excise.ims.ws.persistence.entity;

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
@Table(name = "WS_INCFRI8000")
public class WsIncfri8000 extends BaseEntity {

	private static final long serialVersionUID = -8985958631247590208L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_INCFRI8000_GEN")
	@SequenceGenerator(name = "WS_INCFRI8000_GEN", sequenceName = "WS_INCFRI8000_SEQ", allocationSize = 1)
	@Column(name = "INCFRI8000_ID")
	private Long incfri8000Id;
	@Column(name = "REG_ID")
	private String regId;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "RECEIPT_NO")
	private String receiptNo;
	@Column(name = "RECEIPT_DATE")
	private String receiptDate;
	@Column(name = "TAX_AMOUNT")
	private BigDecimal taxAmount;
	@Column(name = "PEN_AMOUNT")
	private BigDecimal penAmount;
	@Column(name = "ADD_AMOUNT")
	private BigDecimal addAmount;
	@Column(name = "REDUCE_AMOUNT")
	private BigDecimal reduceAmount;
	@Column(name = "CREDIT_AMOUNT")
	private BigDecimal creditAmount;
	@Column(name = "OFFICE_RECEIVE_CODE")
	private String officeReceiveCode;
	@Column(name = "TRN_DATE")
	private String trnDate;
	@Column(name = "DEPOSIT_DATE")
	private String depositDate;
	@Column(name = "SEND_DATE")
	private String sendDate;
	@Column(name = "INCOME_CODE")
	private String incomeCode;
	@Column(name = "INCOME_TYPE")
	private String incomeType;

	public Long getIncfri8000Id() {
		return incfri8000Id;
	}

	public void setIncfri8000Id(Long incfri8000Id) {
		this.incfri8000Id = incfri8000Id;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getPenAmount() {
		return penAmount;
	}

	public void setPenAmount(BigDecimal penAmount) {
		this.penAmount = penAmount;
	}

	public BigDecimal getAddAmount() {
		return addAmount;
	}

	public void setAddAmount(BigDecimal addAmount) {
		this.addAmount = addAmount;
	}

	public BigDecimal getReduceAmount() {
		return reduceAmount;
	}

	public void setReduceAmount(BigDecimal reduceAmount) {
		this.reduceAmount = reduceAmount;
	}

	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getOfficeReceiveCode() {
		return officeReceiveCode;
	}

	public void setOfficeReceiveCode(String officeReceiveCode) {
		this.officeReceiveCode = officeReceiveCode;
	}

	public String getTrnDate() {
		return trnDate;
	}

	public void setTrnDate(String trnDate) {
		this.trnDate = trnDate;
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

	public String getIncomeCode() {
		return incomeCode;
	}

	public void setIncomeCode(String incomeCode) {
		this.incomeCode = incomeCode;
	}

	public String getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}

}
