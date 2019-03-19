package th.go.excise.ims.ta.vo;

import org.springframework.web.multipart.MultipartFile;

public class ProductPaperBalanceMaterialVo {
	private Long id;
	private String materialDesc;
	private String balanceByAccountQty;
	private String balanceByStock;
	private String balanceByCountQty;
	private String maxDiffQty1;
	private String maxDiffQty2;

	private MultipartFile file;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public String getBalanceByAccountQty() {
		return balanceByAccountQty;
	}

	public void setBalanceByAccountQty(String balanceByAccountQty) {
		this.balanceByAccountQty = balanceByAccountQty;
	}

	public String getBalanceByStock() {
		return balanceByStock;
	}

	public void setBalanceByStock(String balanceByStock) {
		this.balanceByStock = balanceByStock;
	}

	public String getBalanceByCountQty() {
		return balanceByCountQty;
	}

	public void setBalanceByCountQty(String balanceByCountQty) {
		this.balanceByCountQty = balanceByCountQty;
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
