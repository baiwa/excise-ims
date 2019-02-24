package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;

import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;

public class TaxDratfVo extends TaWsReg4000 {

	private static final long serialVersionUID = 6016709334408153017L;

	private BigDecimal taxMonthNo;
	private BigDecimal taxAmtChnPnt;

	public BigDecimal getTaxMonthNo() {
		return taxMonthNo;
	}

	public void setTaxMonthNo(BigDecimal taxMonthNo) {
		this.taxMonthNo = taxMonthNo;
	}

	public BigDecimal getTaxAmtChnPnt() {
		return taxAmtChnPnt;
	}

	public void setTaxAmtChnPnt(BigDecimal taxAmtChnPnt) {
		this.taxAmtChnPnt = taxAmtChnPnt;
	}

}
