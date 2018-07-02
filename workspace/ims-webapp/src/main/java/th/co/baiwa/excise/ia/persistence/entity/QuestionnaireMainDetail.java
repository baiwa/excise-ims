package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

public class QuestionnaireMainDetail {
	
	private BigDecimal qtnMainId;
	private BigDecimal headerId;
	private String qtnMainDetail;
	private String createdBy;
	private Date createdDatetime;
	private String updateBy;
	private Date updateDatetime;
	
	public BigDecimal getQtnMainId() {
		return qtnMainId;
	}
	public void setQtnMainId(BigDecimal qtnMainId) {
		this.qtnMainId = qtnMainId;
	}
	public BigDecimal getHeaderId() {
		return headerId;
	}
	public void setHeaderId(BigDecimal headerId) {
		this.headerId = headerId;
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