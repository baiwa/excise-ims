package th.co.baiwa.excise.cop.persistence.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Cop091FormVo extends DataTableRequest {

	private String searchFlag;
	private Long id;
	private String fiscalYear;

	private String actionPlan;
	
	private Cop0711Vo cop0711Vo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public String getActionPlan() {
		return actionPlan;
	}

	public void setActionPlan(String actionPlan) {
		this.actionPlan = actionPlan;
	}

	public Cop0711Vo getCop0711Vo() {
		return cop0711Vo;
	}

	public void setCop0711Vo(Cop0711Vo cop0711Vo) {
		this.cop0711Vo = cop0711Vo;
	}
	
	

}
