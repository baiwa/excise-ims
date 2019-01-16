package th.co.baiwa.excise.ta.persistence.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class TaxHomeFormVo extends DataTableRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8098290324595614448L;
	private String type;
	private String date;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
