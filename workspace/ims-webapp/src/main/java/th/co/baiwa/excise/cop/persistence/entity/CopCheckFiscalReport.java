package th.co.baiwa.excise.cop.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name="COP_CHECK_FISCAL_REPORT")
public class CopCheckFiscalReport extends BaseEntity  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 370085863774409717L;
	
	@Id
	@SequenceGenerator(name="COP_CHECK_FISCAL_REPORT_GEN", sequenceName="COP_CHECK_FISCAL_REPORT_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COP_CHECK_FISCAL_REPORT_GEN")
	@Column(name="FISCAL_REPORT_ID")
	private Long fiscalReportId;
	
	@Column(name="FISCAL_YEAR_ID")
	private Long fiscalYearId;
	
	@Column(name="EXCISE_AREA")
	private String exciseArea;
	
	@Column(name="EXCISE_SUB_AREA")
	private String exciseSubArea;
	
	@Column(name="EXCISE_ID")
	private String exciseId;
	
	@Column(name="COMPANY_NAME")
	private String companyName;
	
	@Column(name="PRODUCT")
	private String product;
	
	@Column(name="DATE_CALENDAR")
	private String dateCalendar;
	
	@Column(name="COMPANY_ADDRESS")
	private String companyAddress;
	
	@Column(name="RESULT_GET_RAW")
	private String resultGetRaw;
	
	@Column(name="RESULT_GET_BOX")
	private String resultGetBox;
	
	@Column(name="RESULT_PAY_RAW")
	private String resultPayRaw;
	
	@Column(name="RESULT_PAY_BOX")
	private String resultPayBox;
	
	@Column(name="RECEIPT_INVOICE_RAW")
	private String receiptInvoiceRaw;
	
	@Column(name="RECEIPT_INVOICE_BOX")
	private String receiptInvoiceBox;
	
	@Column(name="PAY_INVOICE_RAW")
	private String payInvoiceRaw;
	
	@Column(name="PAY_INVOICE_BOX")
	private String payInvoiceBox;

	@Column(name="OFFICER")
	private String officer;

	public Long getFiscalReportId() {
		return fiscalReportId;
	}

	public void setFiscalReportId(Long fiscalReportId) {
		this.fiscalReportId = fiscalReportId;
	}

	public Long getFiscalYearId() {
		return fiscalYearId;
	}

	public void setFiscalYearId(Long fiscalYearId) {
		this.fiscalYearId = fiscalYearId;
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

	public String getResultGetBox() {
		return resultGetBox;
	}

	public void setResultGetBox(String resultGetBox) {
		this.resultGetBox = resultGetBox;
	}

	public String getResultPayRaw() {
		return resultPayRaw;
	}

	public void setResultPayRaw(String resultPayRaw) {
		this.resultPayRaw = resultPayRaw;
	}

	public String getResultPayBox() {
		return resultPayBox;
	}

	public void setResultPayBox(String resultPayBox) {
		this.resultPayBox = resultPayBox;
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

	@Override
	public String toString() {
		return "CopCheckFiscalReport [fiscalReportId=" + fiscalReportId + ", fiscalYearId=" + fiscalYearId
				+ ", exciseArea=" + exciseArea + ", exciseSubArea=" + exciseSubArea + ", exciseId=" + exciseId
				+ ", companyName=" + companyName + ", product=" + product + ", dateCalendar=" + dateCalendar
				+ ", companyAddress=" + companyAddress + ", resultGetRaw=" + resultGetRaw + ", resultGetBox="
				+ resultGetBox + ", resultPayRaw=" + resultPayRaw + ", resultPayBox=" + resultPayBox
				+ ", receiptInvoiceRaw=" + receiptInvoiceRaw + ", receiptInvoiceBox=" + receiptInvoiceBox
				+ ", payInvoiceRaw=" + payInvoiceRaw + ", payInvoiceBox=" + payInvoiceBox + ", officer=" + officer
				+ "]";
	}
	

}
