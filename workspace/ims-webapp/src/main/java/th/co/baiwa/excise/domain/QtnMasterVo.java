package th.co.baiwa.excise.domain;

import java.util.List;

public class QtnMasterVo {
	private String qtnName;
	private List<QtnHdrConditionVo> qtnHdrConditionVoList;
	private String riskPointMaster;
	private String rl;
	private String color;
	private String valueTranslation;
	
	
	public String getQtnName() {
		return qtnName;
	}
	public void setQtnName(String qtnName) {
		this.qtnName = qtnName;
	}
	public List<QtnHdrConditionVo> getQtnHdrConditionVoList() {
		return qtnHdrConditionVoList;
	}
	public void setQtnHdrConditionVoList(List<QtnHdrConditionVo> qtnHdrConditionVoList) {
		this.qtnHdrConditionVoList = qtnHdrConditionVoList;
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
	public String getRiskPointMaster() {
		return riskPointMaster;
	}
	public void setRiskPointMaster(String riskPointMaster) {
		this.riskPointMaster = riskPointMaster;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
