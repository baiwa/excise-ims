package th.co.baiwa.excise.ta.persistence.vo;

import java.math.BigDecimal;

public class Ope0412Vo {

	private String list;
	private BigDecimal totalTax;
	private BigDecimal pdtAmount1;
	private BigDecimal taxPerPdt;
	private String billNo;
	private BigDecimal taxAmount;
	private BigDecimal pdtSAmount2;
	private BigDecimal maxValues;
	private BigDecimal result;

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public BigDecimal getPdtAmount1() {
		return pdtAmount1;
	}

	public void setPdtAmount1(BigDecimal pdtAmount1) {
		this.pdtAmount1 = pdtAmount1;
	}

	public BigDecimal getTaxPerPdt() {
		return taxPerPdt;
	}

	public void setTaxPerPdt(BigDecimal taxPerPdt) {
		this.taxPerPdt = taxPerPdt;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getPdtSAmount2() {
		return pdtSAmount2;
	}

	public void setPdtSAmount2(BigDecimal pdtSAmount2) {
		this.pdtSAmount2 = pdtSAmount2;
	}

	public BigDecimal getMaxValues() {
		return maxValues;
	}

	public void setMaxValues(BigDecimal maxValues) {
		this.maxValues = maxValues;
	}

	public BigDecimal getResult() {
		return result;
	}

	public void setResult(BigDecimal result) {
		this.result = result;
	}

}
