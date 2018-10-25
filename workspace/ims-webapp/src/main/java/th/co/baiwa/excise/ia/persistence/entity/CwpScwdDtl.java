package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the IA_CWP_SCWD_DTL database table.
 * 
 */
@Entity
@Table(name="IA_CWP_SCWD_DTL")
public class CwpScwdDtl extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4452436677726969797L;

	@Id
	@SequenceGenerator(name="IA_CWP_SCWD_DTL_GEN", sequenceName="IA_CWP_SCWD_DTL_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_CWP_SCWD_DTL_GEN")
	@Column(name="CWP_SCWD_DTL_ID")
	private Long cwpScwdDtlId;

	@Column(name="BANK_ACCOUNT")
	private String bankAccount;

	@Column(name="BUDGET_CODE")
	private String budgetCode;

	@Column(name="CWP_SCWD_HDR_ID")
	private Long cwpScwdHdrId;

	@Column(name="DUCUMENT_NUMBER")
	private String ducumentNumber;

	private BigDecimal fee;

	private BigDecimal fines;

	@Column(name="NET_AMOUNT")
	private BigDecimal netAmount;

	@Column(name="POST_DATE")
	private Date postDate;

	@Column(name="RECORD_DATE")
	private Date recordDate;

	@Column(name="REFERENCE_NO")
	private String referenceNo;

	private String seller;

	@Column(name="TYPE_CODE")
	private String typeCode;

	@Column(name="WITHDRAW_AMOUNT")
	private BigDecimal withdrawAmount;

	@Column(name="WITHHOLDING_TAX")
	private BigDecimal withholdingTax;
	
	@Transient
	private String postDateStr;

	@Transient
	private String recordDateStr;
	
	@Transient
	private Long idExcel2;
	
	public CwpScwdDtl() {
		fee = new BigDecimal(0);
		fines = new BigDecimal(0);
		netAmount = new BigDecimal(0);
		withdrawAmount = new BigDecimal(0);
		withholdingTax = new BigDecimal(0);
	}

	public Long getCwpScwdDtlId() {
		return cwpScwdDtlId;
	}

	public void setCwpScwdDtlId(Long cwpScwdDtlId) {
		this.cwpScwdDtlId = cwpScwdDtlId;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBudgetCode() {
		return budgetCode;
	}

	public void setBudgetCode(String budgetCode) {
		this.budgetCode = budgetCode;
	}

	public Long getCwpScwdHdrId() {
		return cwpScwdHdrId;
	}

	public void setCwpScwdHdrId(Long cwpScwdHdrId) {
		this.cwpScwdHdrId = cwpScwdHdrId;
	}

	public String getDucumentNumber() {
		return ducumentNumber;
	}

	public void setDucumentNumber(String ducumentNumber) {
		this.ducumentNumber = ducumentNumber;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public BigDecimal getFines() {
		return fines;
	}

	public void setFines(BigDecimal fines) {
		this.fines = fines;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public BigDecimal getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(BigDecimal withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public BigDecimal getWithholdingTax() {
		return withholdingTax;
	}

	public void setWithholdingTax(BigDecimal withholdingTax) {
		this.withholdingTax = withholdingTax;
	}

	public String getPostDateStr() {
		return postDateStr;
	}

	public void setPostDateStr(String postDateStr) {
		this.postDateStr = postDateStr;
	}

	public String getRecordDateStr() {
		return recordDateStr;
	}

	public void setRecordDateStr(String recordDateStr) {
		this.recordDateStr = recordDateStr;
	}

	public Long getIdExcel2() {
		return idExcel2;
	}

	public void setIdExcel2(Long idExcel2) {
		this.idExcel2 = idExcel2;
	}

}