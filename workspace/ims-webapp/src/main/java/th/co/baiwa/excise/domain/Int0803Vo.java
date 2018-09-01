package th.co.baiwa.excise.domain;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcAreaHdr;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssOtherDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsHdr;

public class Int0803Vo {

	private List<RiskAssOtherDtl> riskAssOtherDtlList;
	private String projectBase;
	private String riskHrdName;
	private String rl;
	private String budgetYear;
	private List<String> riskHrdNameList;
	private String departmentName;
	private List<RiskAssExcAreaHdr> riskAssExcAreaHdrList;

	public List<RiskAssOtherDtl> getRiskAssOtherDtlList() {
		return riskAssOtherDtlList;
	}

	public void setRiskAssOtherDtlList(List<RiskAssOtherDtl> riskAssOtherDtlList) {
		this.riskAssOtherDtlList = riskAssOtherDtlList;
	}

	public String getProjectBase() {
		return projectBase;
	}

	public void setProjectBase(String projectBase) {
		this.projectBase = projectBase;
	}

	public String getRl() {
		return rl;
	}

	public void setRl(String rl) {
		this.rl = rl;
	}

	public String getRiskHrdName() {
		return riskHrdName;
	}

	public void setRiskHrdName(String riskHrdName) {
		this.riskHrdName = riskHrdName;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public List<String> getRiskHrdNameList() {
		return riskHrdNameList;
	}

	public void setRiskHrdNameList(List<String> riskHrdNameList) {
		this.riskHrdNameList = riskHrdNameList;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<RiskAssExcAreaHdr> getRiskAssExcAreaHdrList() {
		return riskAssExcAreaHdrList;
	}

	public void setRiskAssExcAreaHdrList(List<RiskAssExcAreaHdr> riskAssExcAreaHdrList) {
		this.riskAssExcAreaHdrList = riskAssExcAreaHdrList;
	}


}