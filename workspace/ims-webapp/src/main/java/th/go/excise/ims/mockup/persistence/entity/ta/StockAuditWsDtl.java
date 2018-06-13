package th.go.excise.ims.mockup.persistence.entity.ta;

import java.math.BigDecimal;
import java.util.Date;

public class StockAuditWsDtl {
	private BigDecimal taStockAuditWsDtlId;
	private BigDecimal taStockAuditWsHeaderId;
	private String taStockAuditWsDtlList;
	private String taStockAuditWsDtlOrder;
	private String monthBook0701;
	private String totalGoods;
	private String createdBy;
	private Date createdDatetime;
	private String updateBy;
	private Date updateDatetime;
	public BigDecimal getTaStockAuditWsDtlId() {
		return taStockAuditWsDtlId;
	}
	public void setTaStockAuditWsDtlId(BigDecimal taStockAuditWsDtlId) {
		this.taStockAuditWsDtlId = taStockAuditWsDtlId;
	}
	public BigDecimal getTaStockAuditWsHeaderId() {
		return taStockAuditWsHeaderId;
	}
	public void setTaStockAuditWsHeaderId(BigDecimal taStockAuditWsHeaderId) {
		this.taStockAuditWsHeaderId = taStockAuditWsHeaderId;
	}
	public String getTaStockAuditWsDtlList() {
		return taStockAuditWsDtlList;
	}
	public void setTaStockAuditWsDtlList(String taStockAuditWsDtlList) {
		this.taStockAuditWsDtlList = taStockAuditWsDtlList;
	}
	public String getTaStockAuditWsDtlOrder() {
		return taStockAuditWsDtlOrder;
	}
	public void setTaStockAuditWsDtlOrder(String taStockAuditWsDtlOrder) {
		this.taStockAuditWsDtlOrder = taStockAuditWsDtlOrder;
	}
	public String getMonthBook0701() {
		return monthBook0701;
	}
	public void setMonthBook0701(String monthBook0701) {
		this.monthBook0701 = monthBook0701;
	}
	public String getTotalGoods() {
		return totalGoods;
	}
	public void setTotalGoods(String totalGoods) {
		this.totalGoods = totalGoods;
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
