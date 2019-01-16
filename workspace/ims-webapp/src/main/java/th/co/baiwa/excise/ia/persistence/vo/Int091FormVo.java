package th.co.baiwa.excise.ia.persistence.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Int091FormVo extends DataTableRequest {
	/**
	 * 
	 */

	private static final long serialVersionUID = -1085458524740446987L;

	private Long id;
	private String createdDate;
	private String createdBy;
	private String pickedType;
	private String fiscalYear;
	private String departureDate;
	private String returnDate;
	private String travelTo;
	private String travelToDescription;
	private String status;
	private String errorMsg;

	private String dateFrom;
	private String dateTo;
	private String searchFlag;
	
	private String documentType;
	private String subject;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getPickedType() {
		return pickedType;
	}
	public void setPickedType(String pickedType) {
		this.pickedType = pickedType;
	}
	public String getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getTravelTo() {
		return travelTo;
	}
	public void setTravelTo(String travelTo) {
		this.travelTo = travelTo;
	}
	public String getTravelToDescription() {
		return travelToDescription;
	}
	public void setTravelToDescription(String travelToDescription) {
		this.travelToDescription = travelToDescription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public String getSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	
	
}
