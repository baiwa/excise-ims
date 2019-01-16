package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Int0610FormVo extends DataTableRequest {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 20233718295487355L;
	private String activity;
	private String budge;
	private String searchFlag;
	private String dateForm;
	private String dateTo;
	private String OfficeCode;
	private String secter;
	private String area;
	private String branch;
	private List<Int0610Vo> dataList;
	private Int0610Vo data;
	
	


	public Int0610Vo getData() {
		return data;
	}

	public void setData(Int0610Vo data) {
		this.data = data;
	}

	public List<Int0610Vo> getDataList() {
		return dataList;
	}

	public void setDataList(List<Int0610Vo> dataList) {
		this.dataList = dataList;
	}

	public String getSecter() {
		return secter;
	}

	public void setSecter(String secter) {
		this.secter = secter;
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

	public String getActivity() {
		return activity;
	}
	
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public String getBudge() {
		return budge;
	}
	
	public void setBudge(String budge) {
		this.budge = budge;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
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

	public String getOfficeCode() {
		return OfficeCode;
	}

	public void setOfficeCode(String officeCode) {
		OfficeCode = officeCode;
	}

}
