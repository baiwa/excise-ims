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
@Table(name = "IA_WITHDRAWAL_PERSONS")
public class IaWithdrawalPersons extends BaseEntity {

	private static final long serialVersionUID = -170359794140625288L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_WITHDRAWAL_PERSONS_GEN")
	@SequenceGenerator(name = "IA_WITHDRAWAL_PERSONS_GEN", sequenceName = "IA_WITHDRAWAL_PERSONS_SEQ", allocationSize = 1)

	@Column(name = "WITHDRAWAL_PERSONS_ID")
	private Long withdrawalPersonsId;

	@Column(name = "WITHDRAWAL_ID")
	private Long withdrawalId;

	@Column(name = "OFFICE_CODE")
	private String officeCode;

	@Column(name = "OFFICE_DESC")
	private String OFFICEDesc;

	@Column(name = "PAYMENT_METHOD")
	private String paymentMethod;

	@Column(name = "REF_PAYMENT")
	private String refPayment;

	@Column(name = "PAYMENT_DATE")
	private Date paymentDate;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

	@Column(name = "PAYEE")
	private String payee;
	
	@Column(name = "BANK_NAME")
	private String bankName;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Long getWithdrawalPersonsId() {
		return withdrawalPersonsId;
	}

	public void setWithdrawalPersonsId(Long withdrawalPersonsId) {
		this.withdrawalPersonsId = withdrawalPersonsId;
	}

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

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getRefPayment() {
		return refPayment;
	}

	public void setRefPayment(String refPayment) {
		this.refPayment = refPayment;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

}
