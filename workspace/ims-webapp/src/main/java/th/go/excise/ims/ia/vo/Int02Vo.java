package th.go.excise.ims.ia.vo;

import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;

public class Int02Vo extends IaQuestionnaireHdr {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7493503564882947512L;
	private String createdDateStr;
	private String updatedDateStr;
	
	public String getCreatedDateStr() {
		return createdDateStr;
	}
	public void setCreatedDateStr(String createdDateStr) {
		this.createdDateStr = createdDateStr;
	}
	public String getUpdatedDateStr() {
		return updatedDateStr;
	}
	public void setUpdatedDateStr(String updatedDateStr) {
		this.updatedDateStr = updatedDateStr;
	}
	
	
	
	
}
