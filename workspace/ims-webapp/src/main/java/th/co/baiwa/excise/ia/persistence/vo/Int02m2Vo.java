package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

public class Int02m2Vo {
	private String title;
	private String content;
	private Boolean conclusion;
	private List<Int02m2VoDetail> detail;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Boolean getConclusion() {
		return conclusion;
	}
	public void setConclusion(Boolean conclusion) {
		this.conclusion = conclusion;
	}
	public List<Int02m2VoDetail> getDetail() {
		return detail;
	}
	public void setDetail(List<Int02m2VoDetail> detail) {
		this.detail = detail;
	}
}
