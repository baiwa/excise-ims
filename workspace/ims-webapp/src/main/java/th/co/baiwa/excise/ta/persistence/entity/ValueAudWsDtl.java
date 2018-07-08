package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;

import th.co.baiwa.excise.domain.BaseEntity;

public class ValueAudWsDtl extends BaseEntity {
	private BigDecimal taValueAudWsDtlId;
	private BigDecimal taValueAudWsHeaderId;
	private String month;
	private String taValueAudWsDtlOrder;
	private String serviceBill;
	private String dayBook0705;
	private String billAmount;
	private String externalData;
	private String monthBook0308;

	public BigDecimal getTaValueAudWsDtlId() {
		return taValueAudWsDtlId;
	}

	public void setTaValueAudWsDtlId(BigDecimal taValueAudWsDtlId) {
		this.taValueAudWsDtlId = taValueAudWsDtlId;
	}

	public BigDecimal getTaValueAudWsHeaderId() {
		return taValueAudWsHeaderId;
	}

	public void setTaValueAudWsHeaderId(BigDecimal taValueAudWsHeaderId) {
		this.taValueAudWsHeaderId = taValueAudWsHeaderId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getTaValueAudWsDtlOrder() {
		return taValueAudWsDtlOrder;
	}

	public void setTaValueAudWsDtlOrder(String taValueAudWsDtlOrder) {
		this.taValueAudWsDtlOrder = taValueAudWsDtlOrder;
	}

	public String getServiceBill() {
		return serviceBill;
	}

	public void setServiceBill(String serviceBill) {
		this.serviceBill = serviceBill;
	}

	public String getDayBook0705() {
		return dayBook0705;
	}

	public void setDayBook0705(String dayBook0705) {
		this.dayBook0705 = dayBook0705;
	}

	public String getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(String billAmount) {
		this.billAmount = billAmount;
	}

	public String getExternalData() {
		return externalData;
	}

	public void setExternalData(String externalData) {
		this.externalData = externalData;
	}

	public String getMonthBook0308() {
		return monthBook0308;
	}

	public void setMonthBook0308(String monthBook0308) {
		this.monthBook0308 = monthBook0308;
	}
}
