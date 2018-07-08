package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;

import th.co.baiwa.excise.domain.BaseEntity;

public class QuestionnaireHeader extends BaseEntity {
	private BigDecimal qtnHeaderId;
	private String qtnHeaderCode;
	private String qtnHeaderName;

	public BigDecimal getQtnHeaderId() {
		return qtnHeaderId;
	}

	public void setQtnHeaderId(BigDecimal qtnHeaderId) {
		this.qtnHeaderId = qtnHeaderId;
	}

	public String getQtnHeaderCode() {
		return qtnHeaderCode;
	}

	public void setQtnHeaderCode(String qtnHeaderCode) {
		this.qtnHeaderCode = qtnHeaderCode;
	}

	public String getQtnHeaderName() {
		return qtnHeaderName;
	}

	public void setQtnHeaderName(String qtnHeaderName) {
		this.qtnHeaderName = qtnHeaderName;
	}
}
