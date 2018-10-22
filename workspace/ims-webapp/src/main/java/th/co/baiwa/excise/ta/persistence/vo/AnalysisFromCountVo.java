package th.co.baiwa.excise.ta.persistence.vo;

public class AnalysisFromCountVo {

	private String productionType;
	private String coordinatesFlag;
	private String formSearch;
	private String dateFrom;
	private String dateTo;

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

	public String getFormSearch() {
		return formSearch;
	}

	public void setFormSearch(String formSearch) {
		this.formSearch = formSearch;
	}

	public String getProductionType() {
		return productionType;
	}

	public void setProductionType(String productionType) {
		this.productionType = productionType;
	}

	public String getCoordinatesFlag() {
		return coordinatesFlag;
	}

	public void setCoordinatesFlag(String coordinatesFlag) {
		this.coordinatesFlag = coordinatesFlag;
	}

}
