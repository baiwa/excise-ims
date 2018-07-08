package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;


import th.co.baiwa.excise.domain.BaseEntity;

public class PdtReceiveWsDtl extends BaseEntity{
	private BigDecimal taPdtWsDtlId;
	private BigDecimal taPdtWsHeaderId;
	private String taPdtWsDtlNo;
	private String taPdtWsDtlOrder;
	private String pdtReceiveBill;
	private String monthBook0704;
	private String account0702;

	public BigDecimal getTaPdtWsDtlId() {
		return taPdtWsDtlId;
	}

	public void setTaPdtWsDtlId(BigDecimal taPdtWsDtlId) {
		this.taPdtWsDtlId = taPdtWsDtlId;
	}

	public BigDecimal getTaPdtWsHeaderId() {
		return taPdtWsHeaderId;
	}

	public void setTaPdtWsHeaderId(BigDecimal taPdtWsHeaderId) {
		this.taPdtWsHeaderId = taPdtWsHeaderId;
	}

	public String getTaPdtWsDtlNo() {
		return taPdtWsDtlNo;
	}

	public void setTaPdtWsDtlNo(String taPdtWsDtlNo) {
		this.taPdtWsDtlNo = taPdtWsDtlNo;
	}

	public String getTaPdtWsDtlOrder() {
		return taPdtWsDtlOrder;
	}

	public void setTaPdtWsDtlOrder(String taPdtWsDtlOrder) {
		this.taPdtWsDtlOrder = taPdtWsDtlOrder;
	}

	public String getPdtReceiveBill() {
		return pdtReceiveBill;
	}

	public void setPdtReceiveBill(String pdtReceiveBill) {
		this.pdtReceiveBill = pdtReceiveBill;
	}

	public String getMonthBook0704() {
		return monthBook0704;
	}

	public void setMonthBook0704(String monthBook0704) {
		this.monthBook0704 = monthBook0704;
	}

	public String getAccount0702() {
		return account0702;
	}

	public void setAccount0702(String account0702) {
		this.account0702 = account0702;
	}

}
