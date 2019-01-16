package th.co.baiwa.excise.ta.persistence.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class ResultAnalysisRequestvo extends DataTableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5888062159485831759L;
	private String exciseId;

	public String getExciseId() {
		return exciseId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

}
