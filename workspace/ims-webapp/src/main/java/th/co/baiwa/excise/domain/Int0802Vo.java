package th.co.baiwa.excise.domain;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfOtherDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfHdr;

public class Int0802Vo {

	private List<RiskAssInfOtherDtl> riskAssInfOtherDtlList;
	private String riskAssInfOtherName;
	private String riskAssInfHdrName;
	private String rl;
	private String budgetYear;
	private List<String> riskAssInfHdrNameList;
	private List<RiskAssInfHdr> riskAssInfHdrList;

	public List<RiskAssInfOtherDtl> getRiskAssInfOtherDtlList() {
		return riskAssInfOtherDtlList;
	}

	public void setRiskAssInfOtherDtlList(List<RiskAssInfOtherDtl> riskAssInfOtherDtlList) {
		this.riskAssInfOtherDtlList = riskAssInfOtherDtlList;
	}

	public String getRiskAssInfOtherName() {
		return riskAssInfOtherName;
	}

	public void setRiskAssInfOtherName(String riskAssInfOtherName) {
		this.riskAssInfOtherName = riskAssInfOtherName;
	}

	public String getRiskAssInfHdrName() {
		return riskAssInfHdrName;
	}

	public void setRiskAssInfHdrName(String riskAssInfHdrName) {
		this.riskAssInfHdrName = riskAssInfHdrName;
	}

	public String getRl() {
		return rl;
	}

	public void setRl(String rl) {
		this.rl = rl;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public List<String> getRiskAssInfHdrNameList() {
		return riskAssInfHdrNameList;
	}

	public void setRiskAssInfHdrNameList(List<String> riskAssInfHdrNameList) {
		this.riskAssInfHdrNameList = riskAssInfHdrNameList;
	}

	public List<RiskAssInfHdr> getRiskAssInfHdrList() {
		return riskAssInfHdrList;
	}

	public void setRiskAssInfHdrList(List<RiskAssInfHdr> riskAssInfHdrList) {
		this.riskAssInfHdrList = riskAssInfHdrList;
	}

}
