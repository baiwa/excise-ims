package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;

public class PaperBasicAnalysisD4Vo {
	private String paperBaNo;
	private String recNo;
	private String goodsDesc;
	private BigDecimal taxRateByPrice;
	private BigDecimal taxRateByQty;
	private BigDecimal anaTaxRateByPrice;
	private BigDecimal anaTaxRateByQty;
	private BigDecimal diffTaxRateByPrice;
	private BigDecimal diffTaxRateByQty;

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

	public BigDecimal getTaxRateByPrice() {
		return taxRateByPrice;
	}

	public void setTaxRateByPrice(BigDecimal taxRateByPrice) {
		this.taxRateByPrice = taxRateByPrice;
	}

	public BigDecimal getTaxRateByQty() {
		return taxRateByQty;
	}

	public void setTaxRateByQty(BigDecimal taxRateByQty) {
		this.taxRateByQty = taxRateByQty;
	}

	public BigDecimal getAnaTaxRateByPrice() {
		return anaTaxRateByPrice;
	}

	public void setAnaTaxRateByPrice(BigDecimal anaTaxRateByPrice) {
		this.anaTaxRateByPrice = anaTaxRateByPrice;
	}

	public BigDecimal getAnaTaxRateByQty() {
		return anaTaxRateByQty;
	}

	public void setAnaTaxRateByQty(BigDecimal anaTaxRateByQty) {
		this.anaTaxRateByQty = anaTaxRateByQty;
	}

	public BigDecimal getDiffTaxRateByPrice() {
		return diffTaxRateByPrice;
	}

	public void setDiffTaxRateByPrice(BigDecimal diffTaxRateByPrice) {
		this.diffTaxRateByPrice = diffTaxRateByPrice;
	}

	public BigDecimal getDiffTaxRateByQty() {
		return diffTaxRateByQty;
	}

	public void setDiffTaxRateByQty(BigDecimal diffTaxRateByQty) {
		this.diffTaxRateByQty = diffTaxRateByQty;
	}

}
