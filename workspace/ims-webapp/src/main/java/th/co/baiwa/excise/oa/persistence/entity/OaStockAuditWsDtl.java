package th.co.baiwa.excise.oa.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


@Entity
@Table(name="OA_STOCK_AUDIT_WS_DTL")
public class OaStockAuditWsDtl extends BaseEntity {

	private static final long serialVersionUID = 619062842154320261L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_STOCK_AUDIT_WS_DTL_GEN")
	@SequenceGenerator(name = "OA_STOCK_AUDIT_WS_DTL_GEN", sequenceName = "OA_STOCK_AUDIT_WS_DTL_SEQ", allocationSize = 1)
	@Column(name="STOCK_AUDIT_WS_DTL_ID")
	private Long stockAuditWsDtlId;

	@Column(name="MONTH_BOOK_07_01")
	private Long monthBook0701;
	
	@Column(name="STOCK_AUDIT_WS_DTL_LIST")
	private Long stockAuditWsDtlList;
	
	@Column(name="STOCK_AUDIT_WS_DTL_ORDER")
	private String stockAuditWsDtlOrder;
	
	@Column(name="STOCK_AUDIT_WS_HDR_ID")
	private Long stockAuditWsHdrId;
	
	@Column(name="TOTAL_GOODS")
	private Long totalGoods;

	public Long getStockAuditWsDtlId() {
		return stockAuditWsDtlId;
	}

	public void setStockAuditWsDtlId(Long stockAuditWsDtlId) {
		this.stockAuditWsDtlId = stockAuditWsDtlId;
	}

	public Long getMonthBook0701() {
		return monthBook0701;
	}

	public void setMonthBook0701(Long monthBook0701) {
		this.monthBook0701 = monthBook0701;
	}

	public Long getStockAuditWsDtlList() {
		return stockAuditWsDtlList;
	}

	public void setStockAuditWsDtlList(Long stockAuditWsDtlList) {
		this.stockAuditWsDtlList = stockAuditWsDtlList;
	}

	public String getStockAuditWsDtlOrder() {
		return stockAuditWsDtlOrder;
	}

	public void setStockAuditWsDtlOrder(String stockAuditWsDtlOrder) {
		this.stockAuditWsDtlOrder = stockAuditWsDtlOrder;
	}

	public Long getStockAuditWsHdrId() {
		return stockAuditWsHdrId;
	}

	public void setStockAuditWsHdrId(Long stockAuditWsHdrId) {
		this.stockAuditWsHdrId = stockAuditWsHdrId;
	}

	public Long getTotalGoods() {
		return totalGoods;
	}

	public void setTotalGoods(Long totalGoods) {
		this.totalGoods = totalGoods;
	}

	

}