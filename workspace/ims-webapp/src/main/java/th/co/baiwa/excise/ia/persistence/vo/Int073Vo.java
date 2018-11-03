package th.co.baiwa.excise.ia.persistence.vo;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

public class Int073Vo {

	private Long id;
	private String accountNumber;
	private String accountName;
	private BigDecimal summitTest;
	private BigDecimal debitTest;
	private BigDecimal creditTest;
	private BigDecimal liftUpTest;

	private BigDecimal debitType;
	private BigDecimal creditType;
	private BigDecimal liftUpType;

	private BigDecimal difference;

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

	public BigDecimal getSummitTest() {
		return summitTest;
	}

	public void setSummitTest(BigDecimal summitTest) {
		this.summitTest = summitTest;
	}

	public BigDecimal getDebitTest() {
		return debitTest;
	}

	public void setDebitTest(BigDecimal debitTest) {
		this.debitTest = debitTest;
	}

	public BigDecimal getCreditTest() {
		return creditTest;
	}

	public void setCreditTest(BigDecimal creditTest) {
		this.creditTest = creditTest;
	}

	public BigDecimal getLiftUpTest() {
		return liftUpTest;
	}

	public void setLiftUpTest(BigDecimal liftUpTest) {
		this.liftUpTest = liftUpTest;
	}

	public BigDecimal getDebitType() {
		return debitType;
	}

	public void setDebitType(BigDecimal debitType) {
		this.debitType = debitType;
	}

	public BigDecimal getCreditType() {
		return creditType;
	}

	public void setCreditType(BigDecimal creditType) {
		this.creditType = creditType;
	}

	public BigDecimal getLiftUpType() {
		return liftUpType;
	}

	public void setLiftUpType(BigDecimal liftUpType) {
		this.liftUpType = liftUpType;
	}

	public BigDecimal getDifference() {
		return difference;
	}

	public void setDifference(BigDecimal difference) {
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
