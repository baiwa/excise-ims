package th.co.baiwa.excise.ia.persistence.vo;

import java.math.BigDecimal;
import java.util.Date;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Int09112FormVo extends DataTableRequest {

	private Long id;
	private Long idProcess;
	private String createdDate;
	private String createdBy;
	private String documentType;
	private String subject;
	private String searchFlag;
	
	
	

	/* ---------- formVo ------------ */
	private BigDecimal order;
	private	String filing;
	private	String deadLinesStr;
	private	Date deadLines;
	private	String ourself;
	private	String position;
	private	String affiliation;
	private	String borrowFrom;
	private	String forCost;
	private	String startDateStr;
	private	String endDateStr;
	private	Date startDate;
	private	Date endDate;
	private	BigDecimal allowance;
	private	BigDecimal accommodationCost;
	private	BigDecimal travelCost;
	private	BigDecimal otherCost;
	private	String totalCostStr;
	private	BigDecimal totalCost;
	private	BigDecimal deserveLend;
	private	String deserveLendStr;
	private	BigDecimal approvedLend;
	private	String approvedLendStr;
	private BigDecimal loanAmount;
	private	String loanAmountStr;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdProcess() {
		return idProcess;
	}
	public void setIdProcess(Long idProcess) {
		this.idProcess = idProcess;
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
	public String getSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}
	public BigDecimal getOrder() {
		return order;
	}
	public void setOrder(BigDecimal order) {
		this.order = order;
	}
	public String getFiling() {
		return filing;
	}
	public void setFiling(String filing) {
		this.filing = filing;
	}
	public String getDeadLinesStr() {
		return deadLinesStr;
	}
	public void setDeadLinesStr(String deadLinesStr) {
		this.deadLinesStr = deadLinesStr;
	}
	public Date getDeadLines() {
		return deadLines;
	}
	public void setDeadLines(Date deadLines) {
		this.deadLines = deadLines;
	}
	public String getOurself() {
		return ourself;
	}
	public void setOurself(String ourself) {
		this.ourself = ourself;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getAffiliation() {
		return affiliation;
	}
	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}
	public String getBorrowFrom() {
		return borrowFrom;
	}
	public void setBorrowFrom(String borrowFrom) {
		this.borrowFrom = borrowFrom;
	}
	public String getForCost() {
		return forCost;
	}
	public void setForCost(String forCost) {
		this.forCost = forCost;
	}
	public String getStartDateStr() {
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public BigDecimal getAllowance() {
		return allowance;
	}
	public void setAllowance(BigDecimal allowance) {
		this.allowance = allowance;
	}
	public BigDecimal getAccommodationCost() {
		return accommodationCost;
	}
	public void setAccommodationCost(BigDecimal accommodationCost) {
		this.accommodationCost = accommodationCost;
	}
	public BigDecimal getTravelCost() {
		return travelCost;
	}
	public void setTravelCost(BigDecimal travelCost) {
		this.travelCost = travelCost;
	}
	public BigDecimal getOtherCost() {
		return otherCost;
	}
	public void setOtherCost(BigDecimal otherCost) {
		this.otherCost = otherCost;
	}
	public String getTotalCostStr() {
		return totalCostStr;
	}
	public void setTotalCostStr(String totalCostStr) {
		this.totalCostStr = totalCostStr;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	public BigDecimal getDeserveLend() {
		return deserveLend;
	}
	public void setDeserveLend(BigDecimal deserveLend) {
		this.deserveLend = deserveLend;
	}
	public String getDeserveLendStr() {
		return deserveLendStr;
	}
	public void setDeserveLendStr(String deserveLendStr) {
		this.deserveLendStr = deserveLendStr;
	}
	public BigDecimal getApprovedLend() {
		return approvedLend;
	}
	public void setApprovedLend(BigDecimal approvedLend) {
		this.approvedLend = approvedLend;
	}
	public String getApprovedLendStr() {
		return approvedLendStr;
	}
	public void setApprovedLendStr(String approvedLendStr) {
		this.approvedLendStr = approvedLendStr;
	}
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getLoanAmountStr() {
		return loanAmountStr;
	}
	public void setLoanAmountStr(String loanAmountStr) {
		this.loanAmountStr = loanAmountStr;
	}

}