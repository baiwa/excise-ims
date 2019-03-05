package th.go.excise.ims.ta.vo;

public class CreatePaperProductRawMaterialReceiptVo {

	private Long id;
	private String list;
	private String invoices;
	private String dailyAccount;
	private String monthStatement;
	private String externalData;
	private String maximumDifference;

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

	public String getInvoices() {
		return invoices;
	}

	public void setInvoices(String invoices) {
		this.invoices = invoices;
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

	public String getMaximumDifference() {
		return maximumDifference;
	}

	public void setMaximumDifference(String maximumDifference) {
		this.maximumDifference = maximumDifference;
	}

}
