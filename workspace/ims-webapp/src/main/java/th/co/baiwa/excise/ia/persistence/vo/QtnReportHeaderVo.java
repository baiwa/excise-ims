package th.co.baiwa.excise.ia.persistence.vo;

import th.co.baiwa.excise.ia.persistence.entity.QtnReportHeader;

public class QtnReportHeaderVo extends QtnReportHeader {

	private String conclusion;
	private Long headerId;
	private String qtnFinished;

	public String getQtnFinished() {
		return qtnFinished;
	}

	public void setQtnFinished(String qtnFinished) {
		this.qtnFinished = qtnFinished;
	}

	public Long getHeaderId() {
		return headerId;
	}

	public void setHeaderId(Long headerId) {
		this.headerId = headerId;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	
}
