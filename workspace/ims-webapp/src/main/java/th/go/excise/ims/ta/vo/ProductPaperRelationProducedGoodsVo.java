package th.go.excise.ims.ta.vo;

import org.springframework.web.multipart.MultipartFile;

public class ProductPaperRelationProducedGoodsVo {
	private Long id;
	private String docNo;
	private String materialDesc;
	private String inputMaterialQty;
	private String formulaMaterialQty;
	private String usedMaterialQty;
	private String realUsedMaterialQty;
	private String diffMaterialQty;
	private String materialQty;
	private String goodsQty;
	private String diffGoodsQty;
	private String wasteGoodsPnt;
	private String wasteGoodsQty;
	private String balanceGoodsQty;

	private MultipartFile file;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public String getInputMaterialQty() {
		return inputMaterialQty;
	}

	public void setInputMaterialQty(String inputMaterialQty) {
		this.inputMaterialQty = inputMaterialQty;
	}

	public String getFormulaMaterialQty() {
		return formulaMaterialQty;
	}

	public void setFormulaMaterialQty(String formulaMaterialQty) {
		this.formulaMaterialQty = formulaMaterialQty;
	}

	public String getUsedMaterialQty() {
		return usedMaterialQty;
	}

	public void setUsedMaterialQty(String usedMaterialQty) {
		this.usedMaterialQty = usedMaterialQty;
	}

	public String getRealUsedMaterialQty() {
		return realUsedMaterialQty;
	}

	public void setRealUsedMaterialQty(String realUsedMaterialQty) {
		this.realUsedMaterialQty = realUsedMaterialQty;
	}

	public String getDiffMaterialQty() {
		return diffMaterialQty;
	}

	public void setDiffMaterialQty(String diffMaterialQty) {
		this.diffMaterialQty = diffMaterialQty;
	}

	public String getMaterialQty() {
		return materialQty;
	}

	public void setMaterialQty(String materialQty) {
		this.materialQty = materialQty;
	}

	public String getGoodsQty() {
		return goodsQty;
	}

	public void setGoodsQty(String goodsQty) {
		this.goodsQty = goodsQty;
	}

	public String getDiffGoodsQty() {
		return diffGoodsQty;
	}

	public void setDiffGoodsQty(String diffGoodsQty) {
		this.diffGoodsQty = diffGoodsQty;
	}

	public String getWasteGoodsPnt() {
		return wasteGoodsPnt;
	}

	public void setWasteGoodsPnt(String wasteGoodsPnt) {
		this.wasteGoodsPnt = wasteGoodsPnt;
	}

	public String getWasteGoodsQty() {
		return wasteGoodsQty;
	}

	public void setWasteGoodsQty(String wasteGoodsQty) {
		this.wasteGoodsQty = wasteGoodsQty;
	}

	public String getBalanceGoodsQty() {
		return balanceGoodsQty;
	}

	public void setBalanceGoodsQty(String balanceGoodsQty) {
		this.balanceGoodsQty = balanceGoodsQty;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
