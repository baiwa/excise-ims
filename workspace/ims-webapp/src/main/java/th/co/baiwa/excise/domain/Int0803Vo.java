package th.co.baiwa.excise.domain;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcAreaHdr;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcOtherDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcRecDtl;

public class Int0803Vo {

	private List<RiskAssExcOtherDtl> riskAssExcOtherDtlList;
	private String projectBase;
	private String riskHrdName;
	private String rl;
	private String budgetYear;
	private List<String> riskHrdNameList;
	private String departmentName;
	private String color;
	private String valueTranslation;
	private Integer riskPoint;
	private List<RiskAssExcAreaHdr> riskAssExcAreaHdrList;
	private List<RiskAssExcRecDtl> riskAssExcRecDtlList;


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

	public List<RiskAssExcOtherDtl> getRiskAssExcOtherDtlList() {
		return riskAssExcOtherDtlList;
	}

	public void setRiskAssExcOtherDtlList(List<RiskAssExcOtherDtl> riskAssExcOtherDtlList) {
		this.riskAssExcOtherDtlList = riskAssExcOtherDtlList;
	}

	public List<RiskAssExcRecDtl> getRiskAssExcRecDtlList() {
		return riskAssExcRecDtlList;
	}

	public void setRiskAssExcRecDtlList(List<RiskAssExcRecDtl> riskAssExcRecDtlList) {
		this.riskAssExcRecDtlList = riskAssExcRecDtlList;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getValueTranslation() {
		return valueTranslation;
	}

	public void setValueTranslation(String valueTranslation) {
		this.valueTranslation = valueTranslation;
	}

	public Integer getRiskPoint() {
		return riskPoint;
	}

	public void setRiskPoint(Integer riskPoint) {
		this.riskPoint = riskPoint;
	}

}
