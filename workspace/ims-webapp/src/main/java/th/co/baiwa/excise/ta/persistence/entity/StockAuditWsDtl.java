package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;

import th.co.baiwa.excise.domain.BaseEntity;


public class StockAuditWsDtl extends BaseEntity{
	private BigDecimal taStockAuditWsDtlId;
	private BigDecimal taStockAuditWsHeaderId;
	private String taStockAuditWsDtlList;
	private String taStockAuditWsDtlOrder;
	private String monthBook0701;
	private String totalGoods;

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
}
