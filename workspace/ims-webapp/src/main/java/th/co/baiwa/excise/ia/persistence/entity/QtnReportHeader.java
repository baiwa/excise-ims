package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

public class QtnReportHeader {

	private BigDecimal qtnReportHdrId;
	private String qtnReportHdrName;
	private String creator;
	private String createdBy;
	private Date createdDatetime;
	private String updateBy;
	private Date updateDatetime;
	
	
	public BigDecimal getQtnReportHdrId() {
		return qtnReportHdrId;
	}
	public void setQtnReportHdrId(BigDecimal qtnReportHdrId) {
		this.qtnReportHdrId = qtnReportHdrId;
	}
	public String getQtnReportHdrName() {
		return qtnReportHdrName;
	}
	public void setQtnReportHdrName(String qtnReportHdrName) {
		this.qtnReportHdrName = qtnReportHdrName;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
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
