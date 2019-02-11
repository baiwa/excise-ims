package co.th.ims.taxaudit.vo;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.bean.BaseVo;

public class TaxAuditConditionFormVo extends BaseVo {
	
	private BigDecimal conditionDetailId;
	private BigDecimal conditionHeaderId;
	private String conditionGroup;
	private BigDecimal taxMonthStart;
	private BigDecimal taxMonthEnd;
	private BigDecimal rangeStart;
	private BigDecimal rangeEnd;
	private String riskLevel;
	
	public BigDecimal getConditionDetailId() {
		return conditionDetailId;
	}
	public void setConditionDetailId(BigDecimal conditionDetailId) {
		this.conditionDetailId = conditionDetailId;
	}
	public BigDecimal getConditionHeaderId() {
		return conditionHeaderId;
	}
	public void setConditionHeaderId(BigDecimal conditionHeaderId) {
		this.conditionHeaderId = conditionHeaderId;
	}
	public String getConditionGroup() {
		return conditionGroup;
	}
	public void setConditionGroup(String conditionGroup) {
		this.conditionGroup = conditionGroup;
	}
	public BigDecimal getTaxMonthStart() {
		return taxMonthStart;
	}
	public void setTaxMonthStart(BigDecimal taxMonthStart) {
		this.taxMonthStart = taxMonthStart;
	}
	public BigDecimal getTaxMonthEnd() {
		return taxMonthEnd;
	}
	public void setTaxMonthEnd(BigDecimal taxMonthEnd) {
		this.taxMonthEnd = taxMonthEnd;
	}
	public BigDecimal getRangeStart() {
		return rangeStart;
	}
	public void setRangeStart(BigDecimal rangeStart) {
		this.rangeStart = rangeStart;
	}
	public BigDecimal getRangeEnd() {
		return rangeEnd;
	}
	public void setRangeEnd(BigDecimal rangeEnd) {
		this.rangeEnd = rangeEnd;
	}
	public String getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	
}
