package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import th.co.baiwa.excise.domain.BaseEntity;

public class ExciseFileUpload extends BaseEntity {
	private BigDecimal taExciseFileUploadId;
	private String exciseId;
	private Date createDate;
	private String uploadPath;

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

}
