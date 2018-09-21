package th.co.baiwa.excise.ia.persistence.vo;

import th.co.baiwa.excise.ia.persistence.entity.qtn.QtnReportDetail;

public class QtnReportDetailVo extends QtnReportDetail {
	
	private String point;
	private Long detailId;

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}
	
}
