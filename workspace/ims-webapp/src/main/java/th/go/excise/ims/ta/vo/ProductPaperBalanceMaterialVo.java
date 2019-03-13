package th.go.excise.ims.ta.vo;

import org.springframework.web.multipart.MultipartFile;

public class ProductPaperBalanceMaterialVo {
	private Long id;
	private String materialDesc;
	private String balanceByAccountQty;
	private String balanceByCountQty;
	private String maxDiffQty;

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

	public String getBalanceByCountQty() {
		return balanceByCountQty;
	}

	public void setBalanceByCountQty(String balanceByCountQty) {
		this.balanceByCountQty = balanceByCountQty;
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
