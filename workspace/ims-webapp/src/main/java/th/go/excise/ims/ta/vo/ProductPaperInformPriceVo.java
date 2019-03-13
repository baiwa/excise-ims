package th.go.excise.ims.ta.vo;

import org.springframework.web.multipart.MultipartFile;

public class ProductPaperInformPriceVo {
	private Long id;
	private String goodsDesc;
	private String informPrice;
	private String externalPrice;
	private String declarePrice;
	private String retailPrice;
	private String taxPrice;
	private String diffPrice;
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
	public String getInformPrice() {
		return informPrice;
	}
	public void setInformPrice(String informPrice) {
		this.informPrice = informPrice;
	}
	public String getExternalPrice() {
		return externalPrice;
	}
	public void setExternalPrice(String externalPrice) {
		this.externalPrice = externalPrice;
	}
	public String getDeclarePrice() {
		return declarePrice;
	}
	public void setDeclarePrice(String declarePrice) {
		this.declarePrice = declarePrice;
	}
	public String getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
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
