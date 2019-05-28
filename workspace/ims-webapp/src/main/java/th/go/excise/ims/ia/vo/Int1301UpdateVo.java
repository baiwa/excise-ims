package th.go.excise.ims.ia.vo;

import java.util.List;

public class Int1301UpdateVo {
	private List<IaAuditPmassessHVo> header;
	private List<IaAuditPmassessDVo> detail;

	public List<IaAuditPmassessHVo> getHeader() {
		return header;
	}

	public void setHeader(List<IaAuditPmassessHVo> header) {
		this.header = header;
	}

	public List<IaAuditPmassessDVo> getDetail() {
		return detail;
	}

	public void setDetail(List<IaAuditPmassessDVo> detail) {
		this.detail = detail;
	}

}
