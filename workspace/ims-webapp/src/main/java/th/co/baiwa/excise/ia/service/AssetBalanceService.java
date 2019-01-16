package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.util.BeanUtils;
import th.co.baiwa.excise.ia.persistence.entity.AssetBalance;
import th.co.baiwa.excise.ia.persistence.entity.AssetMaintenance;
import th.co.baiwa.excise.ia.persistence.repository.AssetBalanceRepository;

@Service
public class AssetBalanceService {
	
	private Logger logger = LoggerFactory.getLogger(AssetBalanceService.class);

	@Autowired
	private AssetBalanceRepository assetBalanceRepository;
	
	@Autowired
	private AssetMaintenanceService assetMaintenanceService;
	
	public AssetBalance saveAssetBalance(AssetBalance assetBalance) {
		logger.info("saveAssetBalance");
		if(BeanUtils.isEmpty(assetBalance.getAssetBalanceId())) {
			logger.info("saveAssetBalance == > new");
			return assetBalanceRepository.save(assetBalance);
		}else {
			logger.info("saveAssetBalance == > update {}" , assetBalance.getAssetBalanceId());
			AssetBalance data = assetBalanceRepository.findOne(assetBalance.getAssetBalanceId());
			assetBalance.setVersion(data.getVersion());
			return assetBalanceRepository.save(assetBalance);
		}
	}
	
	public AssetBalance findAssetBalanceById(Long id) {
		return assetBalanceRepository.findOne(id);
	}
	public List<AssetBalance> findAssetBalanceByCriteria(AssetBalance assetBalance) {
		return assetBalanceRepository.findAssetBalanceByCriteria(assetBalance);
	}
	
	public List<AssetBalance> findAllAssetBalanceById(Long id) {
		return assetBalanceRepository.findAll();
	}
	
	@Transactional
	public List<AssetBalance> deleteAssetBalance(List<Long> AssetBalanceIdList) {
		List<AssetMaintenance> assetMaintenanceList;
		for (Long asset : AssetBalanceIdList) {
			assetMaintenanceList = assetMaintenanceService.findByAssetBalanceId(asset);
			assetMaintenanceService.delete(assetMaintenanceList);
		}
		assetBalanceRepository.delete(AssetBalanceIdList);
		return assetBalanceRepository.findAll();
	}
}
