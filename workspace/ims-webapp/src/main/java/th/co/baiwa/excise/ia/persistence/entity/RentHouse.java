package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

/**
 * The persistent class for the IA_RENT_HOUSE database table.
 * 
 */
@Entity
@Table(name = "IA_RENT_HOUSE")
public class RentHouse extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5421459708607851543L;

	@Id
	@SequenceGenerator(name = "IA_RENT_HOUSE_GEN", sequenceName = "IA_RENT_HOUSE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RENT_HOUSE_GEN")
	@Column(name = "RENT_HOUSE_ID")
	private BigDecimal rentHouseId;

	private String affiliation;

	@Column(name = "BILL_AMOUNT")
	private BigDecimal billAmount;

	private String name;

	@Column(name = "NOT_OVER")
	private BigDecimal notOver;

	@Column(name = "PAYMENT_COST")
	private String paymentCost;

	@Column(name = "PAYMENT_FOR")
	private String paymentFor;

	private String period;

	@Column(name = "PERIOD_WITHDRAW")
	private String periodWithdraw;

	@Column(name = "\"POSITION\"")
	private String position;

	private String receipts;

	@Column(name = "REF_RECEIPTS")
	private BigDecimal refReceipts;

	@Column(name = "REQUEST_NO")
	private String requestNo;

	private BigDecimal salary;

	@Column(name = "TOTAL_MONTH")
	private BigDecimal totalMonth;

	@Column(name = "TOTAL_WITHDRAW")
	private BigDecimal totalWithdraw;

	public BigDecimal getRentHouseId() {
		return rentHouseId;
	}

	public void setRentHouseId(BigDecimal rentHouseId) {
		this.rentHouseId = rentHouseId;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public BigDecimal getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getNotOver() {
		return notOver;
	}

	public void setNotOver(BigDecimal notOver) {
		this.notOver = notOver;
	}

	public String getPaymentCost() {
		return paymentCost;
	}

	public void setPaymentCost(String paymentCost) {
		this.paymentCost = paymentCost;
	}

	public String getPaymentFor() {
		return paymentFor;
	}

	public void setPaymentFor(String paymentFor) {
		this.paymentFor = paymentFor;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPeriodWithdraw() {
		return periodWithdraw;
	}

	public void setPeriodWithdraw(String periodWithdraw) {
		this.periodWithdraw = periodWithdraw;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getReceipts() {
		return receipts;
	}

	public void setReceipts(String receipts) {
		this.receipts = receipts;
	}

	public BigDecimal getRefReceipts() {
		return refReceipts;
	}

	public void setRefReceipts(BigDecimal refReceipts) {
		this.refReceipts = refReceipts;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public BigDecimal getTotalMonth() {
		return totalMonth;
	}

	public void setTotalMonth(BigDecimal totalMonth) {
		this.totalMonth = totalMonth;
	}

	public BigDecimal getTotalWithdraw() {
		return totalWithdraw;
	}

	public void setTotalWithdraw(BigDecimal totalWithdraw) {
		this.totalWithdraw = totalWithdraw;
	}

}