package th.co.baiwa.excise.oa.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


@Entity
@Table(name="OA_EXCISE_FILE_UPLOAD")
public class OaExciseFileUpload extends BaseEntity {
	
	private static final long serialVersionUID = -3404885391487706919L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_EXCISE_FILE_UPLOAD_GEN")
	@SequenceGenerator(name = "OA_EXCISE_FILE_UPLOAD_GEN", sequenceName = "OA_EXCISE_FILE_UPLOAD_SEQ", allocationSize = 1)
	private Long exciseFileUploadId;

	@Column(name="EXCISE_ID")
	private String exciseId;

	@Column(name="CREATE_DATE")
	private Date createDate;
	
	@Column(name="UPLOAD_PATH")
	private String uploadPath;

	public Long getExciseFileUploadId() {
		return exciseFileUploadId;
	}

	public void setExciseFileUploadId(Long exciseFileUploadId) {
		this.exciseFileUploadId = exciseFileUploadId;
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