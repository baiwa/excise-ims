package th.go.excise.ims.ta.vo;

public class CppRawMaterialPaymentVo {

	private Long id;
	private String list;
	private String requisition;
	private String dailyAccount;
	private String monthStatement;
	private String externalData;
	private String maxDiff;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getRequisition() {
		return requisition;
	}

	public void setRequisition(String requisition) {
		this.requisition = requisition;
	}

	public String getDailyAccount() {
		return dailyAccount;
	}

	public void setDailyAccount(String dailyAccount) {
		this.dailyAccount = dailyAccount;
	}

	public String getMonthStatement() {
		return monthStatement;
	}

	public void setMonthStatement(String monthStatement) {
		this.monthStatement = monthStatement;
	}

	public String getExternalData() {
		return externalData;
	}

	public void setExternalData(String externalData) {
		this.externalData = externalData;
	}

	public String getMaxDiff() {
		return maxDiff;
	}

	public void setMaxDiff(String maxDiff) {
		this.maxDiff = maxDiff;
	}

}
