package th.co.baiwa.excise.ia.persistence.vo;

public class RiskExcelVo {
	private String riskHeaderName;
	private Long riskHrdId;
	private String conditionPage;
	private String conditionType;
	private Long conditionParentId;
	
	public String getRiskHeaderName() {
		return riskHeaderName;
	}
	public void setRiskHeaderName(String riskHeaderName) {
		this.riskHeaderName = riskHeaderName;
	}
	public Long getRiskHrdId() {
		return riskHrdId;
	}
	public void setRiskHrdId(Long riskHrdId) {
		this.riskHrdId = riskHrdId;
	}
	public String getConditionPage() {
		return conditionPage;
	}
	public void setConditionPage(String conditionPage) {
		this.conditionPage = conditionPage;
	}
	public String getConditionType() {
		return conditionType;
	}
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	public Long getConditionParentId() {
		return conditionParentId;
	}
	public void setConditionParentId(Long conditionParentId) {
		this.conditionParentId = conditionParentId;
	}
	
	
}
