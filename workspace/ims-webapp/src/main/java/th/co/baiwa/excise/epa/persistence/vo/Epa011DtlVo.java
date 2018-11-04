package th.co.baiwa.excise.epa.persistence.vo;

import java.math.BigDecimal;

public class Epa011DtlVo {
	private BigDecimal id;
	private BigDecimal hdrId;
	private String brandName;
	private String productName;
	private String modelName;
	private BigDecimal goodsSize;
	private BigDecimal goodsNum;
	private BigDecimal retailPrice;
	private BigDecimal taxvalUnit;
	private BigDecimal taxqtyUnit;
	private BigDecimal taxAmount;
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getHdrId() {
		return hdrId;
	}
	public void setHdrId(BigDecimal hdrId) {
		this.hdrId = hdrId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public BigDecimal getGoodsSize() {
		return goodsSize;
	}
	public void setGoodsSize(BigDecimal goodsSize) {
		this.goodsSize = goodsSize;
	}
	public BigDecimal getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(BigDecimal goodsNum) {
		this.goodsNum = goodsNum;
	}
	public BigDecimal getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}
	public BigDecimal getTaxvalUnit() {
		return taxvalUnit;
	}
	public void setTaxvalUnit(BigDecimal taxvalUnit) {
		this.taxvalUnit = taxvalUnit;
	}
	public BigDecimal getTaxqtyUnit() {
		return taxqtyUnit;
	}
	public void setTaxqtyUnit(BigDecimal taxqtyUnit) {
		this.taxqtyUnit = taxqtyUnit;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

}
