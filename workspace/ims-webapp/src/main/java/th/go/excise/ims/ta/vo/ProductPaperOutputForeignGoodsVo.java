package th.go.excise.ims.ta.vo;

public class ProductPaperOutputForeignGoodsVo {
	private Long id;
	private String goodsDesc;
	private String cargoDocNo;
	private String invoice;
	private String outputDailyAccountQty;
	private String outputMonthStatementQty;
	private String outputAuditQty;
	private String taxReduceQty;
	private String diffOutputQty;
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
	public String getCargoDocNo() {
		return cargoDocNo;
	}
	public void setCargoDocNo(String cargoDocNo) {
		this.cargoDocNo = cargoDocNo;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
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
	public String getOutputAuditQty() {
		return outputAuditQty;
	}
	public void setOutputAuditQty(String outputAuditQty) {
		this.outputAuditQty = outputAuditQty;
	}
	public String getTaxReduceQty() {
		return taxReduceQty;
	}
	public void setTaxReduceQty(String taxReduceQty) {
		this.taxReduceQty = taxReduceQty;
	}
	public String getDiffOutputQty() {
		return diffOutputQty;
	}
	public void setDiffOutputQty(String diffOutputQty) {
		this.diffOutputQty = diffOutputQty;
	}

}
