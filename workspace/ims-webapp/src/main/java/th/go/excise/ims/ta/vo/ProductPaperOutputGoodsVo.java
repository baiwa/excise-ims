package th.go.excise.ims.ta.vo;

import org.springframework.web.multipart.MultipartFile;

public class ProductPaperOutputGoodsVo {

	private Long id;
	private String goodsDesc;
	private String outputGoodsQty;
	private String outputDailyAccountQty;
	private String outputMonthStatementQty;
	private String auditQty;
	private String taxGoodsQty;
	private String diffQty;

	private MultipartFile file;

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

	public String getOutputGoodsQty() {
		return outputGoodsQty;
	}

	public void setOutputGoodsQty(String outputGoodsQty) {
		this.outputGoodsQty = outputGoodsQty;
	}

	public String getOutputDailyAccountQty() {
		return outputDailyAccountQty;
	}

	public void setOutputDailyAccountQty(String outputDailyAccountQty) {
		this.outputDailyAccountQty = outputDailyAccountQty;
	}

	public String getOutputMonthStatementQty() {
		return outputMonthStatementQty;
	}

	public void setOutputMonthStatementQty(String outputMonthStatementQty) {
		this.outputMonthStatementQty = outputMonthStatementQty;
	}

	public String getAuditQty() {
		return auditQty;
	}

	public void setAuditQty(String auditQty) {
		this.auditQty = auditQty;
	}

	public String getTaxGoodsQty() {
		return taxGoodsQty;
	}

	public void setTaxGoodsQty(String taxGoodsQty) {
		this.taxGoodsQty = taxGoodsQty;
	}

	public String getDiffQty() {
		return diffQty;
	}

	public void setDiffQty(String diffQty) {
		this.diffQty = diffQty;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
