package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the TA_PLAN_FROM_WS_HEADER database table.
 * 
 */
@Entity
@Table(name="TA_PLAN_FROM_WS_HEADER")
public class PlanFromWsHeader extends BaseEntity{

	private static final long serialVersionUID = -8598589665950907033L;

	@Id
	@SequenceGenerator(name="TA_PLAN_FROM_WS_HEADER_GEN", sequenceName="TA_PLAN_FROM_WS_HEADER_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_PLAN_FROM_WS_HEADER_GEN")
	@Column(name="WORK_SHEET_HEADER_ID")
	private long workSheetHeaderId;

	@Column(name="ANALYS_NUMBER")
	private String analysNumber;

	private String central;

	@Column(name="COMPANY_NAME")
	private String companyName;

	@Column(name="DECIDE_TYPE")
	private String decideType;

	@Column(name="EXCISE_ID")
	private String exciseId;

	@Column(name="EXCISE_OWNER_AREA")
	private String exciseOwnerArea;

	@Column(name="EXCISE_OWNER_AREA_1")
	private String exciseOwnerArea1;

	@Column(name="FACTORY_ADDRESS")
	private String factoryAddress;

	@Column(name="FACTORY_NAME")
	private String factoryName;

	@Column(name="FIRST_MONTH")
	private BigDecimal firstMonth;

	private String flag;

	@Column(name="FULL_MONTH")
	private BigDecimal fullMonth;

	@Column(name="LAST_MONTH")
	private BigDecimal lastMonth;

	@Column(name="MONTH_DATE")
	private String monthDate;

	private BigDecimal percentage;

	@Column(name="PRODUCT_TYPE")
	private String productType;

	private String sector;

	@Column(name="TOTAL_AMOUNT")
	private BigDecimal totalAmount;

	@Column(name="TOTAL_MONTH")
	private BigDecimal totalMonth;

	@Column(name="VIEW_STATUS")
	private String viewStatus;

	@Column(name="WORK_SHEET_NUMBER")
	private String workSheetNumber;

	public PlanFromWsHeader() {
	}

	public long getWorkSheetHeaderId() {
		return this.workSheetHeaderId;
	}

	public void setWorkSheetHeaderId(long workSheetHeaderId) {
		this.workSheetHeaderId = workSheetHeaderId;
	}

	public String getAnalysNumber() {
		return this.analysNumber;
	}

	public void setAnalysNumber(String analysNumber) {
		this.analysNumber = analysNumber;
	}

	public String getCentral() {
		return this.central;
	}

	public void setCentral(String central) {
		this.central = central;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDecideType() {
		return this.decideType;
	}

	public void setDecideType(String decideType) {
		this.decideType = decideType;
	}

	public String getExciseId() {
		return this.exciseId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public String getExciseOwnerArea() {
		return this.exciseOwnerArea;
	}

	public void setExciseOwnerArea(String exciseOwnerArea) {
		this.exciseOwnerArea = exciseOwnerArea;
	}

	public String getExciseOwnerArea1() {
		return this.exciseOwnerArea1;
	}

	public void setExciseOwnerArea1(String exciseOwnerArea1) {
		this.exciseOwnerArea1 = exciseOwnerArea1;
	}

	public String getFactoryAddress() {
		return this.factoryAddress;
	}

	public void setFactoryAddress(String factoryAddress) {
		this.factoryAddress = factoryAddress;
	}

	public String getFactoryName() {
		return this.factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public BigDecimal getFirstMonth() {
		return this.firstMonth;
	}

	public void setFirstMonth(BigDecimal firstMonth) {
		this.firstMonth = firstMonth;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public BigDecimal getFullMonth() {
		return this.fullMonth;
	}

	public void setFullMonth(BigDecimal fullMonth) {
		this.fullMonth = fullMonth;
	}


	public BigDecimal getLastMonth() {
		return this.lastMonth;
	}

	public void setLastMonth(BigDecimal lastMonth) {
		this.lastMonth = lastMonth;
	}

	public String getMonthDate() {
		return this.monthDate;
	}

	public void setMonthDate(String monthDate) {
		this.monthDate = monthDate;
	}

	public BigDecimal getPercentage() {
		return this.percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getSector() {
		return this.sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTotalMonth() {
		return this.totalMonth;
	}

	public void setTotalMonth(BigDecimal totalMonth) {
		this.totalMonth = totalMonth;
	}


	public String getViewStatus() {
		return this.viewStatus;
	}

	public void setViewStatus(String viewStatus) {
		this.viewStatus = viewStatus;
	}

	public String getWorkSheetNumber() {
		return this.workSheetNumber;
	}

	public void setWorkSheetNumber(String workSheetNumber) {
		this.workSheetNumber = workSheetNumber;
	}

}