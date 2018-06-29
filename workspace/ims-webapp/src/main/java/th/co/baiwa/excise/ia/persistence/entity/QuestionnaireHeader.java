package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

public class QuestionnaireHeader {
	private BigDecimal qtnHeaderId;
	private String qtnHeaderCode;
	private String qtnHeaderName;
	private String createdBy;
	private Date createdDatetime;
	private String updateBy;
	private Date updateDatetime;
	
	
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
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDatetime() {
		return createdDatetime;
	}
	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
}
