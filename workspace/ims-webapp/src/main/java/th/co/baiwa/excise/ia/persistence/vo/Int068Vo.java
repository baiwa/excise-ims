package th.co.baiwa.excise.ia.persistence.vo;

import java.math.BigDecimal;

public class Int068Vo {
	private String receiptNo;
	private String depositDateStr;
	private BigDecimal nettaxAmount;
	private BigDecimal netlocAmount;
	private String statusDate;
	private String statusMoney;

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getDepositDateStr() {
		return depositDateStr;
	}

	public void setDepositDateStr(String depositDateStr) {
		this.depositDateStr = depositDateStr;
	}

	public BigDecimal getNettaxAmount() {
		return nettaxAmount;
	}

	public void setNettaxAmount(BigDecimal nettaxAmount) {
		this.nettaxAmount = nettaxAmount;
	}

	public BigDecimal getNetlocAmount() {
		return netlocAmount;
	}

	public void setNetlocAmount(BigDecimal netlocAmount) {
		this.netlocAmount = netlocAmount;
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

}
