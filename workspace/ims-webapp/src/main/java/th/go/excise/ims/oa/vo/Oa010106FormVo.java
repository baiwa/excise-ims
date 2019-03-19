package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import th.go.excise.ims.oa.persistence.entity.OaHydCustomerLicenDtl;

public class Oa010106FormVo {
	private BigDecimal oaCuslicenseId;
	private BigDecimal oaCustomerId;
	private String licenseType;
	private String licenseNo;
	private Date licenseDate;
	private String oldLicenseYear;
	private String bankGuarantee;
	private String bankGuaranteeNo;
	private Date bankGuaranteeDate;
	private String operateName;
	private String operateRemark;
	private String approveName;
	private Date startDate;
	private Date endDate;
	private String offCode;
	private Date receiveDate;
	private String receiveNo;
	private String approve;
	private List<OaHydCustomerLicenDtl> details;
	private List<OaHydCustomerLicenDtl> deletes;

	public BigDecimal getOaCuslicenseId() {
		return oaCuslicenseId;
	}

	public void setOaCuslicenseId(BigDecimal oaCuslicenseId) {
		this.oaCuslicenseId = oaCuslicenseId;
	}

	public BigDecimal getOaCustomerId() {
		return oaCustomerId;
	}

	public void setOaCustomerId(BigDecimal oaCustomerId) {
		this.oaCustomerId = oaCustomerId;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public Date getLicenseDate() {
		return licenseDate;
	}

	public void setLicenseDate(Date licenseDate) {
		this.licenseDate = licenseDate;
	}

	public String getOldLicenseYear() {
		return oldLicenseYear;
	}

	public void setOldLicenseYear(String oldLicenseYear) {
		this.oldLicenseYear = oldLicenseYear;
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

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getOperateRemark() {
		return operateRemark;
	}

	public void setOperateRemark(String operateRemark) {
		this.operateRemark = operateRemark;
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

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getReceiveNo() {
		return receiveNo;
	}

	public void setReceiveNo(String receiveNo) {
		this.receiveNo = receiveNo;
	}

	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

	public List<OaHydCustomerLicenDtl> getDetails() {
		return details;
	}

	public void setDetails(List<OaHydCustomerLicenDtl> details) {
		this.details = details;
	}

	public List<OaHydCustomerLicenDtl> getDeletes() {
		return deletes;
	}

	public void setDeletes(List<OaHydCustomerLicenDtl> deletes) {
		this.deletes = deletes;
	}
}
