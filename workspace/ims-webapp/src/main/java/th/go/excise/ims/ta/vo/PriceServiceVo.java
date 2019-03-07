package th.go.excise.ims.ta.vo;

public class PriceServiceVo {
	private String list; //รายการ
	private String taxInvoicePrice; //ราคาตามใบกำกับภาษี
	private String notificationService; //ราคาบริการตามแบบแจ้ง
	private String fromExamination; // จากการตรวจสอบ
	private String taxFilingPrice; //ราคาที่ยื่นชำระภาษี
	private String difference; //ผลต่าง
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public String getTaxInvoicePrice() {
		return taxInvoicePrice;
	}
	public void setTaxInvoicePrice(String taxInvoicePrice) {
		this.taxInvoicePrice = taxInvoicePrice;
	}
	public String getNotificationService() {
		return notificationService;
	}
	public void setNotificationService(String notificationService) {
		this.notificationService = notificationService;
	}
	public String getFromExamination() {
		return fromExamination;
	}
	public void setFromExamination(String fromExamination) {
		this.fromExamination = fromExamination;
	}
	public String getTaxFilingPrice() {
		return taxFilingPrice;
	}
	public void setTaxFilingPrice(String taxFilingPrice) {
		this.taxFilingPrice = taxFilingPrice;
	}
	public String getDifference() {
		return difference;
	}
	public void setDifference(String difference) {
		this.difference = difference;
	}
}
