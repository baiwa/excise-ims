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
	private long transferId;

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

	public TransferList() {
	}

	public long getTransferId() {
		return this.transferId;
	}

	public void setTransferId(long transferId) {
		this.transferId = transferId;
	}

	public String getActivities() {
		return this.activities;
	}

	public void setActivities(String activities) {
		this.activities = activities;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBudget() {
		return this.budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getBudgetCode() {
		return this.budgetCode;
	}

	public void setBudgetCode(String budgetCode) {
		this.budgetCode = budgetCode;
	}

	public String getBudgetType() {
		return this.budgetType;
	}

	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}

	public String getCtgBudget() {
		return this.ctgBudget;
	}

	public void setCtgBudget(String ctgBudget) {
		this.ctgBudget = ctgBudget;
	}

	public String getDescriptionList() {
		return this.descriptionList;
	}

	public void setDescriptionList(String descriptionList) {
		this.descriptionList = descriptionList;
	}

	public String getMofNum() {
		return this.mofNum;
	}

	public void setMofNum(String mofNum) {
		this.mofNum = mofNum;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOfficeCode() {
		return this.officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getOfficeDesc() {
		return this.officeDesc;
	}

	public void setOfficeDesc(String officeDesc) {
		this.officeDesc = officeDesc;
	}

	public Date getRefDate() {
		return this.refDate;
	}

	public void setRefDate(Date refDate) {
		this.refDate = refDate;
	}

	public String getRefNum() {
		return this.refNum;
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	public String getSubCtgBudget() {
		return this.subCtgBudget;
	}

	public void setSubCtgBudget(String subCtgBudget) {
		this.subCtgBudget = subCtgBudget;
	}

	public String getTransferList() {
		return this.transferList;
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