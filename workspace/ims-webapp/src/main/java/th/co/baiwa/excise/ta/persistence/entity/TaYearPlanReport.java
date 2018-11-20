package th.co.baiwa.excise.ta.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_YEAR_PLAN_REPORT")
public class TaYearPlanReport extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4223416676657436796L;
	
	
	@Id
	@SequenceGenerator(name = "TA_YEAR_PLAN_REPORT_GEN", sequenceName = "TA_YEAR_PLAN_REPORT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_YEAR_PLAN_REPORT_GEN")
	@Column(name = "PLAN_REPORT_ID")
	private Long planReportId;
	
	@Column(name = "YEAR_PLAN_ID")
	private Long yearPlanId;
	
	@Column(name = "EXCISE_AREA")
	private String exciseArea;
	
	@Column(name = "EXCISE_SUB_AREA")
	private String exciseSubArea;
	
	@Column(name = "EXCISE_ID")
	private String exciseId;
	
	@Column(name = "COMPANY_NAME")
	private String companyName;
	
	@Column(name = "PRODUCT")
	private String product;
	
	@Column(name = "RISK_TYPE_DESC")
	private String riskTypeDesc;
	
	@Column(name = "DATE_CALENDAR")
	private String dateCalendar;
	
	@Column(name = "COMPANY_ADDRESS")
	private String companyAddress;
	
	@Column(name = "RESULT_GET_RAW")
	private String resultGetRaw;
	
	@Column(name = "RESULT_GET_RAW_BOX")
	private String resultGetRawBox;
	
	@Column(name = "RESULT_PAY_RAW")
	private String resultPayRaw;
	
	@Column(name = "RESULT_PAY_RAW_BOX")
	private String resultPayRawBox;
	
	@Column(name = "RECEIPT_INVOICE_RAW")
	private String receiptInvoiceRaw;
	
	@Column(name = "RECEIPT_INVOICE_BOX")
	private String receiptInvoiceBox;
	
	@Column(name = "PAY_INVOICE_RAW")
	private String payInvoiceRaw;
	
	@Column(name = "PAY_INVOICE_BOX")
	private String payInvoiceBox;
	
	@Column(name = "OFFICER")
	private String officer;
	
	public Long getPlanReportId() {
		return planReportId;
	}
	public void setPlanReportId(Long planReportId) {
		this.planReportId = planReportId;
	}
	public Long getYearPlanId() {
		return yearPlanId;
	}
	public void setYearPlanId(Long yearPlanId) {
		this.yearPlanId = yearPlanId;
	}
	public String getExciseArea() {
		return exciseArea;
	}
	public void setExciseArea(String exciseArea) {
		this.exciseArea = exciseArea;
	}
	public String getExciseSubArea() {
		return exciseSubArea;
	}
	public void setExciseSubArea(String exciseSubArea) {
		this.exciseSubArea = exciseSubArea;
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
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getRiskTypeDesc() {
		return riskTypeDesc;
	}
	public void setRiskTypeDesc(String riskTypeDesc) {
		this.riskTypeDesc = riskTypeDesc;
	}
	public String getDateCalendar() {
		return dateCalendar;
	}
	public void setDateCalendar(String dateCalendar) {
		this.dateCalendar = dateCalendar;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getResultGetRaw() {
		return resultGetRaw;
	}
	public void setResultGetRaw(String resultGetRaw) {
		this.resultGetRaw = resultGetRaw;
	}
	public String getResultGetRawBox() {
		return resultGetRawBox;
	}
	public void setResultGetRawBox(String resultGetRawBox) {
		this.resultGetRawBox = resultGetRawBox;
	}
	public String getResultPayRaw() {
		return resultPayRaw;
	}
	public void setResultPayRaw(String resultPayRaw) {
		this.resultPayRaw = resultPayRaw;
	}
	public String getResultPayRawBox() {
		return resultPayRawBox;
	}
	public void setResultPayRawBox(String resultPayRawBox) {
		this.resultPayRawBox = resultPayRawBox;
	}
	public String getReceiptInvoiceRaw() {
		return receiptInvoiceRaw;
	}
	public void setReceiptInvoiceRaw(String receiptInvoiceRaw) {
		this.receiptInvoiceRaw = receiptInvoiceRaw;
	}
	public String getReceiptInvoiceBox() {
		return receiptInvoiceBox;
	}
	public void setReceiptInvoiceBox(String receiptInvoiceBox) {
		this.receiptInvoiceBox = receiptInvoiceBox;
	}
	public String getPayInvoiceRaw() {
		return payInvoiceRaw;
	}
	public void setPayInvoiceRaw(String payInvoiceRaw) {
		this.payInvoiceRaw = payInvoiceRaw;
	}
	public String getPayInvoiceBox() {
		return payInvoiceBox;
	}
	public void setPayInvoiceBox(String payInvoiceBox) {
		this.payInvoiceBox = payInvoiceBox;
	}
	public String getOfficer() {
		return officer;
	}
	public void setOfficer(String officer) {
		this.officer = officer;
	}
	



	
}
