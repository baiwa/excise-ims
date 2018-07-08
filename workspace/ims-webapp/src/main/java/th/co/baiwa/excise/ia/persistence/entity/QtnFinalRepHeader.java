package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;

import th.co.baiwa.excise.domain.BaseEntity;

public class QtnFinalRepHeader extends BaseEntity {

	private BigDecimal qtnFinalRepHdrId;
	private String qtnFinalRepHdrName;

	public BigDecimal getQtnFinalRepHdrId() {
		return qtnFinalRepHdrId;
	}

	public void setQtnFinalRepHdrId(BigDecimal qtnFinalRepHdrId) {
		this.qtnFinalRepHdrId = qtnFinalRepHdrId;
	}

	public String getQtnFinalRepHdrName() {
		return qtnFinalRepHdrName;
	}

	public void setQtnFinalRepHdrName(String qtnFinalRepHdrName) {
		this.qtnFinalRepHdrName = qtnFinalRepHdrName;
	}
}
