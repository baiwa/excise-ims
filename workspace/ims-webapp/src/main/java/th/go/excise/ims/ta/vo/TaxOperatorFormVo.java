package th.go.excise.ims.ta.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class TaxOperatorFormVo extends DataTableRequest {

	private static final long serialVersionUID = 2104725920450722805L;

	private String dateStart;
	private String dateEnd;
	private int dateRange;
	private String draftNumber;
	private String budgetYear;
	private String facType;
	private String dutyCode;
	private String officeCode;
	private String analysisNumber;
	private String cond;
	private String seeDataSelect;
	private String condNumber;
	private String condSub1;
	private String condSub2;
	private String condSub3;
	private String capital;
	private String risk;
	private String cusFullname;
	private String facFullname;
	private String mainCondFreqType;
	private Integer taxMonthStart;
	private Integer taxMonthEnd;

	private String flagPage;

	public String getFlagPage() {
		return flagPage;
	}

	public void setFlagPage(String flagPage) {
		this.flagPage = flagPage;
	}

	public String getMainCondFreqType() {
		return mainCondFreqType;
	}

	public void setMainCondFreqType(String mainCondFreqType) {
		this.mainCondFreqType = mainCondFreqType;
	}

	public Integer getTaxMonthStart() {
		return taxMonthStart;
	}

	public void setTaxMonthStart(Integer taxMonthStart) {
		this.taxMonthStart = taxMonthStart;
	}

	public Integer getTaxMonthEnd() {
		return taxMonthEnd;
	}

	public void setTaxMonthEnd(Integer taxMonthEnd) {
		this.taxMonthEnd = taxMonthEnd;
	}

	public String getCusFullname() {
		return cusFullname;
	}

	public void setCusFullname(String cusFullname) {
		this.cusFullname = cusFullname;
	}

	public String getFacFullname() {
		return facFullname;
	}

	public void setFacFullname(String facFullname) {
		this.facFullname = facFullname;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getDateRange() {
		return dateRange;
	}

	public void setDateRange(int dateRange) {
		this.dateRange = dateRange;
	}

	public String getDraftNumber() {
		return draftNumber;
	}

	public void setDraftNumber(String draftNumber) {
		this.draftNumber = draftNumber;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getFacType() {
		return facType;
	}

	public void setFacType(String facType) {
		this.facType = facType;
	}

	public String getDutyCode() {
		return dutyCode;
	}

	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getAnalysisNumber() {
		return analysisNumber;
	}

	public void setAnalysisNumber(String analysisNumber) {
		this.analysisNumber = analysisNumber;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public String getSeeDataSelect() {
		return seeDataSelect;
	}

	public void setSeeDataSelect(String seeDataSelect) {
		this.seeDataSelect = seeDataSelect;
	}

	public String getCondNumber() {
		return condNumber;
	}

	public void setCondNumber(String condNumber) {
		this.condNumber = condNumber;
	}

	public String getCondSub1() {
		return condSub1;
	}

	public void setCondSub1(String condSub1) {
		this.condSub1 = condSub1;
	}

	public String getCondSub2() {
		return condSub2;
	}

	public void setCondSub2(String condSub2) {
		this.condSub2 = condSub2;
	}

	public String getCondSub3() {
		return condSub3;
	}

	public void setCondSub3(String condSub3) {
		this.condSub3 = condSub3;
	}

}
