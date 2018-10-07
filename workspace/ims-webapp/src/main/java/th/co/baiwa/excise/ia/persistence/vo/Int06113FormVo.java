package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Int06113FormVo extends DataTableRequest {

	private static final long serialVersionUID = -2931078995199121213L;
	private String year;
	private String sector;
	private String area;
	private String searchFlag = "FALSE";
	private List<Int0611ExcelVo> dataBudget;
	private List<Int06112ExcelVo> dataLedger;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public List<Int0611ExcelVo> getDataBudget() {
		return dataBudget;
	}

	public void setDataBudget(List<Int0611ExcelVo> dataBudget) {
		this.dataBudget = dataBudget;
	}

	public List<Int06112ExcelVo> getDataLedger() {
		return dataLedger;
	}

	public void setDataLedger(List<Int06112ExcelVo> dataLedger) {
		this.dataLedger = dataLedger;
	}
}
