package th.go.excise.ims.ia.vo;

import java.util.Date;

public class Int0610SearchVo {
	private String officeCode;
	private String periodFrom;
	private String periodTo;
	private String periodFromStr;
	private String periodToStr;
	private String glAccNo;
	private String deptDisb;
	private Date periodFromDate;
	private Date periodToDate;

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getPeriodFrom() {
		return periodFrom;
	}

	public void setPeriodFrom(String periodFrom) {
		this.periodFrom = periodFrom;
	}

	public String getPeriodTo() {
		return periodTo;
	}

	public void setPeriodTo(String periodTo) {
		this.periodTo = periodTo;
	}

	public String getGlAccNo() {
		return glAccNo;
	}

	public void setGlAccNo(String glAccNo) {
		this.glAccNo = glAccNo;
	}

	public String getDeptDisb() {
		return deptDisb;
	}

	public void setDeptDisb(String deptDisb) {
		this.deptDisb = deptDisb;
	}

	public String getPeriodFromStr() {
		return periodFromStr;
	}

	public void setPeriodFromStr(String periodFromStr) {
		this.periodFromStr = periodFromStr;
	}

	public String getPeriodToStr() {
		return periodToStr;
	}

	public void setPeriodToStr(String periodToStr) {
		this.periodToStr = periodToStr;
	}

	public Date getPeriodFromDate() {
		return periodFromDate;
	}

	public void setPeriodFromDate(Date periodFromDate) {
		this.periodFromDate = periodFromDate;
	}

	public Date getPeriodToDate() {
		return periodToDate;
	}

	public void setPeriodToDate(Date periodToDate) {
		this.periodToDate = periodToDate;
	}

}
