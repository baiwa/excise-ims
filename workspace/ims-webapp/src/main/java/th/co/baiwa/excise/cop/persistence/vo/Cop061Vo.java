package th.co.baiwa.excise.cop.persistence.vo;

import java.math.BigDecimal;

public class Cop061Vo {

	private String taExciseAcc0502DtlId;
	private String exciseId;
	private String taExciseAcc0502DtlList;
	private BigDecimal taxAmount;
	private BigDecimal amount;
	private BigDecimal taxPerAmount;
	private String receiptNumber;
	private String taxNumber;
	private String volume;
	private BigDecimal unit;

	public String getTaExciseAcc0502DtlId() {
		return taExciseAcc0502DtlId;
	}

	public void setTaExciseAcc0502DtlId(String taExciseAcc0502DtlId) {
		this.taExciseAcc0502DtlId = taExciseAcc0502DtlId;
	}

	public String getExciseId() {
		return exciseId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public String getTaExciseAcc0502DtlList() {
		return taExciseAcc0502DtlList;
	}

	public void setTaExciseAcc0502DtlList(String taExciseAcc0502DtlList) {
		this.taExciseAcc0502DtlList = taExciseAcc0502DtlList;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getTaxPerAmount() {
		return taxPerAmount;
	}

	public void setTaxPerAmount(BigDecimal taxPerAmount) {
		this.taxPerAmount = taxPerAmount;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public BigDecimal getUnit() {
		return unit;
	}

	public void setUnit(BigDecimal unit) {
		this.unit = unit;
	}

}
