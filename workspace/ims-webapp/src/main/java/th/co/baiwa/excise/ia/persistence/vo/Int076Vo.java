package th.co.baiwa.excise.ia.persistence.vo;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

public class Int076Vo {
	private Long id;
	private String datePosted;
	private String docNumber;
	private String docType;
	private String docRefer;
	private String actor;
	private String determination;
	private String payUnit;
	private BigDecimal debit;
	private BigDecimal credit;
	private BigDecimal liftUp;
	private String color;
	private String checkData;

	private MultipartFile fileExel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(String datePosted) {
		this.datePosted = datePosted;
	}

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocRefer() {
		return docRefer;
	}

	public void setDocRefer(String docRefer) {
		this.docRefer = docRefer;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getDetermination() {
		return determination;
	}

	public void setDetermination(String determination) {
		this.determination = determination;
	}

	public String getPayUnit() {
		return payUnit;
	}

	public void setPayUnit(String payUnit) {
		this.payUnit = payUnit;
	}

	public BigDecimal getDebit() {
		return debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public BigDecimal getLiftUp() {
		return liftUp;
	}

	public void setLiftUp(BigDecimal liftUp) {
		this.liftUp = liftUp;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
