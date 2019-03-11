package th.go.excise.ims.ta.vo;

import org.springframework.web.multipart.MultipartFile;

public class CppRawMaterialReceiveVo {

	private Long id;
	private String list;
	private String invoices;
	private String dailyAccount;
	private String monthStatement;
	private String externalData;
	private String maxDiff;

	private MultipartFile file;

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

	public String getMaxDiff() {
		return maxDiff;
	}

	public void setMaxDiff(String maxDiff) {
		this.maxDiff = maxDiff;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}



}
