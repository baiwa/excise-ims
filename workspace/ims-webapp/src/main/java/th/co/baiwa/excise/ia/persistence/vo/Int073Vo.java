package th.co.baiwa.excise.ia.persistence.vo;

import org.springframework.web.multipart.MultipartFile;

public class Int073Vo {

	private Long id;
	private String accountNumber;
	private String accountName;
	private String SummitTest;
	private String debitTest;
	private String creditTest;
	private String liftUpTest;

	private String debitType;
	private String creditType;
	private String liftUpType;

	private String difference;
	private String checkData;
	
	private MultipartFile fileExel;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getSummitTest() {
		return SummitTest;
	}

	public void setSummitTest(String summitTest) {
		SummitTest = summitTest;
	}

	public String getDebitTest() {
		return debitTest;
	}

	public void setDebitTest(String debitTest) {
		this.debitTest = debitTest;
	}

	public String getCreditTest() {
		return creditTest;
	}

	public void setCreditTest(String creditTest) {
		this.creditTest = creditTest;
	}

	public String getLiftUpTest() {
		return liftUpTest;
	}

	public void setLiftUpTest(String liftUpTest) {
		this.liftUpTest = liftUpTest;
	}

	public String getDebitType() {
		return debitType;
	}

	public void setDebitType(String debitType) {
		this.debitType = debitType;
	}

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public String getLiftUpType() {
		return liftUpType;
	}

	public void setLiftUpType(String liftUpType) {
		this.liftUpType = liftUpType;
	}

	public String getDifference() {
		return difference;
	}

	public void setDifference(String difference) {
		this.difference = difference;
	}

	public String getCheckData() {
		return checkData;
	}

	public void setCheckData(String checkData) {
		this.checkData = checkData;
	}

	public MultipartFile getFileExel() {
		return fileExel;
	}

	public void setFileExel(MultipartFile fileExel) {
		this.fileExel = fileExel;
	}

	
}
