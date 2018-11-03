package th.co.baiwa.excise.epa.persistence.vo;

import java.math.BigDecimal;

public class InvhdrFormVo {

	private BigDecimal hdrId;
	private BigDecimal dtlId;

	private InvhdrVo leftFrom;
	private InvhdrVo rightForm;
	private Epa011Vo hdrVo;
	private Epa011DtlVo dtlVo;

	public BigDecimal getHdrId() {
		return hdrId;
	}

	public void setHdrId(BigDecimal hdrId) {
		this.hdrId = hdrId;
	}

	public BigDecimal getDtlId() {
		return dtlId;
	}

	public void setDtlId(BigDecimal dtlId) {
		this.dtlId = dtlId;
	}

	public InvhdrVo getLeftFrom() {
		return leftFrom;
	}

	public void setLeftFrom(InvhdrVo leftFrom) {
		this.leftFrom = leftFrom;
	}

	public InvhdrVo getRightForm() {
		return rightForm;
	}

	public void setRightForm(InvhdrVo rightForm) {
		this.rightForm = rightForm;
	}

	public Epa011Vo getHdrVo() {
		return hdrVo;
	}

	public void setHdrVo(Epa011Vo hdrVo) {
		this.hdrVo = hdrVo;
	}

	public Epa011DtlVo getDtlVo() {
		return dtlVo;
	}

	public void setDtlVo(Epa011DtlVo dtlVo) {
		this.dtlVo = dtlVo;
	}

}
