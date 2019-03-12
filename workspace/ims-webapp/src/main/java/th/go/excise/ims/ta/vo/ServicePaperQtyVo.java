package th.go.excise.ims.ta.vo;

import org.springframework.web.multipart.MultipartFile;


public class ServicePaperQtyVo  {
	private Long id;
	private String no;

	private String goodsDesc;
	private String serviceDocNo;
	private String incomeDailyAccountAmt;
	private String paymentDocNo;
	private String auditAmt;
	private String taxAmt;
	private String diffAmt;
	private MultipartFile file;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public String getServiceDocNo() {
		return serviceDocNo;
	}
	public void setServiceDocNo(String serviceDocNo) {
		this.serviceDocNo = serviceDocNo;
	}
	public String getIncomeDailyAccountAmt() {
		return incomeDailyAccountAmt;
	}
	public void setIncomeDailyAccountAmt(String incomeDailyAccountAmt) {
		this.incomeDailyAccountAmt = incomeDailyAccountAmt;
	}
	public String getPaymentDocNo() {
		return paymentDocNo;
	}
	public void setPaymentDocNo(String paymentDocNo) {
		this.paymentDocNo = paymentDocNo;
	}
	public String getAuditAmt() {
		return auditAmt;
	}
	public void setAuditAmt(String auditAmt) {
		this.auditAmt = auditAmt;
	}
	public String getTaxAmt() {
		return taxAmt;
	}
	public void setTaxAmt(String taxAmt) {
		this.taxAmt = taxAmt;
	}
	public String getDiffAmt() {
		return diffAmt;
	}
	public void setDiffAmt(String diffAmt) {
		this.diffAmt = diffAmt;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	

}
