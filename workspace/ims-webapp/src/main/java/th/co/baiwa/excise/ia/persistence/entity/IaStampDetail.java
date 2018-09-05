package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

/**
 * The persistent class for the IA_STAMP_DETAIL database table.
 * 
 */
@Entity
@Table(name = "IA_STAMP_DETAIL")
public class IaStampDetail extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_STAMP_DETAIL_GEN")
	@SequenceGenerator(name = "IA_STAMP_DETAIL_GEN", sequenceName = "IA_STAMP_DETAIL_SEQ", allocationSize = 1)

	@Column(name = "WORK_SHEET_DETAIL_ID")
	private long workSheetDetailId;

	@Column(name = "BOOK_NUMBER_DELIVER_STAMP")
	private String bookNumberDeliverStamp;

	@Column(name = "BOOK_NUMBER_WITHDRAW_STAMP")
	private String bookNumberWithdrawStamp;

	@Column(name = "DATE_DELIVER_STAMP")
	private Date dateDeliverStamp;

	@Column(name = "DATE_OF_PAY")
	private Date dateOfPay;

	@Column(name = "DATE_WITHDRAW_STAMP")
	private Date dateWithdrawStamp;

	@Column(name = "DEPARTMENT_NAME")
	private String departmentName;

	@Column(name = "EXCISE_DEPARTMENT")
	private String exciseDepartment;

	@Column(name = "EXCISE_DISTRICT")
	private String exciseDistrict;

	@Column(name = "EXCISE_REGION")
	private String exciseRegion;

	@Column(name = "FIVE_PART_DATE")
	private Date fivePartDate;

	@Column(name = "FIVE_PART_NUMBER")
	private String fivePartNumber;

	@Column(name = "NOTE")
	private String note;

	@Column(name = "NUMBER_OF_BOOK")
	private BigDecimal numberOfBook;

	@Column(name = "NUMBER_OF_STAMP")
	private BigDecimal numberOfStamp;

	@Column(name = "SERIAL_NUMBER")
	private String serialNumber;

	@Column(name = "STAMP_BRAND")
	private String stampBrand;

	@Column(name = "STAMP_CHECK_DATE")
	private Date stampCheckDate;

	@Column(name = "STAMP_CHECKER")
	private String stampChecker;

	@Column(name = "STAMP_CODE_END")
	private String stampCodeEnd;

	@Column(name = "STAMP_CODE_START")
	private String stampCodeStart;

	@Column(name = "STAMP_TYPE")
	private String stampType;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "SUM_OF_VALUE")
	private BigDecimal sumOfValue;

	@Column(name = "TAX_STAMP")
	private BigDecimal taxStamp;

	@Column(name = "VALUE_OF_STAMP_PRINTED")
	private BigDecimal valueOfStampPrinted;

	@Column(name = "FILE_NAME")
	private String fileName;

	public long getWorkSheetDetailId() {
		return workSheetDetailId;
	}

	public void setWorkSheetDetailId(long workSheetDetailId) {
		this.workSheetDetailId = workSheetDetailId;
	}

	public String getBookNumberDeliverStamp() {
		return bookNumberDeliverStamp;
	}

	public void setBookNumberDeliverStamp(String bookNumberDeliverStamp) {
		this.bookNumberDeliverStamp = bookNumberDeliverStamp;
	}

	public String getBookNumberWithdrawStamp() {
		return bookNumberWithdrawStamp;
	}

	public void setBookNumberWithdrawStamp(String bookNumberWithdrawStamp) {
		this.bookNumberWithdrawStamp = bookNumberWithdrawStamp;
	}

	public Date getDateDeliverStamp() {
		return dateDeliverStamp;
	}

	public void setDateDeliverStamp(Date dateDeliverStamp) {
		this.dateDeliverStamp = dateDeliverStamp;
	}

	public Date getDateOfPay() {
		return dateOfPay;
	}

	public void setDateOfPay(Date dateOfPay) {
		this.dateOfPay = dateOfPay;
	}

	public Date getDateWithdrawStamp() {
		return dateWithdrawStamp;
	}

	public void setDateWithdrawStamp(Date dateWithdrawStamp) {
		this.dateWithdrawStamp = dateWithdrawStamp;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getExciseDepartment() {
		return exciseDepartment;
	}

	public void setExciseDepartment(String exciseDepartment) {
		this.exciseDepartment = exciseDepartment;
	}

	public String getExciseDistrict() {
		return exciseDistrict;
	}

	public void setExciseDistrict(String exciseDistrict) {
		this.exciseDistrict = exciseDistrict;
	}

	public String getExciseRegion() {
		return exciseRegion;
	}

	public void setExciseRegion(String exciseRegion) {
		this.exciseRegion = exciseRegion;
	}

	public Date getFivePartDate() {
		return fivePartDate;
	}

	public void setFivePartDate(Date fivePartDate) {
		this.fivePartDate = fivePartDate;
	}

	public String getFivePartNumber() {
		return fivePartNumber;
	}

	public void setFivePartNumber(String fivePartNumber) {
		this.fivePartNumber = fivePartNumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getNumberOfBook() {
		return numberOfBook;
	}

	public void setNumberOfBook(BigDecimal numberOfBook) {
		this.numberOfBook = numberOfBook;
	}

	public BigDecimal getNumberOfStamp() {
		return numberOfStamp;
	}

	public void setNumberOfStamp(BigDecimal numberOfStamp) {
		this.numberOfStamp = numberOfStamp;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getStampBrand() {
		return stampBrand;
	}

	public void setStampBrand(String stampBrand) {
		this.stampBrand = stampBrand;
	}

	public Date getStampCheckDate() {
		return stampCheckDate;
	}

	public void setStampCheckDate(Date stampCheckDate) {
		this.stampCheckDate = stampCheckDate;
	}

	public String getStampChecker() {
		return stampChecker;
	}

	public void setStampChecker(String stampChecker) {
		this.stampChecker = stampChecker;
	}

	public String getStampCodeEnd() {
		return stampCodeEnd;
	}

	public void setStampCodeEnd(String stampCodeEnd) {
		this.stampCodeEnd = stampCodeEnd;
	}

	public String getStampCodeStart() {
		return stampCodeStart;
	}

	public void setStampCodeStart(String stampCodeStart) {
		this.stampCodeStart = stampCodeStart;
	}

	public String getStampType() {
		return stampType;
	}

	public void setStampType(String stampType) {
		this.stampType = stampType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getSumOfValue() {
		return sumOfValue;
	}

	public void setSumOfValue(BigDecimal sumOfValue) {
		this.sumOfValue = sumOfValue;
	}

	public BigDecimal getTaxStamp() {
		return taxStamp;
	}

	public void setTaxStamp(BigDecimal taxStamp) {
		this.taxStamp = taxStamp;
	}

	public BigDecimal getValueOfStampPrinted() {
		return valueOfStampPrinted;
	}

	public void setValueOfStampPrinted(BigDecimal valueOfStampPrinted) {
		this.valueOfStampPrinted = valueOfStampPrinted;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}