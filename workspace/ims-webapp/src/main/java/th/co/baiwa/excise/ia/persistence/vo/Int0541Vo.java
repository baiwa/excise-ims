package th.co.baiwa.excise.ia.persistence.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.excise.ia.persistence.entity.IaProcurementList;

public class Int0541Vo {
	
	private MultipartFile file;
	private Long procurementId;
	private String approveDatePlan;
	private String approveDateReport;
	private String budgetType;
	private String budgetYear;
	private String contractDatePlan;
	private String contractDateReport;
	private String contractPartiesName;
	private String contractPartiesNum;
	private String disbursementFinalPlan;
	private String disbursementFinalReport;
	private String exciseDepartment;
	private String exciseDistrict;
	private String exciseRegion;
	private String expireDatePlan;
	private String expireDateReport;
	private String jobDescription;
	private String poNumber;
	private String projectCodeEgp;
	private String projectName;
	private String signedDateReport;
	private String signedDatePlan;
	private String status;
	private String supplyChoice;
	private String supplyType;
	private Long tenderResults;
	private String installmentjob;
	private String operationend;
	private String operationstart;
	private BigDecimal budget;
	private String respondepartment;
	
	private Long procurementListId;
	private Long amount;
	private Long appraisalPrice;
	private Long presetPrice;
	private Long price;
	private String procurementList;
	private String unit;
	private Long unitPrice;
	private String updatedDate;
	private Long procurementIdMinor;
	private List<IaProcurementList> pcmList;
	
	private String nameFile;
	private String typeFile;
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public Long getProcurementId() {
		return procurementId;
	}
	public void setProcurementId(Long procurementId) {
		this.procurementId = procurementId;
	}
	public String getApproveDatePlan() {
		return approveDatePlan;
	}
	public void setApproveDatePlan(String approveDatePlan) {
		this.approveDatePlan = approveDatePlan;
	}
	public String getApproveDateReport() {
		return approveDateReport;
	}
	public void setApproveDateReport(String approveDateReport) {
		this.approveDateReport = approveDateReport;
	}
	public String getBudgetType() {
		return budgetType;
	}
	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}
	public String getBudgetYear() {
		return budgetYear;
	}
	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}
	public String getContractDatePlan() {
		return contractDatePlan;
	}
	public void setContractDatePlan(String contractDatePlan) {
		this.contractDatePlan = contractDatePlan;
	}
	public String getContractDateReport() {
		return contractDateReport;
	}
	public void setContractDateReport(String contractDateReport) {
		this.contractDateReport = contractDateReport;
	}
	public String getContractPartiesName() {
		return contractPartiesName;
	}
	public void setContractPartiesName(String contractPartiesName) {
		this.contractPartiesName = contractPartiesName;
	}
	public String getContractPartiesNum() {
		return contractPartiesNum;
	}
	public void setContractPartiesNum(String contractPartiesNum) {
		this.contractPartiesNum = contractPartiesNum;
	}
	public String getDisbursementFinalPlan() {
		return disbursementFinalPlan;
	}
	public void setDisbursementFinalPlan(String disbursementFinalPlan) {
		this.disbursementFinalPlan = disbursementFinalPlan;
	}
	public String getDisbursementFinalReport() {
		return disbursementFinalReport;
	}
	public void setDisbursementFinalReport(String disbursementFinalReport) {
		this.disbursementFinalReport = disbursementFinalReport;
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
	public String getExpireDatePlan() {
		return expireDatePlan;
	}
	public void setExpireDatePlan(String expireDatePlan) {
		this.expireDatePlan = expireDatePlan;
	}
	public String getExpireDateReport() {
		return expireDateReport;
	}
	public void setExpireDateReport(String expireDateReport) {
		this.expireDateReport = expireDateReport;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getProjectCodeEgp() {
		return projectCodeEgp;
	}
	public void setProjectCodeEgp(String projectCodeEgp) {
		this.projectCodeEgp = projectCodeEgp;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getSignedDateReport() {
		return signedDateReport;
	}
	public void setSignedDateReport(String signedDateReport) {
		this.signedDateReport = signedDateReport;
	}
	public String getSignedDatePlan() {
		return signedDatePlan;
	}
	public void setSignedDatePlan(String signedDatePlan) {
		this.signedDatePlan = signedDatePlan;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSupplyChoice() {
		return supplyChoice;
	}
	public void setSupplyChoice(String supplyChoice) {
		this.supplyChoice = supplyChoice;
	}
	public String getSupplyType() {
		return supplyType;
	}
	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}
	public Long getTenderResults() {
		return tenderResults;
	}
	public void setTenderResults(Long tenderResults) {
		this.tenderResults = tenderResults;
	}
	public Long getProcurementListId() {
		return procurementListId;
	}
	public void setProcurementListId(Long procurementListId) {
		this.procurementListId = procurementListId;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getAppraisalPrice() {
		return appraisalPrice;
	}
	public void setAppraisalPrice(Long appraisalPrice) {
		this.appraisalPrice = appraisalPrice;
	}
	public Long getPresetPrice() {
		return presetPrice;
	}
	public void setPresetPrice(Long presetPrice) {
		this.presetPrice = presetPrice;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getProcurementList() {
		return procurementList;
	}
	public void setProcurementList(String procurementList) {
		this.procurementList = procurementList;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Long getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Long unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Long getProcurementIdMinor() {
		return procurementIdMinor;
	}
	public void setProcurementIdMinor(Long procurementIdMinor) {
		this.procurementIdMinor = procurementIdMinor;
	}
	public List<IaProcurementList> getPcmList() {
		return pcmList;
	}
	public void setPcmList(List<IaProcurementList> pcmList) {
		this.pcmList = pcmList;
	}
	public String getNameFile() {
		return nameFile;
	}
	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}
	public String getTypeFile() {
		return typeFile;
	}
	public void setTypeFile(String typeFile) {
		this.typeFile = typeFile;
	}
	public String getInstallmentjob() {
		return installmentjob;
	}
	public void setInstallmentjob(String installmentjob) {
		this.installmentjob = installmentjob;
	}
	public String getOperationend() {
		return operationend;
	}
	public void setOperationend(String operationend) {
		this.operationend = operationend;
	}
	public String getOperationstart() {
		return operationstart;
	}
	public void setOperationstart(String operationstart) {
		this.operationstart = operationstart;
	}
	public BigDecimal getBudget() {
		return budget;
	}
	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}
	public String getRespondepartment() {
		return respondepartment;
	}
	public void setRespondepartment(String respondepartment) {
		this.respondepartment = respondepartment;
	}
	
	

}