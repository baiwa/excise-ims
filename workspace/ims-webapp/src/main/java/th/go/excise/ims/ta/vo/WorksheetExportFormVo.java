package th.go.excise.ims.ta.vo;

import java.util.List;

public class WorksheetExportFormVo {

	private String budgetYear;
	private int dateRange;
	private WorksheetDateRangeVo worksheetDateRangeVo;
	private List<TaxOperatorDatatableVo> taxOperatorDatatableVoList;

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public int getDateRange() {
		return dateRange;
	}

	public void setDateRange(int dateRange) {
		this.dateRange = dateRange;
	}

	public WorksheetDateRangeVo getWorksheetDateRangeVo() {
		return worksheetDateRangeVo;
	}

	public void setWorksheetDateRangeVo(WorksheetDateRangeVo worksheetDateRangeVo) {
		this.worksheetDateRangeVo = worksheetDateRangeVo;
	}

	public List<TaxOperatorDatatableVo> getTaxOperatorDatatableVoList() {
		return taxOperatorDatatableVoList;
	}

	public void setTaxOperatorDatatableVoList(List<TaxOperatorDatatableVo> taxOperatorDatatableVoList) {
		this.taxOperatorDatatableVoList = taxOperatorDatatableVoList;
	}

}
