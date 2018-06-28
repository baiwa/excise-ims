package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ReceiveRmatWsDetail {
	private BigDecimal taReceiveRmatWsDetailId;
	private BigDecimal taReceiveRmatHeaderId;
	private BigDecimal receiveRmatDetailNo;
	private String receiveRmatDetailOrder;
	private String purchaseTaxInv;
	private BigDecimal dayBook;
	private BigDecimal monthBook;
	private BigDecimal externalData;
	private String createdBy;
	private Date createdDatetime;
	private String updateBy;
	private Date updateDatetime;
	public BigDecimal getTaReceiveRmatWsDetailId() {
		return taReceiveRmatWsDetailId;
	}
	public void setTaReceiveRmatWsDetailId(BigDecimal taReceiveRmatWsDetailId) {
		this.taReceiveRmatWsDetailId = taReceiveRmatWsDetailId;
	}
	public BigDecimal getTaReceiveRmatHeaderId() {
		return taReceiveRmatHeaderId;
	}
	public void setTaReceiveRmatHeaderId(BigDecimal taReceiveRmatHeaderId) {
		this.taReceiveRmatHeaderId = taReceiveRmatHeaderId;
	}
	public BigDecimal getReceiveRmatDetailNo() {
		return receiveRmatDetailNo;
	}
	public void setReceiveRmatDetailNo(BigDecimal receiveRmatDetailNo) {
		this.receiveRmatDetailNo = receiveRmatDetailNo;
	}
	public String getReceiveRmatDetailOrder() {
		return receiveRmatDetailOrder;
	}
	public void setReceiveRmatDetailOrder(String receiveRmatDetailOrder) {
		this.receiveRmatDetailOrder = receiveRmatDetailOrder;
	}
	public String getPurchaseTaxInv() {
		return purchaseTaxInv;
	}
	public void setPurchaseTaxInv(String purchaseTaxInv) {
		this.purchaseTaxInv = purchaseTaxInv;
	}
	public BigDecimal getDayBook() {
		return dayBook;
	}
	public void setDayBook(BigDecimal dayBook) {
		this.dayBook = dayBook;
	}
	public BigDecimal getMonthBook() {
		return monthBook;
	}
	public void setMonthBook(BigDecimal monthBook) {
		this.monthBook = monthBook;
	}
	public BigDecimal getExternalData() {
		return externalData;
	}
	public void setExternalData(BigDecimal externalData) {
		this.externalData = externalData;
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
