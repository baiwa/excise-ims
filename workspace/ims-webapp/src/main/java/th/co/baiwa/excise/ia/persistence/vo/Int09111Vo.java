package th.co.baiwa.excise.ia.persistence.vo;

import java.math.BigDecimal;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Int09111Vo extends DataTableRequest {

	private Long id;
	private Long idProcess;
	private String name;
	private String position;
	private BigDecimal feedDay;
	private BigDecimal feedMoney;
	private BigDecimal roostDay;
	private BigDecimal roostMoney;
	private BigDecimal passage;
	private BigDecimal otherExpenses;
	private BigDecimal totalMoney;
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdProcess() {
		return idProcess;
	}

	public void setIdProcess(Long idProcess) {
		this.idProcess = idProcess;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public BigDecimal getFeedDay() {
		return feedDay;
	}

	public void setFeedDay(BigDecimal feedDay) {
		this.feedDay = feedDay;
	}

	public BigDecimal getFeedMoney() {
		return feedMoney;
	}

	public void setFeedMoney(BigDecimal feedMoney) {
		this.feedMoney = feedMoney;
	}

	public BigDecimal getRoostDay() {
		return roostDay;
	}

	public void setRoostDay(BigDecimal roostDay) {
		this.roostDay = roostDay;
	}

	public BigDecimal getRoostMoney() {
		return roostMoney;
	}

	public void setRoostMoney(BigDecimal roostMoney) {
		this.roostMoney = roostMoney;
	}

	public BigDecimal getPassage() {
		return passage;
	}

	public void setPassage(BigDecimal passage) {
		this.passage = passage;
	}

	public BigDecimal getOtherExpenses() {
		return otherExpenses;
	}

	public void setOtherExpenses(BigDecimal otherExpenses) {
		this.otherExpenses = otherExpenses;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
