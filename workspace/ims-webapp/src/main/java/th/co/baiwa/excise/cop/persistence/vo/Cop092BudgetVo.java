package th.co.baiwa.excise.cop.persistence.vo;

import java.util.Date;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Cop092BudgetVo extends DataTableRequest {
	
	private static final long serialVersionUID = -3847144759386374430L;
	private String taMonthBudgetId;
	private String exciseId;
	private Date monthBudget;
	private String list;
	private String unit;
	private String stock;
	private String receive;
	private String receiveTotal;
	private String productInLine;
	private String productOutLine;
	private String corrupt;
	private String other;
	public String getTaMonthBudgetId() {
		return taMonthBudgetId;
	}
	public void setTaMonthBudgetId(String taMonthBudgetId) {
		this.taMonthBudgetId = taMonthBudgetId;
	}
	public String getExciseId() {
		return exciseId;
	}
	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
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
	public String getReceive() {
		return receive;
	}
	public void setReceive(String receive) {
		this.receive = receive;
	}
	public String getReceiveTotal() {
		return receiveTotal;
	}
	public void setReceiveTotal(String receiveTotal) {
		this.receiveTotal = receiveTotal;
	}
	public String getProductInLine() {
		return productInLine;
	}
	public void setProductInLine(String productInLine) {
		this.productInLine = productInLine;
	}
	public String getProductOutLine() {
		return productOutLine;
	}
	public void setProductOutLine(String productOutLine) {
		this.productOutLine = productOutLine;
	}
	public String getCorrupt() {
		return corrupt;
	}
	public void setCorrupt(String corrupt) {
		this.corrupt = corrupt;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
}
