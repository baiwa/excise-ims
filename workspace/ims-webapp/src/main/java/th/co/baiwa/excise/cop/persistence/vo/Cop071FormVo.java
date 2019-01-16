package th.co.baiwa.excise.cop.persistence.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Cop071FormVo extends DataTableRequest {

	private Long id;
	private String fiscalYear;
	private String searchFlag;

	private Cop071Vo cop071Vo;

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

	public Cop071Vo getCop071Vo() {
		return cop071Vo;
	}

	public void setCop071Vo(Cop071Vo cop071Vo) {
		this.cop071Vo = cop071Vo;
	}

}
