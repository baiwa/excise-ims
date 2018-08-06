package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class ExciseFileUpload extends BaseEntity {
	private static final long serialVersionUID = -10115375538652336L;
	
	private BigDecimal taExciseFileUploadId;
	private String exciseId;
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
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
	
	
}
