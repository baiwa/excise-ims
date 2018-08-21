package th.co.baiwa.excise.ia.persistence.vo;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class Int023FormVo extends BaseEntity {
	
	private String qtnReportHdrId;
	private String qtnReportManId;
	private String qtnReportDtlId;
	private String qtnMainDetail;
	private String qtnFor;
	
	public String getQtnReportHdrId() {
		return qtnReportHdrId;
	}
	public void setQtnReportHdrId(String qtnReportHdrId) {
		this.qtnReportHdrId = qtnReportHdrId;
	}
	public String getQtnReportManId() {
		return qtnReportManId;
	}
	public void setQtnReportManId(String qtnReportManId) {
		this.qtnReportManId = qtnReportManId;
	}
	public String getQtnReportDtlId() {
		return qtnReportDtlId;
	}
	public void setQtnReportDtlId(String qtnReportDtlId) {
		this.qtnReportDtlId = qtnReportDtlId;
	}
	public String getQtnMainDetail() {
		return qtnMainDetail;
	}
	public void setQtnMainDetail(String qtnMainDetail) {
		this.qtnMainDetail = qtnMainDetail;
	}
	public String getQtnFor() {
		return qtnFor;
	}
	public void setQtnFor(String qtnFor) {
		this.qtnFor = qtnFor;
	}
	
}
