package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.domain.BaseEntity;

@Repository
public class PlanWorksheetHeader extends BaseEntity {

	private BigDecimal worksheetHeaderId;
	private String workSheetNumber;
	private String analysNumber;
	private String exciseId;
	private String companyName;
	private String factoryName;
	private String factoryAddress;
	private String exciseOwnerArea;
	private String productType;
	private String exciseOwnerArea1;
	private BigDecimal totalAmount;
	private BigDecimal percentage;
	private BigDecimal totalMonth;
	private String decideType;
	private String flag;
	private BigDecimal firstMonth;
	private BigDecimal lastMonth;
	private String monthDate;
	private BigDecimal fullMonth;

	public BigDecimal getWorksheetHeaderId() {
		return worksheetHeaderId;
	}

	public void setWorksheetHeaderId(BigDecimal worksheetHeaderId) {
		this.worksheetHeaderId = worksheetHeaderId;
	}

	public String getAnalysNumber() {
		return analysNumber;
	}

	public void setAnalysNumber(String analysNumber) {
		this.analysNumber = analysNumber;
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

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getExciseOwnerArea1() {
		return exciseOwnerArea1;
	}

	public void setExciseOwnerArea1(String exciseOwnerArea1) {
		this.exciseOwnerArea1 = exciseOwnerArea1;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public BigDecimal getTotalMonth() {
		return totalMonth;
	}

	public void setTotalMonth(BigDecimal totalMonth) {
		this.totalMonth = totalMonth;
	}

	public String getDecideType() {
		return decideType;
	}

	public void setDecideType(String decideType) {
		this.decideType = decideType;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public BigDecimal getFirstMonth() {
		return firstMonth;
	}

	public void setFirstMonth(BigDecimal firstMonth) {
		this.firstMonth = firstMonth;
	}

	public BigDecimal getLastMonth() {
		return lastMonth;
	}

	public void setLastMonth(BigDecimal lastMonth) {
		this.lastMonth = lastMonth;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getWorkSheetNumber() {
		return workSheetNumber;
	}

	public void setWorkSheetNumber(String workSheetNumber) {
		this.workSheetNumber = workSheetNumber;
	}

	public String getMonthDate() {
		return monthDate;
	}

	public void setMonthDate(String monthDate) {
		this.monthDate = monthDate;
	}

	public BigDecimal getFullMonth() {
		return fullMonth;
	}

	public void setFullMonth(BigDecimal fullMonth) {
		this.fullMonth = fullMonth;
	}

}
