package th.co.baiwa.excise.entity.ta;

import java.math.BigDecimal;
import java.util.Date;

public class ExciseFileUpload {
	private BigDecimal taExciseFileUploadId;
	private String exciseId;
	private Date createDate;
	private String uploadPath;
	private String createdBy;
	private Date  createdDatetime;
	private String updateBy;
	private Date updateDatetime;
	public BigDecimal getTaExciseFileUploadId() {
		return taExciseFileUploadId;
	}
	public void setTaExciseFileUploadId(BigDecimal taExciseFileUploadId) {
		this.taExciseFileUploadId = taExciseFileUploadId;
	}
	public String getExciseId() {
		return exciseId;
	}
	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
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
