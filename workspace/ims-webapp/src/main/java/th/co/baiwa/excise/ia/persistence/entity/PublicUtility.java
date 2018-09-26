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

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the IA_PUBLIC_UTILITY database table.
 * 
 */
@Entity
@Table(name="IA_PUBLIC_UTILITY")
public class PublicUtility extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 34254030790757510L;

	@Id
	@SequenceGenerator(name="IA_PUBLIC_UTILITY_GEN", sequenceName="IA_PUBLIC_UTILITY_SEQ", allocationSize  = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_PUBLIC_UTILITY_GEN")
	@Column(name="PUBLIC_UTILITY_ID")
	private long publicUtilityId;

	@Column(name="ALLOCATED_BUDGET_ID")
	private BigDecimal allocatedBudgetId;

	private BigDecimal amount;

	@Column(name="EXCISE_DEPARTMENT")
	private String exciseDepartment;

	@Column(name="EXCISE_DISTRICT")
	private String exciseDistrict;

	@Column(name="EXCISE_REGION")
	private String exciseRegion;

	@Temporal(TemporalType.DATE)
	@Column(name="INVOICE_DATE")
	private Date invoiceDate;

	@Column(name="INVOICE_NUMBER")
	private String invoiceNumber;

	@Column(name="MONTH_INVOICE")
	private String monthInvoice;

	@Column(name="PUBLIC_UTILITY_TYPE")
	private String publicUtilityType;

	@Temporal(TemporalType.DATE)
	@Column(name="WITHDRAWAL_DATE")
	private Date withdrawalDate;

	@Column(name="WITHDRAWAL_NUMBER")
	private String withdrawalNumber;

	public PublicUtility() {
	}

	public long getPublicUtilityId() {
		return this.publicUtilityId;
	}

	public void setPublicUtilityId(long publicUtilityId) {
		this.publicUtilityId = publicUtilityId;
	}

	public BigDecimal getAllocatedBudgetId() {
		return this.allocatedBudgetId;
	}

	public void setAllocatedBudgetId(BigDecimal allocatedBudgetId) {
		this.allocatedBudgetId = allocatedBudgetId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getExciseDepartment() {
		return this.exciseDepartment;
	}

	public void setExciseDepartment(String exciseDepartment) {
		this.exciseDepartment = exciseDepartment;
	}

	public String getExciseDistrict() {
		return this.exciseDistrict;
	}

	public void setExciseDistrict(String exciseDistrict) {
		this.exciseDistrict = exciseDistrict;
	}

	public String getExciseRegion() {
		return this.exciseRegion;
	}

	public void setExciseRegion(String exciseRegion) {
		this.exciseRegion = exciseRegion;
	}

	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getMonthInvoice() {
		return this.monthInvoice;
	}

	public void setMonthInvoice(String monthInvoice) {
		this.monthInvoice = monthInvoice;
	}

	public String getPublicUtilityType() {
		return this.publicUtilityType;
	}

	public void setPublicUtilityType(String publicUtilityType) {
		this.publicUtilityType = publicUtilityType;
	}

	public Date getWithdrawalDate() {
		return this.withdrawalDate;
	}

	public void setWithdrawalDate(Date withdrawalDate) {
		this.withdrawalDate = withdrawalDate;
	}

	public String getWithdrawalNumber() {
		return this.withdrawalNumber;
	}

	public void setWithdrawalNumber(String withdrawalNumber) {
		this.withdrawalNumber = withdrawalNumber;
	}

}