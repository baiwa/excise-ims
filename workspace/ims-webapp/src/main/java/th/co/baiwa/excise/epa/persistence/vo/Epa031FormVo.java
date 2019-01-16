package th.co.baiwa.excise.epa.persistence.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Epa031FormVo extends DataTableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4762183421021605117L;
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
