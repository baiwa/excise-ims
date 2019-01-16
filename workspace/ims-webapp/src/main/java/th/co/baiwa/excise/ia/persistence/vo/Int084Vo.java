package th.co.baiwa.excise.ia.persistence.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Int084Vo extends DataTableRequest {

	private static final long serialVersionUID = -1085458524740446987L;

	private String searchFlag;

	private Long id;
	private Long idHead;

	private String startDate;
	private String endDate;

	private String riskNumber;
	private String riskRemark;
	private String riskPersen;
	
	private String billAll;
	private String billWaste;

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

	public Long getIdHead() {
		return idHead;
	}

	public void setIdHead(Long idHead) {
		this.idHead = idHead;
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

	public String getRiskRemark() {
		return riskRemark;
	}

	public void setRiskRemark(String riskRemark) {
		this.riskRemark = riskRemark;
	}

	public String getRiskPersen() {
		return riskPersen;
	}

	public void setRiskPersen(String riskPersen) {
		this.riskPersen = riskPersen;
	}

	public String getBillAll() {
		return billAll;
	}

	public void setBillAll(String billAll) {
		this.billAll = billAll;
	}

	public String getBillWaste() {
		return billWaste;
	}

	public void setBillWaste(String billWaste) {
		this.billWaste = billWaste;
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
