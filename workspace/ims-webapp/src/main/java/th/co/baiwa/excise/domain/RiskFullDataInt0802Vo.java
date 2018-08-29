package th.co.baiwa.excise.domain;

import java.util.List;

public class RiskFullDataInt0802Vo {
	private String id;
	private String infName;
	private List<String> rl;
	private String sumRiskCost;

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
