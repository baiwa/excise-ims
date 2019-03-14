package th.go.excise.ims.ta.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class AnalysisTaxQtyVo extends DataTableRequest {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3817127640195693832L;
	private String goodsDesc;
	private String taxQty;
	private String monthStatementTaxQty;
	private String diffTaxQty;
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public String getTaxQty() {
		return taxQty;
	}
	public void setTaxQty(String taxQty) {
		this.taxQty = taxQty;
	}
	public String getMonthStatementTaxQty() {
		return monthStatementTaxQty;
	}
	public void setMonthStatementTaxQty(String monthStatementTaxQty) {
		this.monthStatementTaxQty = monthStatementTaxQty;
	}
	public String getDiffTaxQty() {
		return diffTaxQty;
	}
	public void setDiffTaxQty(String diffTaxQty) {
		this.diffTaxQty = diffTaxQty;
	}

}
