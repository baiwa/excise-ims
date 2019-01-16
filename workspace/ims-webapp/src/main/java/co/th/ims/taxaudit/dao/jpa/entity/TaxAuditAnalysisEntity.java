package co.th.ims.taxaudit.dao.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import co.th.baiwa.buckwaframework.common.bean.BaseEntity;

@Entity
@Table(name = "TA_ANALYSIS_DATA_DETAIL")
public class TaxAuditAnalysisEntity extends BaseEntity {

	private static final long serialVersionUID = -4142601074312098023L;

	@Id
	@Column(name = "ANALYSIS_DATA_DETAIL_ID")
	private Long analysisDataDetailId;

	@Column(name = "ANALYSIS_DATA_ID")
	private Long analysisDataId;

	@Column(name = "TA_EXCISE_FILE_UPLOAD_ID")
	private Long exciseFileUploadId;

	@Column(name = "EXCISE_ID")
	private Long exciseId;

	public Long getAnalysisDataDetailId() {
		return analysisDataDetailId;
	}

	public void setAnalysisDataDetailId(Long analysisDataDetailId) {
		this.analysisDataDetailId = analysisDataDetailId;
	}

	public Long getAnalysisDataId() {
		return analysisDataId;
	}

	public void setAnalysisDataId(Long analysisDataId) {
		this.analysisDataId = analysisDataId;
	}

	public Long getExciseFileUploadId() {
		return exciseFileUploadId;
	}

	public void setExciseFileUploadId(Long exciseFileUploadId) {
		this.exciseFileUploadId = exciseFileUploadId;
	}

	public Long getExciseId() {
		return exciseId;
	}

	public void setExciseId(Long exciseId) {
		this.exciseId = exciseId;
	}

}
