package th.go.excise.ims.ta.vo;

import org.springframework.web.multipart.MultipartFile;


public class QuantityServiceVo  {
	private Long id;
	private String no;

	private String list;
	private String serviceSlip;
	private String dailyAccount;
	private String paymentSlip;
	private String fromExamination;
	private String taxForm;
	private String difference;
	private MultipartFile fileExel;
	
	public MultipartFile getFileExel() {
		return fileExel;
	}
	public void setFileExel(MultipartFile fileExel) {
		this.fileExel = fileExel;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getServiceSlip() {
		return serviceSlip;
	}
	public void setServiceSlip(String serviceSlip) {
		this.serviceSlip = serviceSlip;
	}
	public String getDailyAccount() {
		return dailyAccount;
	}
	public void setDailyAccount(String dailyAccount) {
		this.dailyAccount = dailyAccount;
	}
	public String getPaymentSlip() {
		return paymentSlip;
	}
	public void setPaymentSlip(String paymentSlip) {
		this.paymentSlip = paymentSlip;
	}
	public String getFromExamination() {
		return fromExamination;
	}
	public void setFromExamination(String fromExamination) {
		this.fromExamination = fromExamination;
	}
	public String getTaxForm() {
		return taxForm;
	}
	public void setTaxForm(String taxForm) {
		this.taxForm = taxForm;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDifference() {
		return difference;
	}
	public void setDifference(String difference) {
		this.difference = difference;
	}
	
}
