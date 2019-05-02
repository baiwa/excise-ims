package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.Date;

public class IaAuditIncD1Vo {
	
	private Long iaAuditIncDId;
	private String auditIncNo;
	private String officeCode;
	private String docCtlNo;
	private String receiptNo;
	private BigDecimal runCheck;
	private String receiptDate;
	private String taxName;
	private String taxCode;
	private BigDecimal amount;
	private String remark;
	public Long getIaAuditIncDId() {
		return iaAuditIncDId;
	}
	public void setIaAuditIncDId(Long iaAuditIncDId) {
		this.iaAuditIncDId = iaAuditIncDId;
	}
	public String getAuditIncNo() {
		return auditIncNo;
	}
	public void setAuditIncNo(String auditIncNo) {
		this.auditIncNo = auditIncNo;
	}
	public String getOfficeCode() {
		return officeCode;
	}
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	public String getDocCtlNo() {
		return docCtlNo;
	}
	public void setDocCtlNo(String docCtlNo) {
		this.docCtlNo = docCtlNo;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public BigDecimal getRunCheck() {
		return runCheck;
	}
	public void setRunCheck(BigDecimal runCheck) {
		this.runCheck = runCheck;
	}
	public String getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	


}
