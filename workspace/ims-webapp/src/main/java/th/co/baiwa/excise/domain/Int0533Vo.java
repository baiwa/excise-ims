package th.co.baiwa.excise.domain;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.entity.AssetBalance;
import th.co.baiwa.excise.ia.persistence.entity.AssetMaintenance;

public class Int0533Vo {

	private AssetBalance assetBalance;
	private AssetMaintenance assetMaintenance;
	private List<AssetBalance> assetBalanceList;
	private List<AssetMaintenance> assetMaintenanceList;
	
	
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
	
	
	
}
