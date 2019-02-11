package co.th.ims.taxaudit.vo;

import java.util.List;

public class TaxAuditCreateWsHdrDtl {
	
	TaxAuditCreateWorkSheetFormVo Header;
	List<TaxAuditConditionFormVo> Detail;
	
	public TaxAuditCreateWorkSheetFormVo getHeader() {
		return Header;
	}
	public void setHeader(TaxAuditCreateWorkSheetFormVo header) {
		Header = header;
	}
	public List<TaxAuditConditionFormVo> getDetail() {
		return Detail;
	}
	public void setDetail(List<TaxAuditConditionFormVo> detail) {
		Detail = detail;
	}

	
}
