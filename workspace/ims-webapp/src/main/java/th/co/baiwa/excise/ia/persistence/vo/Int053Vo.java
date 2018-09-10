package th.co.baiwa.excise.ia.persistence.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Int053Vo implements Serializable {

	private static final long serialVersionUID = 4017714557542850248L;
	
	private String errorMessage;
	private List<Int053AssetWorkSheetVo> assetWorkSheets = new ArrayList<>();
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List<Int053AssetWorkSheetVo> getAssetWorkSheets() {
		return assetWorkSheets;
	}
	public void setAssetWorkSheets(List<Int053AssetWorkSheetVo> assetWorkSheets) {
		this.assetWorkSheets = assetWorkSheets;
	}
	
}
