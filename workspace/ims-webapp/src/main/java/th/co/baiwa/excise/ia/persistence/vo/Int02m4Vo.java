package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

public class Int02m4Vo {
    private String title;
    private Integer pass;
    private Integer fail;
    private String risk;
    private List<Int02m4VoDetail> detail;
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
	public Integer getPass() {
		return pass;
	}
	public void setPass(Integer pass) {
		this.pass = pass;
	}
	public Integer getFail() {
		return fail;
	}
	public void setFail(Integer fail) {
		this.fail = fail;
	}
	public String getRisk() {
		return risk;
	}
	public void setRisk(String risk) {
		this.risk = risk;
	}
	public List<Int02m4VoDetail> getDetail() {
		return detail;
	}
	public void setDetail(List<Int02m4VoDetail> detail) {
		this.detail = detail;
	}
}
