package th.co.baiwa.excise.ia.persistence;

import th.co.baiwa.excise.ia.persistence.entity.MoneyCheck;

public class Int0806Vo extends MoneyCheck {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7216852943087838020L;

	private String depositDateStr;
	private String statusDate;
	private String statusMoney;
	private String trnDateStr;
	private String createdDateStr;
	private String accountType;
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getDepositDateStr() {
		return depositDateStr;
	}
	public void setDepositDateStr(String depositDateStr) {
		this.depositDateStr = depositDateStr;
	}
	public String getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}
	public String getStatusMoney() {
		return statusMoney;
	}
	public void setStatusMoney(String statusMoney) {
		this.statusMoney = statusMoney;
	}
	public String getTrnDateStr() {
		return trnDateStr;
	}
	public void setTrnDateStr(String trnDateStr) {
		this.trnDateStr = trnDateStr;
	}
	public String getCreatedDateStr() {
		return createdDateStr;
	}
	public void setCreatedDateStr(String createdDateStr) {
		this.createdDateStr = createdDateStr;
	}

}
