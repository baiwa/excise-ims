package th.co.baiwa.excise.epa.persistence.vo;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Epa012FormVo extends DataTableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 789989652574034396L;
	private String exciseId;

	public String getExciseId() {
		return exciseId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

}
