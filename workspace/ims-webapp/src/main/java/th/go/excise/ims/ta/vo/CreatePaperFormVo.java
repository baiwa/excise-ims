package th.go.excise.ims.ta.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class CreatePaperFormVo extends DataTableRequest {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7030486177102512117L;
	private String startDate;
	private String stopDate;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStopDate() {
		return stopDate;
	}

	public void setStopDate(String stopDate) {
		this.stopDate = stopDate;
	}

}
