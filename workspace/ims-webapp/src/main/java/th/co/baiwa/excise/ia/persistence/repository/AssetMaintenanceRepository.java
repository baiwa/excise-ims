package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.AssetBalance;
import th.co.baiwa.excise.ia.persistence.entity.AssetMaintenance;

public interface AssetMaintenanceRepository extends CommonJpaCrudRepository<AssetMaintenance, Long> ,AssetMaintenanceRepositoryCustom {
	@Query("select e from AssetMaintenance e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.assetBalanceId = ?1")
	public List<AssetBalance> findByAssetBalanceId(Long assetBalanceId);
}
