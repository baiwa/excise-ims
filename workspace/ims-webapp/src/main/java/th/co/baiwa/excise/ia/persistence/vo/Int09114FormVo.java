package th.co.baiwa.excise.ia.persistence.vo;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Int09114FormVo extends DataTableRequest {

	private Long id;
	private Long idProcess;
	private String createdDate;
	private String createdBy;
	private String documentType;
	private String subject;
	private String searchFlag;

	/*----- formVo -----*/
	private String dateRefStr;
	private String borrowerName;
	private BigDecimal amount;
	private String dateCurrentStr;
	// private String subject ;
	private String invite;
	private String saveTo;
	private String dateSaveToStr;
	private String approve;
	private String position;
	private String stack;
	private String descTravel;
	private Long service;
	private Long day;
	private String travelRadio;
	private String datetravelRadioStr;
	private String backToRadio;
	private String dateBackToRadioStr;
	private String withdrawRadio;
	private BigDecimal allowanceAmount;
	private BigDecimal allowance;
	private BigDecimal accommodationCostAmount;
	private BigDecimal accommodationCost;
	private BigDecimal travelCost;
	private BigDecimal otherCost;
	private BigDecimal totalCost;
	private String totalCostStr;
	private BigDecimal docAmount;
	private String payee;
	private String payeePosition;
	private BigDecimal getTravelCost;
	private String getTravelCostStr;
	private String payee2;
	private String payeePosition2;
	private String datePayerStr;
	private String descTravel2;
	
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
	public String getDateRefStr() {
		return dateRefStr;
	}
	public void setDateRefStr(String dateRefStr) {
		this.dateRefStr = dateRefStr;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getDateCurrentStr() {
		return dateCurrentStr;
	}
	public void setDateCurrentStr(String dateCurrentStr) {
		this.dateCurrentStr = dateCurrentStr;
	}
	public String getInvite() {
		return invite;
	}
	public void setInvite(String invite) {
		this.invite = invite;
	}
	public String getSaveTo() {
		return saveTo;
	}
	public void setSaveTo(String saveTo) {
		this.saveTo = saveTo;
	}
	public String getDateSaveToStr() {
		return dateSaveToStr;
	}
	public void setDateSaveToStr(String dateSaveToStr) {
		this.dateSaveToStr = dateSaveToStr;
	}
	public String getApprove() {
		return approve;
	}
	public void setApprove(String approve) {
		this.approve = approve;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getStack() {
		return stack;
	}
	public void setStack(String stack) {
		this.stack = stack;
	}
	public String getDescTravel() {
		return descTravel;
	}
	public void setDescTravel(String descTravel) {
		this.descTravel = descTravel;
	}
	public Long getService() {
		return service;
	}
	public void setService(Long service) {
		this.service = service;
	}
	public Long getDay() {
		return day;
	}
	public void setDay(Long day) {
		this.day = day;
	}
	public String getTravelRadio() {
		return travelRadio;
	}
	public void setTravelRadio(String travelRadio) {
		this.travelRadio = travelRadio;
	}
	public String getDatetravelRadioStr() {
		return datetravelRadioStr;
	}
	public void setDatetravelRadioStr(String datetravelRadioStr) {
		this.datetravelRadioStr = datetravelRadioStr;
	}
	public String getBackToRadio() {
		return backToRadio;
	}
	public void setBackToRadio(String backToRadio) {
		this.backToRadio = backToRadio;
	}
	public String getDateBackToRadioStr() {
		return dateBackToRadioStr;
	}
	public void setDateBackToRadioStr(String dateBackToRadioStr) {
		this.dateBackToRadioStr = dateBackToRadioStr;
	}
	public String getWithdrawRadio() {
		return withdrawRadio;
	}
	public void setWithdrawRadio(String withdrawRadio) {
		this.withdrawRadio = withdrawRadio;
	}
	public BigDecimal getAllowanceAmount() {
		return allowanceAmount;
	}
	public void setAllowanceAmount(BigDecimal allowanceAmount) {
		this.allowanceAmount = allowanceAmount;
	}
	public BigDecimal getAllowance() {
		return allowance;
	}
	public void setAllowance(BigDecimal allowance) {
		this.allowance = allowance;
	}
	public BigDecimal getAccommodationCostAmount() {
		return accommodationCostAmount;
	}
	public void setAccommodationCostAmount(BigDecimal accommodationCostAmount) {
		this.accommodationCostAmount = accommodationCostAmount;
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
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	public String getTotalCostStr() {
		return totalCostStr;
	}
	public void setTotalCostStr(String totalCostStr) {
		this.totalCostStr = totalCostStr;
	}
	public BigDecimal getDocAmount() {
		return docAmount;
	}
	public void setDocAmount(BigDecimal docAmount) {
		this.docAmount = docAmount;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String getPayeePosition() {
		return payeePosition;
	}
	public void setPayeePosition(String payeePosition) {
		this.payeePosition = payeePosition;
	}
	public BigDecimal getGetTravelCost() {
		return getTravelCost;
	}
	public void setGetTravelCost(BigDecimal getTravelCost) {
		this.getTravelCost = getTravelCost;
	}
	public String getGetTravelCostStr() {
		return getTravelCostStr;
	}
	public void setGetTravelCostStr(String getTravelCostStr) {
		this.getTravelCostStr = getTravelCostStr;
	}
	public String getPayee2() {
		return payee2;
	}
	public void setPayee2(String payee2) {
		this.payee2 = payee2;
	}
	public String getPayeePosition2() {
		return payeePosition2;
	}
	public void setPayeePosition2(String payeePosition2) {
		this.payeePosition2 = payeePosition2;
	}
	public String getDatePayerStr() {
		return datePayerStr;
	}
	public void setDatePayerStr(String datePayerStr) {
		this.datePayerStr = datePayerStr;
	}
	public String getDescTravel2() {
		return descTravel2;
	}
	public void setDescTravel2(String descTravel2) {
		this.descTravel2 = descTravel2;
	}
	
}