package th.co.baiwa.excise.domain;

public class QtnHdrConditionVo {
	
	private Long qtnMasterId;
	private String qtnName;
	private Long qtnHrdId;
	private Integer riskPoint;
	private String rl;
	
	
	public String getQtnName() {
		return qtnName;
	}
	public void setQtnName(String qtnName) {
		this.qtnName = qtnName;
	}
	
	public String getRl() {
		return rl;
	}
	public void setRl(String rl) {
		this.rl = rl;
	}
	public Integer getRiskPoint() {
		return riskPoint;
	}
	public void setRiskPoint(Integer riskPoint) {
		this.riskPoint = riskPoint;
	}
	public Long getQtnMasterId() {
		return qtnMasterId;
	}
	public void setQtnMasterId(Long qtnMasterId) {
		this.qtnMasterId = qtnMasterId;
	}
	public Long getQtnHrdId() {
		return qtnHrdId;
	}
	public void setQtnHrdId(Long qtnHrdId) {
		this.qtnHrdId = qtnHrdId;
	}
	
	
	
}
