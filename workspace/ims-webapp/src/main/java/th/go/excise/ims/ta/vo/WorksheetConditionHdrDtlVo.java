package th.go.excise.ims.ta.vo;

import java.util.List;

import th.go.excise.ims.ta.persistence.entity.TaWsCondDtlTax;
import th.go.excise.ims.ta.persistence.entity.TaWsCondHdr;

public class WorksheetConditionHdrDtlVo {

	TaWsCondHdr header;
	List<TaWsCondDtlTax> detail;
	
	public TaWsCondHdr getHeader() {
		return header;
	}
	public void setHeader(TaWsCondHdr header) {
		this.header = header;
	}
	public List<TaWsCondDtlTax> getDetail() {
		return detail;
	}
	public void setDetail(List<TaWsCondDtlTax> detail) {
		this.detail = detail;
	}
}
