package th.go.excise.ims.mockup.persistence.entity.ta;

import java.util.Date;

public class AnalysisDataDetail {
	private Integer analysisDataDetailId;
	private Integer analysisDataId;
	private String analysisName;
	private String analysisOrder;
	private String analysisList1;
	private String analysisList2;
	private String createdBy;
	private String updatedBy;
	private Date createdDatetime;
	private Date updatedDatetime;
	
	public Integer getAnalysisDataDetailId() {
		return analysisDataDetailId;
	}
	public void setAnalysisDataDetailId(Integer analysisDataDetailId) {
		this.analysisDataDetailId = analysisDataDetailId;
	}
	public Integer getAnalysisDataId() {
		return analysisDataId;
	}
	public void setAnalysisDataId(Integer analysisDataId) {
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
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getCreatedDatetime() {
		return createdDatetime;
	}
	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	public Date getUpdatedDatetime() {
		return updatedDatetime;
	}
	public void setUpdatedDatetime(Date updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}	
}
