package th.go.excise.ims.ta.vo;

import java.util.Date;

public class PlanWorksheetSendTableVo {
	private String officeName;
	private String officeCode;
	private Date sendDate;
	private Date submitDate;
	private String auditStatus;
	private int countPlan;
	
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getOfficeCode() {
		return officeCode;
	}
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public int getCountPlan() {
		return countPlan;
	}
	public void setCountPlan(int countPlan) {
		this.countPlan = countPlan;
	}
	
	

}
