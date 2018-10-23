package th.co.baiwa.excise.epa.persistence.vo;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Epa011FormVo extends DataTableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288719590800487594L;
	private String exciseId;
	private String exportDate;
	private String searchFlag;

	public String getExciseId() {
		return exciseId;
	}

	public String getExportDate() {
		return exportDate;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public void setExportDate(String exportDate) {
		this.exportDate = exportDate;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

}
