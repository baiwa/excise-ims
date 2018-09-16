package th.co.baiwa.excise.ia.persistence.vo;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Int09TableDtlVo extends DataTableRequest {

	private Long id;
	private Long idProcess;
	private String documentType;
	private String name;
	private String position;
	private Long feedDay;
	private Long feedMoney;
	private Long roostDay;
	private Long roostMoney;
	private Long passage;
	private Long otherExpenses;
	private Long totalMoney;
	private String remark;

	private Int09FormDtlVo int09FormDtlVo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdProcess() {
		return idProcess;
	}

	public void setIdProcess(Long idProcess) {
		this.idProcess = idProcess;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Long getFeedDay() {
		return feedDay;
	}

	public void setFeedDay(Long feedDay) {
		this.feedDay = feedDay;
	}

	public Long getFeedMoney() {
		return feedMoney;
	}

	public void setFeedMoney(Long feedMoney) {
		this.feedMoney = feedMoney;
	}

	public Long getRoostDay() {
		return roostDay;
	}

	public void setRoostDay(Long roostDay) {
		this.roostDay = roostDay;
	}

	public Long getRoostMoney() {
		return roostMoney;
	}

	public void setRoostMoney(Long roostMoney) {
		this.roostMoney = roostMoney;
	}

	public Long getPassage() {
		return passage;
	}

	public void setPassage(Long passage) {
		this.passage = passage;
	}

	public Long getOtherExpenses() {
		return otherExpenses;
	}

	public void setOtherExpenses(Long otherExpenses) {
		this.otherExpenses = otherExpenses;
	}

	public Long getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Long totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Int09FormDtlVo getInt09FormDtlVo() {
		return int09FormDtlVo;
	}

	public void setInt09FormDtlVo(Int09FormDtlVo int09FormDtlVo) {
		this.int09FormDtlVo = int09FormDtlVo;
	}

	

}
