package th.co.baiwa.excise.oa.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name="OA_ANALYSIS_DATA_DTL")
public class OaAnalysisDataDtl extends BaseEntity {
	
	private static final long serialVersionUID = -6561119925615468643L;

	@Id	
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_ANALYSIS_DATA_DTL_GEN" )
	@SequenceGenerator(name = "OA_ANALYSIS_DATA_DTL_GEN", sequenceName = "OA_ANALYSIS_DATA_DTL_SEQ", allocationSize = 1)
	@Column(name="ANALYSIS_DATA_DTL_ID")
	private Long analysisDataDtlId;
	
	@Column(name="ANALYSIS_DATA_HDR_ID")
	private Long analysisDataHdrId;
	
	@Column(name="ANALYSIS_NAME")
	private String analysisName;
	
	@Column(name="ANALYSIS_ORDER")
	private String analysisOrder;
	
	@Column(name="ANALYSIS_LIST_1")
	private String analysisList1;
	
	@Column(name="ANALYSIS_LIST_2")
	private String analysisList2;	


	public Long getAnalysisDataDtlId() {
		return analysisDataDtlId;
	}

	public void setAnalysisDataDtlId(Long analysisDataDtlId) {
		this.analysisDataDtlId = analysisDataDtlId;
	}

	public Long getAnalysisDataHdrId() {
		return analysisDataHdrId;
	}

	public void setAnalysisDataHdrId(Long analysisDataHdrId) {
		this.analysisDataHdrId = analysisDataHdrId;
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
	
		

}