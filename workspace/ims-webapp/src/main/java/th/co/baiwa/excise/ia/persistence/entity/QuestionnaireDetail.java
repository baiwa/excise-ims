package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import th.co.baiwa.excise.domain.BaseEntity;

public class QuestionnaireDetail extends BaseEntity {
	private BigDecimal qtnDetailId;
	private BigDecimal masterId;
	private String headerCode;
	private String qtnMainDetail;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String isDeleted;
	private BigDecimal version;

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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public BigDecimal getVersion() {
		return version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}
}