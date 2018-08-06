package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class TravelCostWsDetail extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4184127479575824656L;
	private BigDecimal workSheetDetailId;
	private String headerId;
	private String name;
	private String lastName;
	private String position;
	private String category;
	private String degree;
	private BigDecimal allowanceDate;
	private BigDecimal allowanceCost;
	private BigDecimal rentDate;
	private BigDecimal rentCost;
	private BigDecimal travelCost;
	private BigDecimal otherCost;
	private BigDecimal sumCost;
	private String note;

	public BigDecimal getWorkSheetDetailId() {
		return workSheetDetailId;
	}

	public void setWorkSheetDetailId(BigDecimal workSheetDetailId) {
		this.workSheetDetailId = workSheetDetailId;
	}

	public String getHeaderId() {
		return headerId;
	}

	public void setHeaderId(String headerId) {
		this.headerId = headerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public BigDecimal getAllowanceDate() {
		return allowanceDate;
	}

	public void setAllowanceDate(BigDecimal allowanceDate) {
		this.allowanceDate = allowanceDate;
	}

	public BigDecimal getAllowanceCost() {
		return allowanceCost;
	}

	public void setAllowanceCost(BigDecimal allowanceCost) {
		this.allowanceCost = allowanceCost;
	}

	public BigDecimal getRentDate() {
		return rentDate;
	}

	public void setRentDate(BigDecimal rentDate) {
		this.rentDate = rentDate;
	}

	public BigDecimal getRentCost() {
		return rentCost;
	}

	public void setRentCost(BigDecimal rentCost) {
		this.rentCost = rentCost;
	}

	public BigDecimal getTravelCost() {
		return travelCost;
	}

	public void setTravelCost(BigDecimal travelCost) {
		this.travelCost = travelCost;
	}

	public BigDecimal getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(BigDecimal otherCost) {
		this.otherCost = otherCost;
	}

	public BigDecimal getSumCost() {
		return sumCost;
	}

	public void setSumCost(BigDecimal sumCost) {
		this.sumCost = sumCost;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
