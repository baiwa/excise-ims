package th.co.baiwa.excise.ia.persistence.vo;

import java.math.BigDecimal;

public class Int061106FormVo {
	private String status;
	private String withdrawRequest;
	private BigDecimal idSelect;

	private BigDecimal id;
	private String billLading;
	private String billPay;
	private String amountPay;
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWithdrawRequest() {
		return withdrawRequest;
	}

	public void setWithdrawRequest(String withdrawRequest) {
		this.withdrawRequest = withdrawRequest;
	}

	public BigDecimal getIdSelect() {
		return idSelect;
	}

	public void setIdSelect(BigDecimal idSelect) {
		this.idSelect = idSelect;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getBillLading() {
		return billLading;
	}

	public void setBillLading(String billLading) {
		this.billLading = billLading;
	}

	public String getBillPay() {
		return billPay;
	}

	public void setBillPay(String billPay) {
		this.billPay = billPay;
	}

	public String getAmountPay() {
		return amountPay;
	}

	public void setAmountPay(String amountPay) {
		this.amountPay = amountPay;
	}

}
