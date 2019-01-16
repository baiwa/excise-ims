package th.co.baiwa.excise.ia.persistence.vo;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Int09FormDtlVo extends DataTableRequest {

	private Long id;
	private Long idProcess;
	private Long idDtl;
	private String name;
	private String lastName;
	private String position;
	private String type;
	private String grade;
	private String permissionDate;
	private String writeDate;
	private String departureFrom;
	private String departureTo;
	private String departureDate;
	private String returnDate;

	private Long numberDateAllowance;
	private Long numberHoursAllowance;
	private BigDecimal allowanceR;
	private BigDecimal allowanceTotal;

	private Long numberDateRoost;
	private BigDecimal roostR;
	private BigDecimal roostTotal;

	private BigDecimal passage;
	private BigDecimal otherExpenses;
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

	public Long getIdDtl() {
		return idDtl;
	}

	public void setIdDtl(Long idDtl) {
		this.idDtl = idDtl;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPermissionDate() {
		return permissionDate;
	}

	public void setPermissionDate(String permissionDate) {
		this.permissionDate = permissionDate;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getDepartureFrom() {
		return departureFrom;
	}

	public void setDepartureFrom(String departureFrom) {
		this.departureFrom = departureFrom;
	}

	public String getDepartureTo() {
		return departureTo;
	}

	public void setDepartureTo(String departureTo) {
		this.departureTo = departureTo;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public Long getNumberDateAllowance() {
		return numberDateAllowance;
	}

	public void setNumberDateAllowance(Long numberDateAllowance) {
		this.numberDateAllowance = numberDateAllowance;
	}

	public Long getNumberHoursAllowance() {
		return numberHoursAllowance;
	}

	public void setNumberHoursAllowance(Long numberHoursAllowance) {
		this.numberHoursAllowance = numberHoursAllowance;
	}

	public BigDecimal getAllowanceR() {
		return allowanceR;
	}

	public void setAllowanceR(BigDecimal allowanceR) {
		this.allowanceR = allowanceR;
	}

	public BigDecimal getAllowanceTotal() {
		return allowanceTotal;
	}

	public void setAllowanceTotal(BigDecimal allowanceTotal) {
		this.allowanceTotal = allowanceTotal;
	}

	public Long getNumberDateRoost() {
		return numberDateRoost;
	}

	public void setNumberDateRoost(Long numberDateRoost) {
		this.numberDateRoost = numberDateRoost;
	}

	public BigDecimal getRoostR() {
		return roostR;
	}

	public void setRoostR(BigDecimal roostR) {
		this.roostR = roostR;
	}

	public BigDecimal getRoostTotal() {
		return roostTotal;
	}

	public void setRoostTotal(BigDecimal roostTotal) {
		this.roostTotal = roostTotal;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
