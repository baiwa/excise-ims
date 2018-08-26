package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;
import th.co.baiwa.excise.ia.persistence.entity.QtnReportDetail;

public class Int023Vo<T> extends BaseEntity {
	
	private static final long serialVersionUID = -1729645795213148437L;

	private Long qtnReportHdrId;
	
	private Long qtnReportManId;
	
	private String qtnMainDetail;
	
	private List<T> detail; 

	public Long getQtnReportHdrId() {
		return qtnReportHdrId;
	}

	public void setQtnReportHdrId(Long qtnReportHdrId) {
		this.qtnReportHdrId = qtnReportHdrId;
	}

	public Long getQtnReportManId() {
		return qtnReportManId;
	}

	public void setQtnReportManId(Long qtnReportManId) {
		this.qtnReportManId = qtnReportManId;
	}

	public List<T> getDetail() {
		return detail;
	}

	public void setDetail(List<T> detail) {
		this.detail = detail;
	}

	public String getQtnMainDetail() {
		return qtnMainDetail;
	}

	public void setQtnMainDetail(String qtnMainDetail) {
		this.qtnMainDetail = qtnMainDetail;
	}

}
