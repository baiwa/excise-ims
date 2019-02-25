package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class Int030101FormVo {
	private String budgetYear;
	private BigDecimal inspectionWork;
	private String riskFactorsMaster;
	private String dataName;
	private String dateFrom;
	private String dateTo;
	private String side;
	private String riskUnit;

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

	public String getRiskFactorsMaster() {
		return riskFactorsMaster;
	}

	public void setRiskFactorsMaster(String riskFactorsMaster) {
		this.riskFactorsMaster = riskFactorsMaster;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getRiskUnit() {
		return riskUnit;
	}

	public void setRiskUnit(String riskUnit) {
		this.riskUnit = riskUnit;
	}

}
