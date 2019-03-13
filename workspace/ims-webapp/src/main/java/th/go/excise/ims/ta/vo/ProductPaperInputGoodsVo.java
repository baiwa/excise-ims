package th.go.excise.ims.ta.vo;

import org.springframework.web.multipart.MultipartFile;

public class ProductPaperInputGoodsVo {
	private Long id;
	private String goodsDesc;
	private String inputGoodsQty;
	private String inputMonthStatementQty;
	private String inputDailyAccountQty;
	private String maxDiffQty;

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

	public String getInputGoodsQty() {
		return inputGoodsQty;
	}

	public void setInputGoodsQty(String inputGoodsQty) {
		this.inputGoodsQty = inputGoodsQty;
	}

	public String getInputMonthStatementQty() {
		return inputMonthStatementQty;
	}

	public void setInputMonthStatementQty(String inputMonthStatementQty) {
		this.inputMonthStatementQty = inputMonthStatementQty;
	}

	public String getInputDailyAccountQty() {
		return inputDailyAccountQty;
	}

	public void setInputDailyAccountQty(String inputDailyAccountQty) {
		this.inputDailyAccountQty = inputDailyAccountQty;
	}

	public String getMaxDiffQty() {
		return maxDiffQty;
	}

	public void setMaxDiffQty(String maxDiffQty) {
		this.maxDiffQty = maxDiffQty;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
