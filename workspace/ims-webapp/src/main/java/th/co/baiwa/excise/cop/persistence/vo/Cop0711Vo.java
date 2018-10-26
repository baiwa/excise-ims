package th.co.baiwa.excise.cop.persistence.vo;

import java.util.Date;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Cop0711Vo extends DataTableRequest {

	private static final long serialVersionUID = -1085458524740446987L;

	private String searchFlag;

	private Long id;
	private Long idMaster;
	private String fiscalyear;
	private String entrepreneurNo;
	private String entrepreneurName;
	private String entrepreneurLoca;
	private String checkDate;
	private String actionPlan;
	private String status;

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

	public Long getIdMaster() {
		return idMaster;
	}

	public void setIdMaster(Long idMaster) {
		this.idMaster = idMaster;
	}

	public String getFiscalyear() {
		return fiscalyear;
	}

	public void setFiscalyear(String fiscalyear) {
		this.fiscalyear = fiscalyear;
	}

	public String getEntrepreneurNo() {
		return entrepreneurNo;
	}

	public void setEntrepreneurNo(String entrepreneurNo) {
		this.entrepreneurNo = entrepreneurNo;
	}

	public String getEntrepreneurName() {
		return entrepreneurName;
	}

	public void setEntrepreneurName(String entrepreneurName) {
		this.entrepreneurName = entrepreneurName;
	}

	public String getEntrepreneurLoca() {
		return entrepreneurLoca;
	}

	public void setEntrepreneurLoca(String entrepreneurLoca) {
		this.entrepreneurLoca = entrepreneurLoca;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getActionPlan() {
		return actionPlan;
	}

	public void setActionPlan(String actionPlan) {
		this.actionPlan = actionPlan;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
