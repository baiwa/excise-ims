package th.co.baiwa.excise.ia.persistence.vo;

public class Int02m4VoDetail {
    private String title;
    private Integer approve;
    private Integer reject;
    private String risk;
    private Integer score;
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getApprove() {
		return approve;
	}
	public void setApprove(Integer approve) {
		this.approve = approve;
	}
	public Integer getReject() {
		return reject;
	}
	public void setReject(Integer reject) {
		this.reject = reject;
	}
	public String getRisk() {
		return risk;
	}
	public void setRisk(String risk) {
		this.risk = risk;
	}
}
