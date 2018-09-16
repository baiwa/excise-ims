package th.co.baiwa.excise.domain;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcAreaHdr;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcOtherDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcOv3dDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcRecDtl;

public class Int0803Vo {

	private List<RiskAssExcOtherDtl> riskAssExcOtherDtlList;
	private String projectBase;
	private String riskHrdName;
	private String rl1;
	private String rl2;
	private String rl;
	private String budgetYear;
	private List<String> riskHrdNameList;
	private String departmentName;
	private String color;
	private String valueTranslation1;
	private String valueTranslation2;
	private String valueTranslation;
	private Integer riskPoint;
	private Double avgRl;
	
	private List<RiskAssExcAreaHdr> riskAssExcAreaHdrList;
	private List<RiskAssExcRecDtl> riskAssExcRecDtlList;
	private List<QtnMasterVo> qtnMasterVoList;
	private List<RiskAssExcOv3dDtl> riskAssExcOv3dDtlList;

	public String getProjectBase() {
		return projectBase;
	}

	public void setProjectBase(String projectBase) {
		this.projectBase = projectBase;
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

	

	public Integer getRiskPoint() {
		return riskPoint;
	}

	public void setRiskPoint(Integer riskPoint) {
		this.riskPoint = riskPoint;
	}

	public List<QtnMasterVo> getQtnMasterVoList() {
		return qtnMasterVoList;
	}

	public void setQtnMasterVoList(List<QtnMasterVo> qtnMasterVoList) {
		this.qtnMasterVoList = qtnMasterVoList;
	}

	public List<RiskAssExcOv3dDtl> getRiskAssExcOv3dDtlList() {
		return riskAssExcOv3dDtlList;
	}

	public void setRiskAssExcOv3dDtlList(List<RiskAssExcOv3dDtl> riskAssExcOv3dDtlList) {
		this.riskAssExcOv3dDtlList = riskAssExcOv3dDtlList;
	}

	public String getRl2() {
		return rl2;
	}

	public void setRl2(String rl2) {
		this.rl2 = rl2;
	}

	public String getValueTranslation2() {
		return valueTranslation2;
	}

	public void setValueTranslation2(String valueTranslation2) {
		this.valueTranslation2 = valueTranslation2;
	}

	public String getRl1() {
		return rl1;
	}

	public void setRl1(String rl1) {
		this.rl1 = rl1;
	}

	public String getValueTranslation1() {
		return valueTranslation1;
	}

	public void setValueTranslation1(String valueTranslation1) {
		this.valueTranslation1 = valueTranslation1;
	}

	public String getRl() {
		return rl;
	}

	public void setRl(String rl) {
		this.rl = rl;
	}

	public String getValueTranslation() {
		return valueTranslation;
	}

	public void setValueTranslation(String valueTranslation) {
		this.valueTranslation = valueTranslation;
	}

	public Double getAvgRl() {
		return avgRl;
	}

	public void setAvgRl(Double avgRl) {
		this.avgRl = avgRl;
	}

}
