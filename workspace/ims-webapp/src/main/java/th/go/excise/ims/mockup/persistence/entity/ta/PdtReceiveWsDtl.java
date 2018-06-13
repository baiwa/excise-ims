package th.go.excise.ims.mockup.persistence.entity.ta;

import java.math.BigDecimal;
import java.util.Date;

public class PdtReceiveWsDtl {
	private BigDecimal taPdtWsDtlId;
	private BigDecimal taPdtWsHeaderId;
	private String taPdtWsDtlNo;
	private String taPdtWsDtlOrder;
	private String pdtReceiveBill;
	private String monthBook0704;
	private String account0702;
	private String createdBy;
	private Date createdDatetime;
	private String updateBy;
	private Date updateDatetime;
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
