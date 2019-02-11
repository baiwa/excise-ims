package co.th.ims.taxaudit.vo;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.bean.BaseVo;

public class TaxAuditCreateWorkSheetFormVo extends BaseVo {
	
	private BigDecimal conditionHeaderId;
	private String budgetYear;
	private BigDecimal monthNum;
	private String areaSeeFlag;
	private String areaSelectFlag;
	private String noAuditYearNum;
	
	public BigDecimal getConditionHeaderId() {
		return conditionHeaderId;
	}
	public void setConditionHeaderId(BigDecimal conditionHeaderId) {
		this.conditionHeaderId = conditionHeaderId;
	}
	public String getBudgetYear() {
		return budgetYear;
	}
	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}
	public BigDecimal getMonthNum() {
		return monthNum;
	}
	public void setMonthNum(BigDecimal monthNum) {
		this.monthNum = monthNum;
	}
	public String getAreaSeeFlag() {
		return areaSeeFlag;
	}
	public void setAreaSeeFlag(String areaSeeFlag) {
		this.areaSeeFlag = areaSeeFlag;
	}
	public String getAreaSelectFlag() {
		return areaSelectFlag;
	}
	public void setAreaSelectFlag(String areaSelectFlag) {
		this.areaSelectFlag = areaSelectFlag;
	}
	public String getNoAuditYearNum() {
		return noAuditYearNum;
	}
	public void setNoAuditYearNum(String noAuditYearNum) {
		this.noAuditYearNum = noAuditYearNum;
	}
	
}
