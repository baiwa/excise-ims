package th.co.baiwa.excise.report.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ContractBean {

	private String numberId;
	private String loanName;
	private String loanFrom;
	private String sendTo;
	private String locateAt;
	private String positionName;
	private String presentTo;
	private String sumCostTxt;
	private String reasonTxt;
	
	private BigDecimal allowanceCost;
	private BigDecimal otherCost;
	private BigDecimal travelCost;
	private BigDecimal rentCost;
	private BigDecimal sumCost;
	private BigDecimal day;
	
	private Date dateFixed;

	public String getNumberId() {
		return numberId;
	}

	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}

	public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	public String getLoanFrom() {
		return loanFrom;
	}

	public void setLoanFrom(String loanFrom) {
		this.loanFrom = loanFrom;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public String getLocateAt() {
		return locateAt;
	}

	public void setLocateAt(String locateAt) {
		this.locateAt = locateAt;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPresentTo() {
		return presentTo;
	}

	public void setPresentTo(String presentTo) {
		this.presentTo = presentTo;
	}

	public String getSumCostTxt() {
		return sumCostTxt;
	}

	public void setSumCostTxt(String sumCostTxt) {
		this.sumCostTxt = sumCostTxt;
	}

	public BigDecimal getAllowanceCost() {
		return allowanceCost;
	}

	public void setAllowanceCost(BigDecimal allowanceCost) {
		this.allowanceCost = allowanceCost;
	}

	public BigDecimal getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(BigDecimal otherCost) {
		this.otherCost = otherCost;
	}

	public BigDecimal getTravelCost() {
		return travelCost;
	}

	public void setTravelCost(BigDecimal travelCost) {
		this.travelCost = travelCost;
	}

	public BigDecimal getRentCost() {
		return rentCost;
	}

	public void setRentCost(BigDecimal rentCost) {
		this.rentCost = rentCost;
	}

	public BigDecimal getSumCost() {
		return sumCost;
	}

	public void setSumCost(BigDecimal sumCost) {
		this.sumCost = sumCost;
	}

	public BigDecimal getDay() {
		return day;
	}

	public void setDay(BigDecimal day) {
		this.day = day;
	}

	public Date getDateFixed() {
		return dateFixed;
	}

	public void setDateFixed(Date dateFixed) {
		this.dateFixed = dateFixed;
	}

	public String getReasonTxt() {
		return reasonTxt;
	}

	public void setReasonTxt(String reasonTxt) {
		this.reasonTxt = reasonTxt;
	}

}
