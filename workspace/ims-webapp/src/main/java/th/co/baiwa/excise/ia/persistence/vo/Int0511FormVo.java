package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Int0511FormVo extends DataTableRequest {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7737047148860962358L;
	private String sector;
	private String area;
	private String branch;
	private String dateForm;
	private String dateTo;
	private List<Int0511Vo> dataList;
	private String searchFlag;

	public List<Int0511Vo> getDataList() {
		return dataList;
	}

	public void setDataList(List<Int0511Vo> dataList) {
		this.dataList = dataList;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getDateForm() {
		return dateForm;
	}

	public void setDateForm(String dateForm) {
		this.dateForm = dateForm;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
}
