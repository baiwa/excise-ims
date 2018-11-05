package th.co.baiwa.excise.ia.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


@Entity
@Table(name="IA_INCOME_EXCISE_AUD_DTL")
public class IncomeExciseAudDtl extends BaseEntity{

	private static final long serialVersionUID = -4808526608720714322L;

	@Id
	@Column(name="IA_INCOME_EXCISE_AUD_DTL_ID")
	private Long iaIncomeExciseAudDtlId;

	@Column(name="IA_INCOME_EXCISE_AUD_ID")
	private Long iaIncomeExciseAudId;

	@Column(name="INCOME_CODE")
	private String incomeCode;

	@Column(name="INCOME_NAME")
	private String incomeName;

	@Column(name="OFFICE_CODE")
	private String officeCode;

	@Column(name="PIN_NID_ID")
	private String pinNidId;

	@Column(name="RECEIPT_NO")
	private String receiptNo;
	
	@Column(name="RECEIPT_DATE")
	private String receiptDate;
	
	@Column(name="RECEIPT_STATUS")
	private String receiptStatus;
	
	
	
	

	public Long getIaIncomeExciseAudDtlId() {
		return iaIncomeExciseAudDtlId;
	}

	public void setIaIncomeExciseAudDtlId(Long iaIncomeExciseAudDtlId) {
		this.iaIncomeExciseAudDtlId = iaIncomeExciseAudDtlId;
	}

	

	public String getIncomeCode() {
		return incomeCode;
	}

	public void setIncomeCode(String incomeCode) {
		this.incomeCode = incomeCode;
	}

	public String getIncomeName() {
		return incomeName;
	}

	public void setIncomeName(String incomeName) {
		this.incomeName = incomeName;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getPinNidId() {
		return pinNidId;
	}

	public void setPinNidId(String pinNidId) {
		this.pinNidId = pinNidId;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public Long getIaIncomeExciseAudId() {
		return iaIncomeExciseAudId;
	}

	public void setIaIncomeExciseAudId(Long iaIncomeExciseAudId) {
		this.iaIncomeExciseAudId = iaIncomeExciseAudId;
	}

	public String getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getReceiptStatus() {
		return receiptStatus;
	}

	public void setReceiptStatus(String receiptStatus) {
		this.receiptStatus = receiptStatus;
	}


}