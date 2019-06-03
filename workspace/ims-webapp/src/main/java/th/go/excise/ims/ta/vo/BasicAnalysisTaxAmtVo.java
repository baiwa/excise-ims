package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class BasicAnalysisTaxAmtVo extends DataTableRequest {

	private static final long serialVersionUID = -6837417994944245251L;

	private String goodsDesc;
	private BigDecimal taxByValAmt;
	private BigDecimal taxByQtyAmt;
	private BigDecimal sumTaxAmt;
	private BigDecimal anaTaxByValAmt;
	private BigDecimal anaTaxByQtyAmt;
	private BigDecimal sumAnaTaxAmt;
	private BigDecimal diffTaxByValAmt;
	private BigDecimal diffTaxByQtyAmt;
	private BigDecimal diffSumTaxAmt;

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public BigDecimal getTaxByValAmt() {
		return taxByValAmt;
	}

	public void setTaxByValAmt(BigDecimal taxByValAmt) {
		this.taxByValAmt = taxByValAmt;
	}

	public BigDecimal getTaxByQtyAmt() {
		return taxByQtyAmt;
	}

	public void setTaxByQtyAmt(BigDecimal taxByQtyAmt) {
		this.taxByQtyAmt = taxByQtyAmt;
	}

	public BigDecimal getSumTaxAmt() {
		return sumTaxAmt;
	}

	public void setSumTaxAmt(BigDecimal sumTaxAmt) {
		this.sumTaxAmt = sumTaxAmt;
	}

	public BigDecimal getAnaTaxByValAmt() {
		return anaTaxByValAmt;
	}

	public void setAnaTaxByValAmt(BigDecimal anaTaxByValAmt) {
		this.anaTaxByValAmt = anaTaxByValAmt;
	}

	public BigDecimal getAnaTaxByQtyAmt() {
		return anaTaxByQtyAmt;
	}

	public void setAnaTaxByQtyAmt(BigDecimal anaTaxByQtyAmt) {
		this.anaTaxByQtyAmt = anaTaxByQtyAmt;
	}

	public BigDecimal getSumAnaTaxAmt() {
		return sumAnaTaxAmt;
	}

	public void setSumAnaTaxAmt(BigDecimal sumAnaTaxAmt) {
		this.sumAnaTaxAmt = sumAnaTaxAmt;
	}

	public BigDecimal getDiffTaxByValAmt() {
		return diffTaxByValAmt;
	}

	public void setDiffTaxByValAmt(BigDecimal diffTaxByValAmt) {
		this.diffTaxByValAmt = diffTaxByValAmt;
	}

	public BigDecimal getDiffTaxByQtyAmt() {
		return diffTaxByQtyAmt;
	}

	public void setDiffTaxByQtyAmt(BigDecimal diffTaxByQtyAmt) {
		this.diffTaxByQtyAmt = diffTaxByQtyAmt;
	}

	public BigDecimal getDiffSumTaxAmt() {
		return diffSumTaxAmt;
	}

	public void setDiffSumTaxAmt(BigDecimal diffSumTaxAmt) {
		this.diffSumTaxAmt = diffSumTaxAmt;
	}

}
