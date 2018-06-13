package th.go.excise.ims.mockup.persistence.entity.ta;

import java.math.BigDecimal;
import java.util.Date;

public class TaxPlan {
	private BigDecimal taxPlanId;
	private BigDecimal wsHeaderId;
	private BigDecimal wsDtlId;
	private String exciseId;
	private String companyName;
	private String factoryName;
	private String factoryAddress;
	private String exciseOwnerArea;
	private String month;
	private String year;
	private String amount;
	private String createdBy;
	private Date createdDatetime;
	private String updateBy;
	private Date updateDatetime;
	public BigDecimal getTaxPlanId() {
		return taxPlanId;
	}
	public void setTaxPlanId(BigDecimal taxPlanId) {
		this.taxPlanId = taxPlanId;
	}
	public BigDecimal getWsHeaderId() {
		return wsHeaderId;
	}
	public void setWsHeaderId(BigDecimal wsHeaderId) {
		this.wsHeaderId = wsHeaderId;
	}
	public BigDecimal getWsDtlId() {
		return wsDtlId;
	}
	public void setWsDtlId(BigDecimal wsDtlId) {
		this.wsDtlId = wsDtlId;
	}
	public String getExciseId() {
		return exciseId;
	}
	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getFactoryAddress() {
		return factoryAddress;
	}
	public void setFactoryAddress(String factoryAddress) {
		this.factoryAddress = factoryAddress;
	}
	public String getExciseOwnerArea() {
		return exciseOwnerArea;
	}
	public void setExciseOwnerArea(String exciseOwnerArea) {
		this.exciseOwnerArea = exciseOwnerArea;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
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
