package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;


public class Int030102FormVo {
	private BigDecimal id;
	private BigDecimal idHead;
	private String budgetYear;
	private BigDecimal inspectionWork;
	private String status;
	

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getIdHead() {
		return idHead;
	}

	public void setIdHead(BigDecimal idHead) {
		this.idHead = idHead;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public BigDecimal getInspectionWork() {
		return inspectionWork;
	}

	public void setInspectionWork(BigDecimal inspectionWork) {
		this.inspectionWork = inspectionWork;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}