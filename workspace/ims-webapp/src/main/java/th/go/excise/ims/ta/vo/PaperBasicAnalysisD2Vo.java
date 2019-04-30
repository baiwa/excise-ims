package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;

public class PaperBasicAnalysisD2Vo {
	private String paperBaNo;
	private String recNo;
	private String goodsDesc;
	private BigDecimal taxInformPrice;
	private BigDecimal informPrice;
	private BigDecimal diffTaxInformPrice;

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

	public BigDecimal getTaxInformPrice() {
		return taxInformPrice;
	}

	public void setTaxInformPrice(BigDecimal taxInformPrice) {
		this.taxInformPrice = taxInformPrice;
	}

	public BigDecimal getInformPrice() {
		return informPrice;
	}

	public void setInformPrice(BigDecimal informPrice) {
		this.informPrice = informPrice;
	}

	public BigDecimal getDiffTaxInformPrice() {
		return diffTaxInformPrice;
	}

	public void setDiffTaxInformPrice(BigDecimal diffTaxInformPrice) {
		this.diffTaxInformPrice = diffTaxInformPrice;
	}

}
