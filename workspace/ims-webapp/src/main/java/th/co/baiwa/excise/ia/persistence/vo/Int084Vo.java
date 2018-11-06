package th.co.baiwa.excise.ia.persistence.vo;

import java.util.Date;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Int084Vo extends DataTableRequest {

	private static final long serialVersionUID = -1085458524740446987L;

	private String searchFlag;

	private Long id;

	private String startDate;
	private String endDate;

	private String riskNumber;
	private String riskList;

	private String officeCode;
	private String officeName;
	private String risk;
	private String origin;
	private String riskScore;

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRiskNumber() {
		return riskNumber;
	}

	public void setRiskNumber(String riskNumber) {
		this.riskNumber = riskNumber;
	}

	public String getRiskList() {
		return riskList;
	}

	public void setRiskList(String riskList) {
		this.riskList = riskList;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getRiskScore() {
		return riskScore;
	}

	public void setRiskScore(String riskScore) {
		this.riskScore = riskScore;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
