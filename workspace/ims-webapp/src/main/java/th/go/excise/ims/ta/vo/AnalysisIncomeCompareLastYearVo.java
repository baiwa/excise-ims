package th.go.excise.ims.ta.vo;

public class AnalysisIncomeCompareLastYearVo {
	private String taxMonth;
	private String incomeLastYear;
	private String incomeCurrentYear;
	private String diffIncomeAmt;
	private String diffIncomePnt;
	public String getTaxMonth() {
		return taxMonth;
	}
	public void setTaxMonth(String taxMonth) {
		this.taxMonth = taxMonth;
	}
	public String getIncomeLastYear() {
		return incomeLastYear;
	}
	public void setIncomeLastYear(String incomeLastYear) {
		this.incomeLastYear = incomeLastYear;
	}
	public String getIncomeCurrentYear() {
		return incomeCurrentYear;
	}
	public void setIncomeCurrentYear(String incomeCurrentYear) {
		this.incomeCurrentYear = incomeCurrentYear;
	}
	public String getDiffIncomeAmt() {
		return diffIncomeAmt;
	}
	public void setDiffIncomeAmt(String diffIncomeAmt) {
		this.diffIncomeAmt = diffIncomeAmt;
	}
	public String getDiffIncomePnt() {
		return diffIncomePnt;
	}
	public void setDiffIncomePnt(String diffIncomePnt) {
		this.diffIncomePnt = diffIncomePnt;
	}
}
