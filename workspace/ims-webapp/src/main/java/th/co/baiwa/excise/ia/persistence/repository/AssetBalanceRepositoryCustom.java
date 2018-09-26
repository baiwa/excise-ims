package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.entity.AssetBalance;

public interface AssetBalanceRepositoryCustom {
	
	public List<AssetBalance> findAssetBalanceByCriteria(AssetBalance assetBalance);
}
