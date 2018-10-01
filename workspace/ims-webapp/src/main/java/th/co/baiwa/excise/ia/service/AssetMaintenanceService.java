package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.entity.AssetMaintenance;
import th.co.baiwa.excise.ia.persistence.repository.AssetMaintenanceRepository;

@Service
public class AssetMaintenanceService {	

	@Autowired
	private AssetMaintenanceRepository assetMaintenanceRepository;

	public AssetMaintenance saveAssetMaintenance(AssetMaintenance assetMaintenance) {
		return assetMaintenanceRepository.save(assetMaintenance);
	}
	
	public AssetMaintenance findAssetMaintenanceById(Long id) {
		return assetMaintenanceRepository.findOne(id);
	}
	
	public List<AssetMaintenance> findAllAssetMaintenanceById(Long id) {
		return assetMaintenanceRepository.findAll();
	}
	public void delete(List<AssetMaintenance> assetMaintenances) {
		assetMaintenanceRepository.delete(assetMaintenances);
	}
	public int getCountByAssetBalanceId(Long assetBalanceId) {
		return assetMaintenanceRepository.getCountByAssetBalanceId(assetBalanceId);
	}
}
