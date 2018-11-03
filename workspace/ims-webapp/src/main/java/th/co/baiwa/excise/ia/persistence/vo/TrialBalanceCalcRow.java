package th.co.baiwa.excise.ia.persistence.vo;

import java.math.BigDecimal;
import java.util.List;

public class TrialBalanceCalcRow {

	private TrialBalanceVo trialBalance;
	private List<TypeTaxVo> typeTaxVoList;

	private BigDecimal totalCal;
	private BigDecimal diff;

	public TrialBalanceVo getTrialBalance() {
		return trialBalance;
	}

	public void setTrialBalance(TrialBalanceVo trialBalance) {
		this.trialBalance = trialBalance;
	}

	public List<TypeTaxVo> getTypeTaxVoList() {
		return typeTaxVoList;
	}

	public void setTypeTaxVoList(List<TypeTaxVo> typeTaxVoList) {
		this.typeTaxVoList = typeTaxVoList;
	}

	public BigDecimal getTotalCal() {
		return totalCal;
	}

	public void setTotalCal(BigDecimal totalCal) {
		this.totalCal = totalCal;
	}

	public BigDecimal getDiff() {
		return diff;
	}

	public void setDiff(BigDecimal diff) {
		this.diff = diff;
	}

}
