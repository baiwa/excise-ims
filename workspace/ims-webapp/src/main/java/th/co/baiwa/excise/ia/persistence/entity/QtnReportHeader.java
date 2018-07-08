package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;

import th.co.baiwa.excise.domain.BaseEntity;

public class QtnReportHeader extends BaseEntity {

	private BigDecimal qtnReportHdrId;
	private String qtnReportHdrName;
	private String creator;

	public BigDecimal getQtnReportHdrId() {
		return qtnReportHdrId;
	}

	public void setQtnReportHdrId(BigDecimal qtnReportHdrId) {
		this.qtnReportHdrId = qtnReportHdrId;
	}

	public String getQtnReportHdrName() {
		return qtnReportHdrName;
	}

	public void setQtnReportHdrName(String qtnReportHdrName) {
		this.qtnReportHdrName = qtnReportHdrName;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
}
