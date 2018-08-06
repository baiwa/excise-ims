package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class DisbRmatWorksheetDetail extends BaseEntity {
	private BigDecimal taDisbRmatWsDtlId;
	private BigDecimal taDisburseRawMatHeadId;
	private BigDecimal disburseRawMatDtlNo;
	private String disburseRawMatDtlOrder;
	private String rawMatRequisition;
	private String dayBook0701;
	private String monthBook0704;
	private String monthlyReport;

	public BigDecimal getTaDisbRmatWsDtlId() {
		return taDisbRmatWsDtlId;
	}

	public void setTaDisbRmatWsDtlId(BigDecimal taDisbRmatWsDtlId) {
		this.taDisbRmatWsDtlId = taDisbRmatWsDtlId;
	}

	public BigDecimal getTaDisburseRawMatHeadId() {
		return taDisburseRawMatHeadId;
	}

	public void setTaDisburseRawMatHeadId(BigDecimal taDisburseRawMatHeadId) {
		this.taDisburseRawMatHeadId = taDisburseRawMatHeadId;
	}

	public BigDecimal getDisburseRawMatDtlNo() {
		return disburseRawMatDtlNo;
	}

	public void setDisburseRawMatDtlNo(BigDecimal disburseRawMatDtlNo) {
		this.disburseRawMatDtlNo = disburseRawMatDtlNo;
	}

	public String getDisburseRawMatDtlOrder() {
		return disburseRawMatDtlOrder;
	}

	public void setDisburseRawMatDtlOrder(String disburseRawMatDtlOrder) {
		this.disburseRawMatDtlOrder = disburseRawMatDtlOrder;
	}

	public String getRawMatRequisition() {
		return rawMatRequisition;
	}

	public void setRawMatRequisition(String rawMatRequisition) {
		this.rawMatRequisition = rawMatRequisition;
	}

	public String getDayBook0701() {
		return dayBook0701;
	}

	public void setDayBook0701(String dayBook0701) {
		this.dayBook0701 = dayBook0701;
	}

	public String getMonthBook0704() {
		return monthBook0704;
	}

	public void setMonthBook0704(String monthBook0704) {
		this.monthBook0704 = monthBook0704;
	}

	public String getMonthlyReport() {
		return monthlyReport;
	}

	public void setMonthlyReport(String monthlyReport) {
		this.monthlyReport = monthlyReport;
	}

}
