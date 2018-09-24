package th.co.baiwa.excise.ia.persistence.vo;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Int0161FormVo extends DataTableRequest {

	private String searchFlag;
	
	private Long id;
	private String nid;
	private String newregId;
	private String pageNo;
	private String dataPerPage;

	private int yearForm;
	private int yearTo;

	
	
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

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getNewregId() {
		return newregId;
	}

	public void setNewregId(String newregId) {
		this.newregId = newregId;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getDataPerPage() {
		return dataPerPage;
	}

	public void setDataPerPage(String dataPerPage) {
		this.dataPerPage = dataPerPage;
	}

	public int getYearForm() {
		return yearForm;
	}

	public void setYearForm(int yearForm) {
		this.yearForm = yearForm;
	}

	public int getYearTo() {
		return yearTo;
	}

	public void setYearTo(int yearTo) {
		this.yearTo = yearTo;
	}

}
