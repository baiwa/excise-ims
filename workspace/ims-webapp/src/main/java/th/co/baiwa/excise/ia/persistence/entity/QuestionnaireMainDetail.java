package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;

import th.co.baiwa.excise.domain.BaseEntity;

public class QuestionnaireMainDetail extends BaseEntity {

	private BigDecimal qtnMainId;
	private String headerCode;
	private String qtnMainDetail;

	public BigDecimal getQtnMainId() {
		return qtnMainId;
	}

	public void setQtnMainId(BigDecimal qtnMainId) {
		this.qtnMainId = qtnMainId;
	}

	public String getQtnMainDetail() {
		return qtnMainDetail;
	}

	public String getHeaderCode() {
		return headerCode;
	}

	public void setHeaderCode(String headerCode) {
		this.headerCode = headerCode;
	}

	public void setQtnMainDetail(String qtnMainDetail) {
		this.qtnMainDetail = qtnMainDetail;
	}

}