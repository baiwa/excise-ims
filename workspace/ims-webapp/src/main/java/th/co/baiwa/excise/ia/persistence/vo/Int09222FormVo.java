package th.co.baiwa.excise.ia.persistence.vo;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;

public class Int09222FormVo extends DataTableRequest {
	/**
	 * 
	 */

	private static final long serialVersionUID = -1085458524740446987L;
	private long id;
	private long idProcess;
	private String createdDate;
	private String createdBy;

	private String stateAgencyName;
	private String thosePicked;
	private String fiscalYear;
	private String searchFlag;
	
	private Message errorMessage;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdProcess() {
		return idProcess;
	}

	public void setIdProcess(long idProcess) {
		this.idProcess = idProcess;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getStateAgencyName() {
		return stateAgencyName;
	}

	public void setStateAgencyName(String stateAgencyName) {
		this.stateAgencyName = stateAgencyName;
	}

	public String getThosePicked() {
		return thosePicked;
	}

	public void setThosePicked(String thosePicked) {
		this.thosePicked = thosePicked;
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

	public Message getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(Message errorMessage) {
		this.errorMessage = errorMessage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
