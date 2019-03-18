package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;

public class AnalyzeCompareOldYearVo {

	private Long wsInc8000MId;
	private String newRegId;
	private String taxYear;
	private String taxMonth;
	private BigDecimal taxAmount;
	private BigDecimal taxAmountOld;
	private BigDecimal diff;
	private BigDecimal diffPercent;
	private String monthDesc;
	
	public Long getWsInc8000MId() {
		return wsInc8000MId;
	}
	public void setWsInc8000MId(Long wsInc8000MId) {
		this.wsInc8000MId = wsInc8000MId;
	}
	public String getNewRegId() {
		return newRegId;
	}
	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}
	public String getTaxYear() {
		return taxYear;
	}
	public void setTaxYear(String taxYear) {
		this.taxYear = taxYear;
	}
	public String getTaxMonth() {
		return taxMonth;
	}
	public void setTaxMonth(String taxMonth) {
		this.taxMonth = taxMonth;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	public BigDecimal getTaxAmountOld() {
		return taxAmountOld;
	}
	public void setTaxAmountOld(BigDecimal taxAmountOld) {
		this.taxAmountOld = taxAmountOld;
	}
	public BigDecimal getDiff() {
		return diff;
	}
	public void setDiff(BigDecimal diff) {
		this.diff = diff;
	}
	public BigDecimal getDiffPercent() {
		return diffPercent;
	}
	public void setDiffPercent(BigDecimal diffPercent) {
		this.diffPercent = diffPercent;
	}
	public String getMonthDesc() {
		return monthDesc;
	}
	public void setMonthDesc(String monthDesc) {
		this.monthDesc = monthDesc;
	}
	
}
