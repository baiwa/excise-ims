package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;

public class PaperBasicAnalysisD3Vo {
	private String paperBaNo;
	private String recNo;
	private String goodsDescText;
	private BigDecimal taxQty;
	private BigDecimal informPrice;
	private BigDecimal goodsValueAmt;

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

	public String getGoodsDescText() {
		return goodsDescText;
	}

	public void setGoodsDescText(String goodsDescText) {
		this.goodsDescText = goodsDescText;
	}

	public BigDecimal getTaxQty() {
		return taxQty;
	}

	public void setTaxQty(BigDecimal taxQty) {
		this.taxQty = taxQty;
	}

	public BigDecimal getInformPrice() {
		return informPrice;
	}

	public void setInformPrice(BigDecimal informPrice) {
		this.informPrice = informPrice;
	}

	public BigDecimal getGoodsValueAmt() {
		return goodsValueAmt;
	}

	public void setGoodsValueAmt(BigDecimal goodsValueAmt) {
		this.goodsValueAmt = goodsValueAmt;
	}

}
