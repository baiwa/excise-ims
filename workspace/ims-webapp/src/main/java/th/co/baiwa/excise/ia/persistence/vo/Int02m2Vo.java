package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

public class Int02m2Vo {
	private Long id;
	private Long headerId;
	private String title;
	private String content;
	private String conclusion;
	private String finished;
	private List<Int02m2VoDetail> detail;

	public String getFinished() {
		return finished;
	}
	public void setFinished(String finished) {
		this.finished = finished;
	}
	public Long getHeaderId() {
		return headerId;
	}
	public void setHeaderId(Long headerId) {
		this.headerId = headerId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getConclusion() {
		return conclusion;
	}
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	public List<Int02m2VoDetail> getDetail() {
		return detail;
	}
	public void setDetail(List<Int02m2VoDetail> detail) {
		this.detail = detail;
	}
}