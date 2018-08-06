package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class QuestionnaireDetail extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6161252031624870357L;
	private BigDecimal qtnDetailId;
	private BigDecimal masterId;
	private String headerCode;
	private String qtnMainDetail;

	public BigDecimal getQtnDetailId() {
		return qtnDetailId;
	}

	public void setQtnDetailId(BigDecimal qtnDetailId) {
		this.qtnDetailId = qtnDetailId;
	}

	public BigDecimal getMasterId() {
		return masterId;
	}

	public void setMasterId(BigDecimal masterId) {
		this.masterId = masterId;
	}

	public String getHeaderCode() {
		return headerCode;
	}

	public void setHeaderCode(String headerCode) {
		this.headerCode = headerCode;
	}

	public String getQtnMainDetail() {
		return qtnMainDetail;
	}

	public void setQtnMainDetail(String qtnMainDetail) {
		this.qtnMainDetail = qtnMainDetail;
	}
}