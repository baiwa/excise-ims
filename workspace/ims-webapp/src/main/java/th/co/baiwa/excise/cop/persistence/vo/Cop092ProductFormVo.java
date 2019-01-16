package th.co.baiwa.excise.cop.persistence.vo;

import java.util.Date;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Cop092ProductFormVo extends DataTableRequest {

	private static final long serialVersionUID = 210619677378643818L;
	private String searchFlag;
	private String taMonthProductId;
	private String excise;
	private String monthBuget;
	public String getSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}
	public String getTaMonthProductId() {
		return taMonthProductId;
	}
	public void setTaMonthProductId(String taMonthProductId) {
		this.taMonthProductId = taMonthProductId;
	}
	public String getExcise() {
		return excise;
	}
	public void setExcise(String excise) {
		this.excise = excise;
	}
	public String getMonthBuget() {
		return monthBuget;
	}
	public void setMonthBuget(String monthBuget) {
		this.monthBuget = monthBuget;
	}
}
