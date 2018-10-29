package th.co.baiwa.excise.epa.persistence.vo;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Epa014FormVo extends DataTableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4381772401715201590L;
	private String searchFlag;
	private String exciseId;

	public String getSearchFlag() {
		return searchFlag;
	}

	public String getExciseId() {
		return exciseId;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

}
