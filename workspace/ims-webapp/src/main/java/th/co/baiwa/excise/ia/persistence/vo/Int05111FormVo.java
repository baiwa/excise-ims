package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Int05111FormVo extends DataTableRequest {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3970459981525845199L;
	private String sector;
	private String area;
	private String branch;
	private String officeCode;
	private String dateForm;
	private String dateTo;
	private String status;
	private List<Int05111Vo> dataList;
	private Int0511Vo data;
	private String searchFlag;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
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

	public List<Int05111Vo> getDataList() {
		return dataList;
	}

	public void setDataList(List<Int05111Vo> dataList) {
		this.dataList = dataList;
	}

	public Int0511Vo getData() {
		return data;
	}

	public void setData(Int0511Vo data) {
		this.data = data;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

}
