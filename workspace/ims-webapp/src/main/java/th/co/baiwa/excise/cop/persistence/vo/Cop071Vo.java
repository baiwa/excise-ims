package th.co.baiwa.excise.cop.persistence.vo;

import java.util.Date;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Cop071Vo extends DataTableRequest {

	private static final long serialVersionUID = -1085458524740446987L;

	private String searchFlag;

	private Long id;
	private String fiscalYear;
	private String fiscalYearText;
	private Integer asPlanNumber;
	private Integer asPlanSuccess;
	private Integer asPlanWait;
	private Integer outsidePlanNumber;
	private Integer outsidePlanSuccess;
	private Integer outsidePlanWait;

	private Date createdDate;
	private String createdBy;
	private Date updateDate;
	private String updateBy;

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

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

	public String getFiscalYearText() {
		return fiscalYearText;
	}

	public void setFiscalYearText(String fiscalYearText) {
		this.fiscalYearText = fiscalYearText;
	}

	public Integer getAsPlanNumber() {
		return asPlanNumber;
	}

	public void setAsPlanNumber(Integer asPlanNumber) {
		this.asPlanNumber = asPlanNumber;
	}

	public Integer getAsPlanSuccess() {
		return asPlanSuccess;
	}

	public void setAsPlanSuccess(Integer asPlanSuccess) {
		this.asPlanSuccess = asPlanSuccess;
	}

	public Integer getAsPlanWait() {
		return asPlanWait;
	}

	public void setAsPlanWait(Integer asPlanWait) {
		this.asPlanWait = asPlanWait;
	}

	public Integer getOutsidePlanNumber() {
		return outsidePlanNumber;
	}

	public void setOutsidePlanNumber(Integer outsidePlanNumber) {
		this.outsidePlanNumber = outsidePlanNumber;
	}

	public Integer getOutsidePlanSuccess() {
		return outsidePlanSuccess;
	}

	public void setOutsidePlanSuccess(Integer outsidePlanSuccess) {
		this.outsidePlanSuccess = outsidePlanSuccess;
	}

	public Integer getOutsidePlanWait() {
		return outsidePlanWait;
	}

	public void setOutsidePlanWait(Integer outsidePlanWait) {
		this.outsidePlanWait = outsidePlanWait;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
