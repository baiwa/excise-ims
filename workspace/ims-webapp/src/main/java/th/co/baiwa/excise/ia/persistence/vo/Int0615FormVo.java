package th.co.baiwa.excise.ia.persistence.vo;

import java.util.ArrayList;
import java.util.List;

public class Int0615FormVo {

	private String sector;
	private String area;
	private String startDate;
	private String endDate;
	private String money;
	private String searchFlag;

	private List<Int0615Vo> dataList = new ArrayList<>();;

	public List<Int0615Vo> getDataList() {
		return dataList;
	}

	public void setDataList(List<Int0615Vo> dataList) {
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

}
