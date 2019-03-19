package th.go.excise.ims.ta.vo;

import org.springframework.web.multipart.MultipartFile;

public class ProductPaperInputGoodsVo {
	private Long id;
	private String goodsDesc;
	private String inputGoodsQty;
	private String inputMonthStatementQty;
	private String inputDailyAccountQty;
	private String maxDiffQty1;
	private String maxDiffQty2;
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
	public String getMaxDiffQty1() {
		return maxDiffQty1;
	}
	public void setMaxDiffQty1(String maxDiffQty1) {
		this.maxDiffQty1 = maxDiffQty1;
	}
	public String getMaxDiffQty2() {
		return maxDiffQty2;
	}
	public void setMaxDiffQty2(String maxDiffQty2) {
		this.maxDiffQty2 = maxDiffQty2;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}



}
