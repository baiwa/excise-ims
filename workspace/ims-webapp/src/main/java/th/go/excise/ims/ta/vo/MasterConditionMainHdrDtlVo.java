package th.go.excise.ims.ta.vo;

import java.util.List;

import th.go.excise.ims.ta.persistence.entity.TaMasCondMainDtl;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainHdr;

public class MasterConditionMainHdrDtlVo {
	
	TaMasCondMainHdr header;
	List<TaMasCondMainDtl> detail;
	
	public TaMasCondMainHdr getHeader() {
		return header;
	}
	public void setHeader(TaMasCondMainHdr header) {
		this.header = header;
	}
	public List<TaMasCondMainDtl> getDetail() {
		return detail;
	}
	public void setDetail(List<TaMasCondMainDtl> detail) {
		this.detail = detail;
	}
	
}
