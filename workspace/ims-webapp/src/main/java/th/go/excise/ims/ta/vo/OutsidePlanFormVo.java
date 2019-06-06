package th.go.excise.ims.ta.vo;

import java.util.Date;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class OutsidePlanFormVo extends DataTableRequest {
	private static final long serialVersionUID = -7514848174971171413L;

	private String officeCode;
	private String cusFullname;
	private String facFullname;
	private String dutyCode;
	private String facType;
	private Date fromDate;
	private Date toDate;
	private String cusType;
	private String cusId;
	private String flagOut;
	private String planNumber;

	public String getCusFullname() {
		return cusFullname;
	}

	public void setCusFullname(String cusFullname) {
		this.cusFullname = cusFullname;
	}

	public String getFacFullname() {
		return facFullname;
	}

	public void setFacFullname(String facFullname) {
		this.facFullname = facFullname;
	}

	public String getDutyCode() {
		return dutyCode;
	}

	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}

	public String getFacType() {
		return facType;
	}

	public void setFacType(String facType) {
		this.facType = facType;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getCusType() {
		return cusType;
	}

	public void setCusType(String cusType) {
		this.cusType = cusType;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getFlagOut() {
		return flagOut;
	}

	public void setFlagOut(String flagOut) {
		this.flagOut = flagOut;
	}

	public String getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}
	
	
}
