package th.go.excise.ims.ta.vo;

import java.util.Date;

public class PaperBasicAnalysisVo {
	private String paperBaNo;
	private String officeCode;
	private String budgetYear;
	private String newRegId;
	private String dutyCode;
	private Date analysisDateStart;
	private Date analysisDateEnd;

	public String getPaperBaNo() {
		return paperBaNo;
	}

	public void setPaperBaNo(String paperBaNo) {
		this.paperBaNo = paperBaNo;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getDutyCode() {
		return dutyCode;
	}

	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}

	public Date getAnalysisDateStart() {
		return analysisDateStart;
	}

	public void setAnalysisDateStart(Date analysisDateStart) {
		this.analysisDateStart = analysisDateStart;
	}

	public Date getAnalysisDateEnd() {
		return analysisDateEnd;
	}

	public void setAnalysisDateEnd(Date analysisDateEnd) {
		this.analysisDateEnd = analysisDateEnd;
	}

}
