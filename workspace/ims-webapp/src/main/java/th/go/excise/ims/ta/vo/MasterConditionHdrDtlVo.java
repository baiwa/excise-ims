package th.go.excise.ims.ta.vo;

import java.util.List;

import th.go.excise.ims.ta.persistence.entity.TaMasCondDtlTax;
import th.go.excise.ims.ta.persistence.entity.TaMasCondHdr;

public class MasterConditionHdrDtlVo {
	
	TaMasCondHdr header;
	List<TaMasCondDtlTax> detail;
	
	public TaMasCondHdr getHeader() {
		return header;
	}
	public void setHeader(TaMasCondHdr header) {
		this.header = header;
	}
	public List<TaMasCondDtlTax> getDetail() {
		return detail;
	}
	public void setDetail(List<TaMasCondDtlTax> detail) {
		this.detail = detail;
	}
	
}
