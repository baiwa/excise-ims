package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class PaperBasicAnalysisD1Vo  extends DataTableRequest{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3796016387872443037L;
	private String paperBaNo;
	private String recNo;
	private String goodsDesc;
	private BigDecimal taxQty;
	private BigDecimal monthStatementTaxQty;
	private BigDecimal diffTaxQty;
	public String getPaperBaNo() {
		return paperBaNo;
	}
	public void setPaperBaNo(String paperBaNo) {
		this.paperBaNo = paperBaNo;
	}
	public String getRecNo() {
		return recNo;
	}
	public void setRecNo(String recNo) {
		this.recNo = recNo;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public BigDecimal getTaxQty() {
		return taxQty;
	}
	public void setTaxQty(BigDecimal taxQty) {
		this.taxQty = taxQty;
	}
	public BigDecimal getMonthStatementTaxQty() {
		return monthStatementTaxQty;
	}

	public void setMonthStatementTaxQty(BigDecimal monthStatementTaxQty) {
		this.monthStatementTaxQty = monthStatementTaxQty;
	}
	public BigDecimal getDiffTaxQty() {
		return diffTaxQty;
	}
	public void setDiffTaxQty(BigDecimal diffTaxQty) {
		this.diffTaxQty = diffTaxQty;
	}

}
