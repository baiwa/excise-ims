package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.bean.BaseVo;

public class TaxAuditAnalysisVo extends BaseVo {
	private BigDecimal analysisDataDetailId;
	private BigDecimal analysisDataId;
	private BigDecimal exciseFileUploadId;
	private BigDecimal exciseId;

	public BigDecimal getAnalysisDataDetailId() {
		return analysisDataDetailId;
	}

	public void setAnalysisDataDetailId(BigDecimal analysisDataDetailId) {
		this.analysisDataDetailId = analysisDataDetailId;
	}

	public BigDecimal getAnalysisDataId() {
		return analysisDataId;
	}

	public void setAnalysisDataId(BigDecimal analysisDataId) {
		this.analysisDataId = analysisDataId;
	}

	public BigDecimal getExciseFileUploadId() {
		return exciseFileUploadId;
	}

	public void setExciseFileUploadId(BigDecimal exciseFileUploadId) {
		this.exciseFileUploadId = exciseFileUploadId;
	}

	public BigDecimal getExciseId() {
		return exciseId;
	}

	public void setExciseId(BigDecimal exciseId) {
		this.exciseId = exciseId;
	}
}
