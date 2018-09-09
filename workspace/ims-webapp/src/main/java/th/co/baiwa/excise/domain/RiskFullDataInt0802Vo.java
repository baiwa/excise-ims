package th.co.baiwa.excise.domain;

import java.util.List;



public class RiskFullDataInt0802Vo {
	private String id;
	private String infName;
	private List<String> rl;
	private String sumRiskCost;
	private String valueRl;
	private String convertValue;
	private String color;
	
	
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getValueRl() {
		return valueRl;
	}

	public void setValueRl(String valueRl) {
		this.valueRl = valueRl;
	}

	public String getConvertValue() {
		return convertValue;
	}

	public void setConvertValue(String convertValue) {
		this.convertValue = convertValue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInfName() {
		return infName;
	}

	public void setInfName(String infName) {
		this.infName = infName;
	}

	public List<String> getRl() {
		return rl;
	}

	public void setRl(List<String> rl) {
		this.rl = rl;
	}

	public String getSumRiskCost() {
		return sumRiskCost;
	}

	public void setSumRiskCost(String sumRiskCost) {
		this.sumRiskCost = sumRiskCost;
	}

}
