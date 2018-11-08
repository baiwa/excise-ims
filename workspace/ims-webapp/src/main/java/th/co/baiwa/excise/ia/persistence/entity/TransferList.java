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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the IA_TRANSFER_LIST database table.
 * 
 */
@Entity
@Table(name="IA_TRANSFER_LIST")
public class TransferList extends BaseEntity {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5562545333052022524L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_TRANSFER_LIST_GEN")
	@SequenceGenerator(name="IA_TRANSFER_LIST_GEN", sequenceName="IA_TRANSFER_LIST_SEQ", allocationSize  = 1)
	@Column(name="TRANSFER_ID")
	private BigDecimal transferId;

	private String activities;

	private BigDecimal amount;

	private String budget;

	@Column(name="BUDGET_CODE")
	private String budgetCode;

	@Column(name="BUDGET_TYPE")
	private String budgetType;

	@Column(name="CTG_BUDGET")
	private String ctgBudget;

	@Column(name="DESCRIPTION_LIST")
	private String descriptionList;

	@Column(name="MOF_NUM")
	private String mofNum;

	private String note;

	@Column(name="OFFICE_CODE")
	private String officeCode;

	@Column(name="OFFICE_DESC")
	private String officeDesc;

	@Column(name="REF_DATE")
	private Date refDate;

	@Column(name="REF_NUM")
	private String refNum;

	@Column(name="SUB_CTG_BUDGET")
	private String subCtgBudget;

	@Column(name="TRANSFER_LIST")
	private String transferList;
	
	@Transient
	private String refDateStr;

	public BigDecimal getTransferId() {
		return transferId;
	}

	public void setTransferId(BigDecimal transferId) {
		this.transferId = transferId;
	}

	public String getActivities() {
		return activities;
	}

	public void setActivities(String activities) {
		this.activities = activities;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getBudgetCode() {
		return budgetCode;
	}

	public void setBudgetCode(String budgetCode) {
		this.budgetCode = budgetCode;
	}

	public String getBudgetType() {
		return budgetType;
	}

	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}

	public String getCtgBudget() {
		return ctgBudget;
	}

	public void setCtgBudget(String ctgBudget) {
		this.ctgBudget = ctgBudget;
	}

	public String getDescriptionList() {
		return descriptionList;
	}

	public void setDescriptionList(String descriptionList) {
		this.descriptionList = descriptionList;
	}

	public String getMofNum() {
		return mofNum;
	}

	public void setMofNum(String mofNum) {
		this.mofNum = mofNum;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getOfficeDesc() {
		return officeDesc;
	}

	public void setOfficeDesc(String officeDesc) {
		this.officeDesc = officeDesc;
	}

	public Date getRefDate() {
		return refDate;
	}

	public void setRefDate(Date refDate) {
		this.refDate = refDate;
	}

	public String getRefNum() {
		return refNum;
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	public String getSubCtgBudget() {
		return subCtgBudget;
	}

	public void setSubCtgBudget(String subCtgBudget) {
		this.subCtgBudget = subCtgBudget;
	}

	public String getTransferList() {
		return transferList;
	}

	public void setTransferList(String transferList) {
		this.transferList = transferList;
	}

	public String getRefDateStr() {
		return refDateStr;
	}

	public void setRefDateStr(String refDateStr) {
		this.refDateStr = refDateStr;
	}

}