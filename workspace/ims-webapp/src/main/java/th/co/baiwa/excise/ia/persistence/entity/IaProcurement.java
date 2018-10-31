package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the IA_PROCUREMENT database table.
 * 
 */
@Entity
@Table(name="IA_PROCUREMENT")
@NamedQuery(name="IaProcurement.findAll", query="SELECT i FROM IaProcurement i")
public class IaProcurement extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2930945659672300751L;

	@Id
	@SequenceGenerator(name="IA_PROCUREMENT_GEN", sequenceName="IA_PROCUREMENT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_PROCUREMENT_GEN")
	@Column(name="PROCUREMENT_ID")
	private Long procurementId;

	@Column(name="APPROVE_DATE_PLAN")
	private Date approveDatePlan;

	@Column(name="APPROVE_DATE_REPORT")
	private Date approveDateReport;

	private BigDecimal budget;

	@Column(name="BUDGET_TYPE")
	private String budgetType;

	@Column(name="BUDGET_YEAR")
	private String budgetYear;

	@Column(name="CONTRACT_DATE_PLAN")
	private Date contractDatePlan;

	@Column(name="CONTRACT_DATE_REPORT")
	private Date contractDateReport;

	@Column(name="CONTRACT_PARTIES_NAME")
	private String contractPartiesName;

	@Column(name="CONTRACT_PARTIES_NUM")
	private String contractPartiesNum;

	@Column(name="DISBURSEMENT_FINAL_PLAN")
	private Date disbursementFinalPlan;

	@Column(name="DISBURSEMENT_FINAL_REPORT")
	private Date disbursementFinalReport;

	@Column(name="EXCISE_DEPARTMENT")
	private String exciseDepartment;

	@Column(name="EXCISE_DISTRICT")
	private String exciseDistrict;

	@Column(name="EXCISE_REGION")
	private String exciseRegion;

	@Column(name="EXPIRE_DATE_PLAN")
	private Date expireDatePlan;

	@Column(name="EXPIRE_DATE_REPORT")
	private Date expireDateReport;

	private String installmentjob;

	@Column(name="JOB_DESCRIPTION")
	private String jobDescription;

	private Date operationend;

	private Date operationstart;

	@Column(name="PO_NUMBER")
	private String poNumber;

	@Column(name="PROJECT_CODE_EGP")
	private String projectCodeEgp;

	@Column(name="PROJECT_NAME")
	private String projectName;

	private String respondepartment;

	@Column(name="SIGNED_DATE__REPORT")
	private Date signedDateReport;

	@Column(name="SIGNED_DATE_PLAN")
	private Date signedDatePlan;

	private String status;

	@Column(name="SUPPLY_CHOICE")
	private String supplyChoice;

	@Column(name="SUPPLY_TYPE")
	private String supplyType;

	@Column(name="TENDER_RESULTS")
	private BigDecimal tenderResults;

	public Long getProcurementId() {
		return procurementId;
	}

	public void setProcurementId(Long procurementId) {
		this.procurementId = procurementId;
	}

	public Date getApproveDatePlan() {
		return approveDatePlan;
	}

	public void setApproveDatePlan(Date approveDatePlan) {
		this.approveDatePlan = approveDatePlan;
	}

	public Date getApproveDateReport() {
		return approveDateReport;
	}

	public void setApproveDateReport(Date approveDateReport) {
		this.approveDateReport = approveDateReport;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
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

	public Date getContractDatePlan() {
		return contractDatePlan;
	}

	public void setContractDatePlan(Date contractDatePlan) {
		this.contractDatePlan = contractDatePlan;
	}

	public Date getContractDateReport() {
		return contractDateReport;
	}

	public void setContractDateReport(Date contractDateReport) {
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

	public Date getDisbursementFinalPlan() {
		return disbursementFinalPlan;
	}

	public void setDisbursementFinalPlan(Date disbursementFinalPlan) {
		this.disbursementFinalPlan = disbursementFinalPlan;
	}

	public Date getDisbursementFinalReport() {
		return disbursementFinalReport;
	}

	public void setDisbursementFinalReport(Date disbursementFinalReport) {
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

	public Date getExpireDatePlan() {
		return expireDatePlan;
	}

	public void setExpireDatePlan(Date expireDatePlan) {
		this.expireDatePlan = expireDatePlan;
	}

	public Date getExpireDateReport() {
		return expireDateReport;
	}

	public void setExpireDateReport(Date expireDateReport) {
		this.expireDateReport = expireDateReport;
	}

	public String getInstallmentjob() {
		return installmentjob;
	}

	public void setInstallmentjob(String installmentjob) {
		this.installmentjob = installmentjob;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public Date getOperationend() {
		return operationend;
	}

	public void setOperationend(Date operationend) {
		this.operationend = operationend;
	}

	public Date getOperationstart() {
		return operationstart;
	}

	public void setOperationstart(Date operationstart) {
		this.operationstart = operationstart;
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

	public String getRespondepartment() {
		return respondepartment;
	}

	public void setRespondepartment(String respondepartment) {
		this.respondepartment = respondepartment;
	}

	public Date getSignedDateReport() {
		return signedDateReport;
	}

	public void setSignedDateReport(Date signedDateReport) {
		this.signedDateReport = signedDateReport;
	}

	public Date getSignedDatePlan() {
		return signedDatePlan;
	}

	public void setSignedDatePlan(Date signedDatePlan) {
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

	public BigDecimal getTenderResults() {
		return tenderResults;
	}

	public void setTenderResults(BigDecimal tenderResults) {
		this.tenderResults = tenderResults;
	}

}