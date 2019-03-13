package th.go.excise.ims.ta.vo;

import org.springframework.web.multipart.MultipartFile;

public class ProductPaperUnitPriceReduceTaxVo {
	private Long id;
	private String goodsDesc;
	private String taxReduceAmt;
	private String taxReduceQty;
	private String taxReducePerUnitAmt;
	private String billNo;
	private String billTaxAmt;
	private String billTaxQty;
	private String billTaxPerUnit;
	private String diffTaxReduceAmt;
	private MultipartFile file;
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public String getTaxReduceAmt() {
		return taxReduceAmt;
	}
	public void setTaxReduceAmt(String taxReduceAmt) {
		this.taxReduceAmt = taxReduceAmt;
	}
	public String getTaxReduceQty() {
		return taxReduceQty;
	}
	public void setTaxReduceQty(String taxReduceQty) {
		this.taxReduceQty = taxReduceQty;
	}
	public String getTaxReducePerUnitAmt() {
		return taxReducePerUnitAmt;
	}
	public void setTaxReducePerUnitAmt(String taxReducePerUnitAmt) {
		this.taxReducePerUnitAmt = taxReducePerUnitAmt;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getBillTaxAmt() {
		return billTaxAmt;
	}
	public void setBillTaxAmt(String billTaxAmt) {
		this.billTaxAmt = billTaxAmt;
	}
	public String getBillTaxQty() {
		return billTaxQty;
	}
	public void setBillTaxQty(String billTaxQty) {
		this.billTaxQty = billTaxQty;
	}
	public String getBillTaxPerUnit() {
		return billTaxPerUnit;
	}
	public void setBillTaxPerUnit(String billTaxPerUnit) {
		this.billTaxPerUnit = billTaxPerUnit;
	}
	public String getDiffTaxReduceAmt() {
		return diffTaxReduceAmt;
	}
	public void setDiffTaxReduceAmt(String diffTaxReduceAmt) {
		this.diffTaxReduceAmt = diffTaxReduceAmt;
	}

	

}
