package th.go.excise.ims.ta.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class TaxOperatorFormVo extends DataTableRequest {

	private static final long serialVersionUID = 2104725920450722805L;
	private String dateStart;
	private String dateEnd;
	private int dateRange;
	private String draftNumber;
	private String budgetYear;

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
