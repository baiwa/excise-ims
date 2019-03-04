package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.List;

public class Int0401Vo {
	private BigDecimal id;
	private String projectName;
	private String exciseCode;
	private String sectorName;
	private String areaName;
	private String budgetYear;
	private BigDecimal inspectionWork;
	private String status;
	private List<Int0401ListVo> lists;
	private BigDecimal riskItem;
	private BigDecimal riskRate;
	private String riskText;

	public BigDecimal getRiskItem() {
		return riskItem;
	}

	public void setRiskItem(BigDecimal riskItem) {
		this.riskItem = riskItem;
	}

	public BigDecimal getRiskRate() {
		return riskRate;
	}

	public void setRiskRate(BigDecimal riskRate) {
		this.riskRate = riskRate;
	}

	public String getRiskText() {
		return riskText;
	}

	public void setRiskText(String riskText) {
		this.riskText = riskText;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getExciseCode() {
		return exciseCode;
	}

	public void setExciseCode(String exciseCode) {
		this.exciseCode = exciseCode;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public List<Int0401ListVo> getLists() {
		return lists;
	}

	public void setLists(List<Int0401ListVo> lists) {
		this.lists = lists;
	}

}
