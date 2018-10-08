package th.co.baiwa.excise.ia.persistence.vo;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Int06121FormVo extends DataTableRequest {

	private static final long serialVersionUID = 2605207817290157816L;
	private String accountId;
	private String accountName;
	private String searchFlag;
	private String year;
	private String yearFrom;
	private String yearTo;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getYearFrom() {
		return yearFrom;
	}

	public void setYearFrom(String yearFrom) {
		this.yearFrom = yearFrom;
	}

	public String getYearTo() {
		return yearTo;
	}

	public void setYearTo(String yearTo) {
		this.yearTo = yearTo;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}
}
