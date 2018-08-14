package th.co.baiwa.excise.ia.persistence.vo;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Int09211FormVo extends DataTableRequest {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1085458524740446987L;
	private String year;
	private String department;
	private String searchFlag;

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
