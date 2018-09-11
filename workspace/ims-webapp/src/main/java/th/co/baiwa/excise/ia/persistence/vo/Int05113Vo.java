package th.co.baiwa.excise.ia.persistence.vo;

import th.co.baiwa.excise.ta.persistence.entity.ExciseFile;

import java.math.BigDecimal;

public class Int05113Vo {
	private String workSheetDetailId;
	private String exciseDepartment;
	private String exciseRegion;
	private String exciseDistrict;
	private String dateOfPay;
	private String status;
	private String departmentName;
	private String bookNumberWithdrawStamp;
	private String dateWithdrawStamp;
	private String bookNumberDeliverStamp;
	private String dateDeliverStamp;
	private String fivePartNumber;
	private String fivePartDate;
	private String stampCheckDate;
	private String stampChecker;
	private String stampType;
	private String stampBrand;
	private BigDecimal numberOfBook;
	private BigDecimal numberOfStamp;
	private BigDecimal valueOfStampPrinted;
	private BigDecimal sumOfValue;
	private String serialNumber;
	private BigDecimal taxStamp;
	private String stampCodeStart;
	private String stampCodeEnd;
	private String note;
	private String createdDate;
	private String fileName;
	private ExciseFile[] file;

    public ExciseFile[] getFile() {
        return file;
    }

    public void setFile(ExciseFile[] file) {
        this.file = file;
    }

    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getWorkSheetDetailId() {
		return workSheetDetailId;
	}

	public void setWorkSheetDetailId(String workSheetDetailId) {
		this.workSheetDetailId = workSheetDetailId;
	}

	public String getExciseDepartment() {
		return exciseDepartment;
	}

	public void setExciseDepartment(String exciseDepartment) {
		this.exciseDepartment = exciseDepartment;
	}

	public String getExciseRegion() {
		return exciseRegion;
	}

	public void setExciseRegion(String exciseRegion) {
		this.exciseRegion = exciseRegion;
	}

	public String getExciseDistrict() {
		return exciseDistrict;
	}

	public void setExciseDistrict(String exciseDistrict) {
		this.exciseDistrict = exciseDistrict;
	}

	public String getDateOfPay() {
		return dateOfPay;
	}

	public void setDateOfPay(String dateOfPay) {
		this.dateOfPay = dateOfPay;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getBookNumberWithdrawStamp() {
		return bookNumberWithdrawStamp;
	}

	public void setBookNumberWithdrawStamp(String bookNumberWithdrawStamp) {
		this.bookNumberWithdrawStamp = bookNumberWithdrawStamp;
	}

	public String getDateWithdrawStamp() {
		return dateWithdrawStamp;
	}

	public void setDateWithdrawStamp(String dateWithdrawStamp) {
		this.dateWithdrawStamp = dateWithdrawStamp;
	}

	public String getBookNumberDeliverStamp() {
		return bookNumberDeliverStamp;
	}

	public void setBookNumberDeliverStamp(String bookNumberDeliverStamp) {
		this.bookNumberDeliverStamp = bookNumberDeliverStamp;
	}

	public String getDateDeliverStamp() {
		return dateDeliverStamp;
	}

	public void setDateDeliverStamp(String dateDeliverStamp) {
		this.dateDeliverStamp = dateDeliverStamp;
	}

	public String getFivePartNumber() {
		return fivePartNumber;
	}

	public void setFivePartNumber(String fivePartNumber) {
		this.fivePartNumber = fivePartNumber;
	}

	public String getFivePartDate() {
		return fivePartDate;
	}

	public void setFivePartDate(String fivePartDate) {
		this.fivePartDate = fivePartDate;
	}

	public String getStampCheckDate() {
		return stampCheckDate;
	}

	public void setStampCheckDate(String stampCheckDate) {
		this.stampCheckDate = stampCheckDate;
	}

	public String getStampChecker() {
		return stampChecker;
	}

	public void setStampChecker(String stampChecker) {
		this.stampChecker = stampChecker;
	}

	public String getStampType() {
		return stampType;
	}

	public void setStampType(String stampType) {
		this.stampType = stampType;
	}

	public String getStampBrand() {
		return stampBrand;
	}

	public void setStampBrand(String stampBrand) {
		this.stampBrand = stampBrand;
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

	public BigDecimal getValueOfStampPrinted() {
		return valueOfStampPrinted;
	}

	public void setValueOfStampPrinted(BigDecimal valueOfStampPrinted) {
		this.valueOfStampPrinted = valueOfStampPrinted;
	}

	public BigDecimal getSumOfValue() {
		return sumOfValue;
	}

	public void setSumOfValue(BigDecimal sumOfValue) {
		this.sumOfValue = sumOfValue;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public BigDecimal getTaxStamp() {
		return taxStamp;
	}

	public void setTaxStamp(BigDecimal taxStamp) {
		this.taxStamp = taxStamp;
	}

	public String getStampCodeStart() {
		return stampCodeStart;
	}

	public void setStampCodeStart(String stampCodeStart) {
		this.stampCodeStart = stampCodeStart;
	}

	public String getStampCodeEnd() {
		return stampCodeEnd;
	}

	public void setStampCodeEnd(String stampCodeEnd) {
		this.stampCodeEnd = stampCodeEnd;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}