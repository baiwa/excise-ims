package th.co.baiwa.excise.ia.persistence.vo;

import java.util.ArrayList;
import java.util.List;

public class Int0614FormSearchVo {
	private String combo1;
	private String combo2;
	private String dateStart;
	private String dateEnd;
	private String withdrawAmount;
	private String name;
	private String order;
	private String sector;

	private List<Int0614FormSearchVo> dataList = new ArrayList<>();

	public List<Int0614FormSearchVo> getDataList() {
		return dataList;
	}

	public void setDataList(List<Int0614FormSearchVo> dataList) {
		this.dataList = dataList;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getCombo1() {
		return combo1;
	}

	public void setCombo1(String combo1) {
		this.combo1 = combo1;
	}

	public String getCombo2() {
		return combo2;
	}

	public void setCombo2(String combo2) {
		this.combo2 = combo2;
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

	public String getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(String withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
