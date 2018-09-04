package th.co.baiwa.excise.domain;

import java.util.List;

public class RiskFullDataVo {
	private String id;
	private String projectBase;
	private String departmentName;
	private List<String> rl;
	private String sumRiskCost;
	private String rlAll;
	private String color;
	private String valueTranslation;
	
	public String getProjectBase() {
		return projectBase;
	}
	public void setProjectBase(String projectBase) {
		this.projectBase = projectBase;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public String getSumRiskCost() {
		return sumRiskCost;
	}
	public void setSumRiskCost(String sumRiskCost) {
		this.sumRiskCost = sumRiskCost;
	}
	
	public List<String> getRl() {
		return rl;
	}
	public void setRl(List<String> rl) {
		this.rl = rl;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRlAll() {
		return rlAll;
	}
	public void setRlAll(String rlAll) {
		this.rlAll = rlAll;
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
	
	
}
