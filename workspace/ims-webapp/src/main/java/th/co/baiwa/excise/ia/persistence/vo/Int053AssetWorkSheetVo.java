package th.co.baiwa.excise.ia.persistence.vo;

import java.io.Serializable;

public class Int053AssetWorkSheetVo implements Serializable {

	private static final long serialVersionUID = -2296939048108788273L;
	
	private String assetWorkSheetId;
	private String accumulatedDepreciation;
	private String acquisitionValue;
	private String assetDescription;
	private String assetNumber;
	private String assetSubNumber;
	private String bookValue;
	private String fundTransferDate;
	public String getAssetWorkSheetId() {
		return assetWorkSheetId;
	}
	public void setAssetWorkSheetId(String assetWorkSheetId) {
		this.assetWorkSheetId = assetWorkSheetId;
	}
	public String getAccumulatedDepreciation() {
		return accumulatedDepreciation;
	}
	public void setAccumulatedDepreciation(String accumulatedDepreciation) {
		this.accumulatedDepreciation = accumulatedDepreciation;
	}
	public String getAcquisitionValue() {
		return acquisitionValue;
	}
	public void setAcquisitionValue(String acquisitionValue) {
		this.acquisitionValue = acquisitionValue;
	}
	public String getAssetDescription() {
		return assetDescription;
	}
	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}
	public String getAssetNumber() {
		return assetNumber;
	}
	public void setAssetNumber(String assetNumber) {
		this.assetNumber = assetNumber;
	}
	public String getAssetSubNumber() {
		return assetSubNumber;
	}
	public void setAssetSubNumber(String assetSubNumber) {
		this.assetSubNumber = assetSubNumber;
	}
	public String getBookValue() {
		return bookValue;
	}
	public void setBookValue(String bookValue) {
		this.bookValue = bookValue;
	}
	public String getFundTransferDate() {
		return fundTransferDate;
	}
	public void setFundTransferDate(String fundTransferDate) {
		this.fundTransferDate = fundTransferDate;
	}
	
}
