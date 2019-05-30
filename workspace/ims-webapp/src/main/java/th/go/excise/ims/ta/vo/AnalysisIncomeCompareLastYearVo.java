package th.go.excise.ims.ta.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class AnalysisIncomeCompareLastYearVo extends DataTableRequest {

	private static final long serialVersionUID = 915242216213450730L;

	private String taxMonth;
	private String incomeLastYearAmt;
	private String incomeCurrentYearAmt;
	private String diffIncomeAmt;
	private String diffIncomePnt;

	public String getTaxMonth() {
		return taxMonth;
	}

	public void setTaxMonth(String taxMonth) {
		this.taxMonth = taxMonth;
	}

	public String getIncomeLastYearAmt() {
		return incomeLastYearAmt;
	}

	public void setIncomeLastYearAmt(String incomeLastYearAmt) {
		this.incomeLastYearAmt = incomeLastYearAmt;
	}

	public String getIncomeCurrentYearAmt() {
		return incomeCurrentYearAmt;
	}

	public void setIncomeCurrentYearAmt(String incomeCurrentYearAmt) {
		this.incomeCurrentYearAmt = incomeCurrentYearAmt;
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
