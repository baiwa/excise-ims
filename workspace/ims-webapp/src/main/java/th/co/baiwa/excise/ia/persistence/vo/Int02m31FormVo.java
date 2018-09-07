package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class Int02m31FormVo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8116697003409301705L;
	
	private String headerCode;
	private String qtnMainDetail;
	private String qtnMinorDetail;
	private String flag;

	private Long qtnManiId;
	private List<T> detail; 
	
	public String getHeaderCode() {
		return headerCode;
	}
	public void setHeaderCode(String headerCode) {
		this.headerCode = headerCode;
	}
	public String getQtnMainDetail() {
		return qtnMainDetail;
	}
	public void setQtnMainDetail(String qtnMainDetail) {
		this.qtnMainDetail = qtnMainDetail;
	}
	public String getQtnMinorDetail() {
		return qtnMinorDetail;
	}
	public void setQtnMinorDetail(String qtnMinorDetail) {
		this.qtnMinorDetail = qtnMinorDetail;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getQtnManiId() {
		return qtnManiId;
	}
	public void setQtnManiId(Long qtnManiId) {
		this.qtnManiId = qtnManiId;
	}
	public List<T> getDetail() {
		return detail;
	}
	public void setDetail(List<T> detail) {
		this.detail = detail;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
