package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

public class QtnFinalRepHeader {
	
	
	private BigDecimal qtnFinalRepHdrId;
	private String qtnFinalRepHdrName;
	private String createdBy;
	private Date createdDatetime;
	private String updateBy;
	private Date updateDatetime;
	
	
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
