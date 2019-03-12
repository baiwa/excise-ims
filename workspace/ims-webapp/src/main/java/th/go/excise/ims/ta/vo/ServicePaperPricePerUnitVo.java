package th.go.excise.ims.ta.vo;

import org.springframework.web.multipart.MultipartFile;

public class ServicePaperPricePerUnitVo {
	private String goodsDesc; //รายการ
	private String invoicePrice; //ราคาตามใบกำกับภาษี
	private String informPrice; //ราคาบริการตามแบบแจ้ง
	private String auditPrice; // จากการตรวจสอบ
	private String taxPrice; //ราคาที่ยื่นชำระภาษี
	private String diffPrice; //ผลต่าง
	private MultipartFile file;
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public String getInvoicePrice() {
		return invoicePrice;
	}
	public void setInvoicePrice(String invoicePrice) {
		this.invoicePrice = invoicePrice;
	}
	public String getInformPrice() {
		return informPrice;
	}
	public void setInformPrice(String informPrice) {
		this.informPrice = informPrice;
	}
	public String getAuditPrice() {
		return auditPrice;
	}
	public void setAuditPrice(String auditPrice) {
		this.auditPrice = auditPrice;
	}
	public String getTaxPrice() {
		return taxPrice;
	}
	public void setTaxPrice(String taxPrice) {
		this.taxPrice = taxPrice;
	}
	public String getDiffPrice() {
		return diffPrice;
	}
	public void setDiffPrice(String diffPrice) {
		this.diffPrice = diffPrice;
	}

}
