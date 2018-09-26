package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.entity.AssetBalance;
import th.co.baiwa.excise.ia.persistence.repository.AssetBalanceRepository;

@Service
public class AssetBalanceService {	

	@Autowired
	private AssetBalanceRepository assetBalanceRepository;
	
	public AssetBalance saveAssetBalance(AssetBalance assetBalance) {
		
		return assetBalanceRepository.save(assetBalance);
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
}
