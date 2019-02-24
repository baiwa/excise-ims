package th.go.excise.ims.ta.vo;

import java.util.List;

import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainDtl;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainHdr;

public class WorksheetConditionHdrDtlVo {

	private TaWorksheetCondMainHdr header;
	private List<TaWorksheetCondMainDtl> detail;

	public TaWorksheetCondMainHdr getHeader() {
		return header;
	}

	public void setHeader(TaWorksheetCondMainHdr header) {
		this.header = header;
	}

	public List<TaWorksheetCondMainDtl> getDetail() {
		return detail;
	}

	public void setDetail(List<TaWorksheetCondMainDtl> detail) {
		this.detail = detail;
	}
}
