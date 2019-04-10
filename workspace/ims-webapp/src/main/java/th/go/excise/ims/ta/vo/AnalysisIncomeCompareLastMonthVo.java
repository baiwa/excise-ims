package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;

public class AnalysisIncomeCompareLastMonthVo {
	private String taxMonth;
	private BigDecimal incomeAmt;
	private BigDecimal diffIncomeAmt;
	private BigDecimal diffIncomePnt;

	public String getTaxMonth() {
		return taxMonth;
	}

	public void setTaxMonth(String taxMonth) {
		this.taxMonth = taxMonth;
	}

	public BigDecimal getIncomeAmt() {
		return incomeAmt;
	}

	public void setIncomeAmt(BigDecimal incomeAmt) {
		this.incomeAmt = incomeAmt;
	}

	public BigDecimal getDiffIncomeAmt() {
		return diffIncomeAmt;
	}

	public void setDiffIncomeAmt(BigDecimal diffIncomeAmt) {
		this.diffIncomeAmt = diffIncomeAmt;
	}

	public BigDecimal getDiffIncomePnt() {
		return diffIncomePnt;
	}

	public void setDiffIncomePnt(BigDecimal diffIncomePnt) {
		this.diffIncomePnt = diffIncomePnt;
	}

}
