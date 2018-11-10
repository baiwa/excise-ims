package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Int084FormVo extends DataTableRequest {

	private String searchFlag;
	private Long id;
	private Long idHead;
	private String startDate;
	private String endDate;
	private String startDateTM;
	private String endDateTM;
	private String billLost;

	private Int084Vo int084Vo;
	private List<Int084Vo> int084VoList;

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

	public Long getIdHead() {
		return idHead;
	}

	public void setIdHead(Long idHead) {
		this.idHead = idHead;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartDateTM() {
		return startDateTM;
	}

	public void setStartDateTM(String startDateTM) {
		this.startDateTM = startDateTM;
	}

	public String getEndDateTM() {
		return endDateTM;
	}

	public void setEndDateTM(String endDateTM) {
		this.endDateTM = endDateTM;
	}

	public String getBillLost() {
		return billLost;
	}

	public void setBillLost(String billLost) {
		this.billLost = billLost;
	}

	public Int084Vo getInt084Vo() {
		return int084Vo;
	}

	public void setInt084Vo(Int084Vo int084Vo) {
		this.int084Vo = int084Vo;
	}

	public List<Int084Vo> getInt084VoList() {
		return int084VoList;
	}

	public void setInt084VoList(List<Int084Vo> int084VoList) {
		this.int084VoList = int084VoList;
	}

}
