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

    public String getCondNumber() {
		return condNumber;
	}

	public void setCondNumber(String condNumber) {
		this.condNumber = condNumber;
	}

	public String getSeeDataSelect() {
        return seeDataSelect;
    }

    public void setSeeDataSelect(String seeDataSelect) {
        this.seeDataSelect = seeDataSelect;
    }

    public String getCond() {
        return cond;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }

    public String getAnalysisNumber() {
        return analysisNumber;
    }

    public void setAnalysisNumber(String analysisNumber) {
        this.analysisNumber = analysisNumber;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
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

    public String getBudgetYear() {
        return budgetYear;
    }

    public void setBudgetYear(String budgetYear) {
        this.budgetYear = budgetYear;
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

}
