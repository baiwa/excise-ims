package th.co.baiwa.excise.domain;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.entity.AssetBalance;
import th.co.baiwa.excise.ia.persistence.entity.AssetMaintenance;

public class Int0533Vo {
	private Long assetBalanceId;
	private AssetBalance assetBalance;
	private AssetMaintenance assetMaintenance;
	private List<AssetBalance> assetBalanceList;
	private List<AssetMaintenance> assetMaintenanceList;
	private Long day;
	private Long month;
	private Long year;
	private List<Long> assetBalanceIdList;
	
	public List<AssetBalance> getAssetBalanceList() {
		return assetBalanceList;
	}
	public void setAssetBalanceList(List<AssetBalance> assetBalanceList) {
		this.assetBalanceList = assetBalanceList;
	}
	public List<AssetMaintenance> getAssetMaintenanceList() {
		return assetMaintenanceList;
	}
	public void setAssetMaintenanceList(List<AssetMaintenance> assetMaintenanceList) {
		this.assetMaintenanceList = assetMaintenanceList;
	}
	public AssetBalance getAssetBalance() {
		return assetBalance;
	}
	public void setAssetBalance(AssetBalance assetBalance) {
		this.assetBalance = assetBalance;
	}
	public AssetMaintenance getAssetMaintenance() {
		return assetMaintenance;
	}
	public void setAssetMaintenance(AssetMaintenance assetMaintenance) {
		this.assetMaintenance = assetMaintenance;
	}
	public Long getAssetBalanceId() {
		return assetBalanceId;
	}
	public void setAssetBalanceId(Long assetBalanceId) {
		this.assetBalanceId = assetBalanceId;
	}
	public Long getDay() {
		return day;
	}
	public void setDay(Long day) {
		this.day = day;
	}
	public Long getMonth() {
		return month;
	}
	public void setMonth(Long month) {
		this.month = month;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public List<Long> getAssetBalanceIdList() {
		return assetBalanceIdList;
	}
	public void setAssetBalanceIdList(List<Long> assetBalanceIdList) {
		this.assetBalanceIdList = assetBalanceIdList;
	}
	
	
	
}
