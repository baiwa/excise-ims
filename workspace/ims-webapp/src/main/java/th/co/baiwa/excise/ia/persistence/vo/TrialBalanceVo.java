package th.co.baiwa.excise.ia.persistence.vo;

import java.math.BigDecimal;

public class TrialBalanceVo {
	private String accountNo;
	private String accountDes;
	private BigDecimal carryForwardAmount; // ยกไป
	private BigDecimal bringForwardAmount; // ยกมา
	private BigDecimal debit;
	private BigDecimal credit;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountDes() {
		return accountDes;
	}

	public void setAccountDes(String accountDes) {
		this.accountDes = accountDes;
	}

	public BigDecimal getCarryForwardAmount() {
		return carryForwardAmount;
	}

	public void setCarryForwardAmount(BigDecimal carryForwardAmount) {
		this.carryForwardAmount = carryForwardAmount;
	}

	public BigDecimal getBringForwardAmount() {
		return bringForwardAmount;
	}

	public void setBringForwardAmount(BigDecimal bringForwardAmount) {
		this.bringForwardAmount = bringForwardAmount;
	}

	public BigDecimal getDebit() {
		return debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

}
