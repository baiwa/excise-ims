package th.co.baiwa.excise.ia.persistence.vo;

import th.co.baiwa.excise.ia.persistence.entity.qtn.QtnReportMain;

public class QtnReportMainVo extends QtnReportMain {

	private String point;
	private Long mainId;

	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}
	
}
