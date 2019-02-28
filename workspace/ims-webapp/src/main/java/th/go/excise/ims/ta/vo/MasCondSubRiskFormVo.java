package th.go.excise.ims.ta.vo;

import java.util.List;

public class MasCondSubRiskFormVo {

	private String budgetYear;
	private List<MasCondSubRiskListFormVo> riskList;
	
	public String getBudgetYear() {
		return budgetYear;
	}
	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}
	public List<MasCondSubRiskListFormVo> getRiskList() {
		return riskList;
	}
	public void setRiskList(List<MasCondSubRiskListFormVo> riskList) {
		this.riskList = riskList;
	}
	
}
