package th.co.baiwa.excise.epa.persistence.vo;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Epa022FormVo extends DataTableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2642677762387078451L;
	private String exciseId;
	private String searchFlag;

	public String getExciseId() {
		return exciseId;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

}
