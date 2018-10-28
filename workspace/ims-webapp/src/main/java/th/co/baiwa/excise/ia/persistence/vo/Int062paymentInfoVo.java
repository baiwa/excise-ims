package th.co.baiwa.excise.ia.persistence.vo;

import java.math.BigDecimal;

public class Int062paymentInfoVo {
	private Long withdrawalPersonsId;
	private Long withdrawalId;
	private String refPayment;
	private String paymentMethod;
	private String payee;
	private BigDecimal amount;
	
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
	public String getRefPayment() {
		return refPayment;
	}
	public void setRefPayment(String refPayment) {
		this.refPayment = refPayment;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
