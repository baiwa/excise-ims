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

@Entity
@Table(name = "IA_WITHDRAWAL_LIST")
public class IaWithdrawalList extends BaseEntity {

	private static final long serialVersionUID = 5318345592646051991L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_WITHDRAWAL_LIST_GEN")
	@SequenceGenerator(name = "IA_WITHDRAWAL_LIST_GEN", sequenceName = "IA_WITHDRAWAL_LIST_SEQ", allocationSize = 1)

	@Column(name = "WITHDRAWAL_ID")
	private Long withdrawalId;

	@Column(name = "OFFICE_CODE")
	private String officeCode;

	@Column(name = "OFFICE_DESC")
	private String OFFICEDesc;

	@Column(name = "REF_NUM")
	private String refNum;

	@Column(name = "WITHDRAWAL_DATE")
	private Date withdrawalDate;

	@Column(name = "ACTIVITIES")
	private String activities;

	@Column(name = "BUDGET_TYPE")
	private String budgetType;

	@Column(name = "WITHDRAWAL_AMOUNT")
	private BigDecimal withdrawalAmount;

	@Column(name = "SOCIAL_SECURITY")
	private BigDecimal socialSecurity;

	@Column(name = "WITHHOLDING_TAX")
	private BigDecimal withholdingTax;

	@Column(name = " RECEIVED_AMOUNT")
	private BigDecimal receivedAmount;

	@Column(name = "ANOTHER_AMOUNT")
	private BigDecimal anotherAmount;

	@Column(name = "PAYMENT_DOC_NUM")
	private String paymentDocNum;

	@Column(name = "WITHDRAWAL_DOC_NUM")
	private String withdrawalDocNum;

	@Column(name = "ITEM_DESC")
	private String itemDesc;

	@Column(name = "NOTE")
	private String note;

	@Column(name = "BUDGET_ID")
	private String budgetId;

	@Column(name = "BUDGET_NAME")
	private String budgetName;

	@Column(name = "CATEGORY_ID")
	private String categoryId;

	@Column(name = "CATEGORY_NAME")
	private String categoryName;

	@Column(name = "LIST_ID")
	private String listId;

	@Column(name = "LIST_NAME")
	private String listName;

	@Column(name = "PAYEE")
	private String payee;

	@Column(name = "PERSON_TYPE")
	private String personType;

	public Long getWithdrawalId() {
		return withdrawalId;
	}

	public void setWithdrawalId(Long withdrawalId) {
		this.withdrawalId = withdrawalId;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getOFFICEDesc() {
		return OFFICEDesc;
	}

	public void setOFFICEDesc(String oFFICEDesc) {
		OFFICEDesc = oFFICEDesc;
	}

	public String getRefNum() {
		return refNum;
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	public Date getWithdrawalDate() {
		return withdrawalDate;
	}

	public void setWithdrawalDate(Date withdrawalDate) {
		this.withdrawalDate = withdrawalDate;
	}

	public String getActivities() {
		return activities;
	}

	public void setActivities(String activities) {
		this.activities = activities;
	}

	public String getBudgetType() {
		return budgetType;
	}

	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}

	public BigDecimal getWithdrawalAmount() {
		return withdrawalAmount;
	}

	public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}

	public BigDecimal getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(BigDecimal socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public BigDecimal getWithholdingTax() {
		return withholdingTax;
	}

	public void setWithholdingTax(BigDecimal withholdingTax) {
		this.withholdingTax = withholdingTax;
	}

	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public BigDecimal getAnotherAmount() {
		return anotherAmount;
	}

	public void setAnotherAmount(BigDecimal anotherAmount) {
		this.anotherAmount = anotherAmount;
	}

	public String getPaymentDocNum() {
		return paymentDocNum;
	}

	public void setPaymentDocNum(String paymentDocNum) {
		this.paymentDocNum = paymentDocNum;
	}

	public String getWithdrawalDocNum() {
		return withdrawalDocNum;
	}

	public void setWithdrawalDocNum(String withdrawalDocNum) {
		this.withdrawalDocNum = withdrawalDocNum;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(String budgetId) {
		this.budgetId = budgetId;
	}

	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}
	
}
