package th.go.excise.ims.ia.vo;

import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;

public class Int02Vo extends IaQuestionnaireHdr {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7493503564882947512L;
	private String createdDateStr;
	private String updatedDateStr;
	private String startDateStr;
	private String endDateStr;
	private String statusStr;
	
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
	public String getStartDateStr() {
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	
}
