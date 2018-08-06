package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class QtnFinalRepHeader extends BaseEntity {

	
	private static final long serialVersionUID = 6237354256881472464L;
	
	
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
