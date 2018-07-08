package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;

import th.co.baiwa.excise.domain.BaseEntity;

public class QuestionnaireMinorDetail extends BaseEntity {
	private BigDecimal qtnMinorId;
	private String headerCode;
	private BigDecimal mainId;
	private String qtnMinorDetail;

	public BigDecimal getQtnMinorId() {
		return qtnMinorId;
	}

	public void setQtnMinorId(BigDecimal qtnMinorId) {
		this.qtnMinorId = qtnMinorId;
	}

	public String getHeaderCode() {
		return headerCode;
	}

	public void setHeaderCode(String headerCode) {
		this.headerCode = headerCode;
	}

	public BigDecimal getMainId() {
		return mainId;
	}

	public void setMainId(BigDecimal mainId) {
		this.mainId = mainId;
	}

	public String getQtnMinorDetail() {
		return qtnMinorDetail;
	}

	public void setQtnMinorDetail(String qtnMinorDetail) {
		this.qtnMinorDetail = qtnMinorDetail;
	}

}
