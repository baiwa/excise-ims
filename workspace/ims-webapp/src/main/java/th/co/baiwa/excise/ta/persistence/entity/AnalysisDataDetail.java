package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;

import th.co.baiwa.excise.domain.BaseEntity;

public class AnalysisDataDetail extends BaseEntity {
	private BigDecimal analysisDataDetailId;
	private BigDecimal analysisDataId;
	private String analysisName;
	private String analysisOrder;
	private String analysisList1;
	private String analysisList2;

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

	public String getAnalysisName() {
		return analysisName;
	}

	public void setAnalysisName(String analysisName) {
		this.analysisName = analysisName;
	}

	public String getAnalysisOrder() {
		return analysisOrder;
	}

	public void setAnalysisOrder(String analysisOrder) {
		this.analysisOrder = analysisOrder;
	}

	public String getAnalysisList1() {
		return analysisList1;
	}

	public void setAnalysisList1(String analysisList1) {
		this.analysisList1 = analysisList1;
	}

	public String getAnalysisList2() {
		return analysisList2;
	}

	public void setAnalysisList2(String analysisList2) {
		this.analysisList2 = analysisList2;
	}
}
