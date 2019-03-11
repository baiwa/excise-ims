package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

public class Oa02030101FormVo {
	    private BigDecimal oaCustomerId;
	    private String licenType;
	    private String licenNo;
	    private Date licenDate;
	    private String oldLicenYear;
	    private String bankGuarantee;
	    private String bankGuaranteeNo;
	    private Date bankGuaranteeDate;
	    private String operrateName;
	    private String operrateRemark;
	    private String approveName;
	    private Date startDate;
	    private Date endDate;
	    private String offCode;
	    private Date reciveDate;
	    private String reciveNo;
	    private String approve;
	     
		public BigDecimal getOaCustomerId() {
			return oaCustomerId;
		}
		public void setOaCustomerId(BigDecimal oaCustomerId) {
			this.oaCustomerId = oaCustomerId;
		}
		public String getLicenType() {
			return licenType;
		}
		public void setLicenType(String licenType) {
			this.licenType = licenType;
		}
		public String getLicenNo() {
			return licenNo;
		}
		public void setLicenNo(String licenNo) {
			this.licenNo = licenNo;
		}
		public Date getLicenDate() {
			return licenDate;
		}
		public void setLicenDate(Date licenDate) {
			this.licenDate = licenDate;
		}
		public String getOldLicenYear() {
			return oldLicenYear;
		}
		public void setOldLicenYear(String oldLicenYear) {
			this.oldLicenYear = oldLicenYear;
		}
		public String getBankGuarantee() {
			return bankGuarantee;
		}
		public void setBankGuarantee(String bankGuarantee) {
			this.bankGuarantee = bankGuarantee;
		}
		public String getBankGuaranteeNo() {
			return bankGuaranteeNo;
		}
		public void setBankGuaranteeNo(String bankGuaranteeNo) {
			this.bankGuaranteeNo = bankGuaranteeNo;
		}
		public Date getBankGuaranteeDate() {
			return bankGuaranteeDate;
		}
		public void setBankGuaranteeDate(Date bankGuaranteeDate) {
			this.bankGuaranteeDate = bankGuaranteeDate;
		}
		public String getOperrateName() {
			return operrateName;
		}
		public void setOperrateName(String operrateName) {
			this.operrateName = operrateName;
		}
		public String getOperrateRemark() {
			return operrateRemark;
		}
		public void setOperrateRemark(String operrateRemark) {
			this.operrateRemark = operrateRemark;
		}
		public String getApproveName() {
			return approveName;
		}
		public void setApproveName(String approveName) {
			this.approveName = approveName;
		}
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		public String getOffCode() {
			return offCode;
		}
		public void setOffCode(String offCode) {
			this.offCode = offCode;
		}
		public Date getReciveDate() {
			return reciveDate;
		}
		public void setReciveDate(Date reciveDate) {
			this.reciveDate = reciveDate;
		}
		public String getReciveNo() {
			return reciveNo;
		}
		public void setReciveNo(String reciveNo) {
			this.reciveNo = reciveNo;
		}
		public String getApprove() {
			return approve;
		}
		public void setApprove(String approve) {
			this.approve = approve;
		}
	    

	    
	    
	
}
