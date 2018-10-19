package th.co.baiwa.excise.ta.persistence.entity.analysis;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_PLAN_WORK_SHEET_HEADER")
public class PlanWorksheetHeader extends BaseEntity {

	private static final long serialVersionUID = -9207974122284774626L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PLAN_WORK_SHEET_HEADER_GEN")
	@SequenceGenerator(name = "TA_PLAN_WORK_SHEET_HEADER_GEN", sequenceName = "TA_PLAN_WORK_SHEET_HEADER_SEQ", allocationSize = 1)

	@Column(name = "WORK_SHEET_HEADER_ID")
	private Long workSheetHeaderId;

	@Column(name = "ANALYS_NUMBER")
	private String analysNumber;

	@Column(name = "EXCISE_ID")
	private String exciseId;

	@Column(name = "COMPANY_NAME")
	private String companyName;

	@Column(name = "FACTORY_NAME")
	private String factoryName;

	@Column(name = "FACTORY_ADDRESS")
	private String factoryAddress;

	@Column(name = "EXCISE_OWNER_AREA")
	private String exciseOwnerArea;

	@Column(name = "PRODUCT_TYPE")
	private String productType;

	@Column(name = "EXCISE_OWNER_AREA_1")
	private String exciseOwnerArea1;

	@Column(name = "TOTAL_AMOUNT")
	private BigDecimal totalAmount;

	@Column(name = "PERCENTAGE")
	private BigDecimal percentage;

	@Column(name = "TOTAL_MONTH")
	private String totalMonth;

	@Column(name = "DECIDE_TYPE")
	private String decideType;

	@Column(name = "FLAG")
	private String flag;

	@Column(name = "FIRST_MONTH")
	private BigDecimal firstMonth;

	@Column(name = "LAST_MONTH")
	private BigDecimal lastMonth;

	@Column(name = "WORK_SHEET_NUMBER")
	private String workSheetNumber;

	@Column(name = "MONTH_DATE")
	private String monthDate;

	@Column(name = "FULL_MONTH")
	private BigDecimal fullMonth;

	@Column(name = "VIEW_STATUS")
	private String viewStatus;

	@Column(name = "CENTRAL")
	private String central;

	@Column(name = "SECTOR")
	private String sector;

	public Long getWorkSheetHeaderId() {
		return workSheetHeaderId;
	}

	public void setWorkSheetHeaderId(Long workSheetHeaderId) {
		this.workSheetHeaderId = workSheetHeaderId;
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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public String getTotalMonth() {
		return totalMonth;
	}

	public void setTotalMonth(String totalMonth) {
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

	public String getViewStatus() {
		return viewStatus;
	}

	public void setViewStatus(String viewStatus) {
		this.viewStatus = viewStatus;
	}

	public String getCentral() {
		return central;
	}

	public void setCentral(String central) {
		this.central = central;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

}
