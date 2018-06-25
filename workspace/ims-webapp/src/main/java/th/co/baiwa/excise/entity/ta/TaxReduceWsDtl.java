package th.co.baiwa.excise.entity.ta;

import java.math.BigDecimal;
import java.util.Date;

public class TaxReduceWsDtl {
	private BigDecimal taTaxReduceWsDtlId;
	private BigDecimal taTaxReduceWsHeaderId;
	private String month;
	private String taTaxReduceWsDtlOrder;
	private String totalTax;
	private String pdtAmount1;
	private String taxPerPdt;
	private String billNo;
	private String taxAmount;
	private String pdtAmount2;
	private String createdBy;
	private Date createdDatetime;
	private String updateBy;
	private Date updateDatetime;
	public BigDecimal getTaTaxReduceWsDtlId() {
		return taTaxReduceWsDtlId;
	}
	public void setTaTaxReduceWsDtlId(BigDecimal taTaxReduceWsDtlId) {
		this.taTaxReduceWsDtlId = taTaxReduceWsDtlId;
	}
	public BigDecimal getTaTaxReduceWsHeaderId() {
		return taTaxReduceWsHeaderId;
	}
	public void setTaTaxReduceWsHeaderId(BigDecimal taTaxReduceWsHeaderId) {
		this.taTaxReduceWsHeaderId = taTaxReduceWsHeaderId;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getTaTaxReduceWsDtlOrder() {
		return taTaxReduceWsDtlOrder;
	}
	public void setTaTaxReduceWsDtlOrder(String taTaxReduceWsDtlOrder) {
		this.taTaxReduceWsDtlOrder = taTaxReduceWsDtlOrder;
	}
	public String getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}
	public String getPdtAmount1() {
		return pdtAmount1;
	}
	public void setPdtAmount1(String pdtAmount1) {
		this.pdtAmount1 = pdtAmount1;
	}
	public String getTaxPerPdt() {
		return taxPerPdt;
	}
	public void setTaxPerPdt(String taxPerPdt) {
		this.taxPerPdt = taxPerPdt;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}
	public String getPdtAmount2() {
		return pdtAmount2;
	}
	public void setPdtAmount2(String pdtAmount2) {
		this.pdtAmount2 = pdtAmount2;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDatetime() {
		return createdDatetime;
	}
	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
}
