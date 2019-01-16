package th.co.baiwa.excise.cop.persistence.vo;

import java.util.Date;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Cop092ProductVo extends DataTableRequest {

	private static final long serialVersionUID = -1208115976906296496L;
	private String taMonthProductId;
	private String excise;
	private Date monthBudget;
	private String list;
	private String unit;
	private String stock;
	private String getPro;
	private String receive;
	private String other;
	private String receiveTotal;
	private String domDist;
	private String foreignSale;
	private String inHouseUse;
	private String warehouse;
	private String corrupt;
	private String other1;
	private String total;
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
	public Date getMonthBudget() {
		return monthBudget;
	}
	public void setMonthBudget(Date monthBudget) {
		this.monthBudget = monthBudget;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getGetPro() {
		return getPro;
	}
	public void setGetPro(String getPro) {
		this.getPro = getPro;
	}
	public String getReceive() {
		return receive;
	}
	public void setReceive(String receive) {
		this.receive = receive;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getReceiveTotal() {
		return receiveTotal;
	}
	public void setReceiveTotal(String receiveTotal) {
		this.receiveTotal = receiveTotal;
	}
	public String getDomDist() {
		return domDist;
	}
	public void setDomDist(String domDist) {
		this.domDist = domDist;
	}
	public String getForeignSale() {
		return foreignSale;
	}
	public void setForeignSale(String foreignSale) {
		this.foreignSale = foreignSale;
	}
	public String getInHouseUse() {
		return inHouseUse;
	}
	public void setInHouseUse(String inHouseUse) {
		this.inHouseUse = inHouseUse;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getCorrupt() {
		return corrupt;
	}
	public void setCorrupt(String corrupt) {
		this.corrupt = corrupt;
	}
	public String getOther1() {
		return other1;
	}
	public void setOther1(String other1) {
		this.other1 = other1;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
}
