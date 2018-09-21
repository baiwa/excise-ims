package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.entity.AssetBalance;
import th.co.baiwa.excise.ia.persistence.repository.AssetBalanceRepository;

@Service
public class AssetBalanceService {	

	private AssetBalanceRepository iaAssetBalanceRepository;
	
	public AssetBalance findAssetBalanceById(Long id) {
		return iaAssetBalanceRepository.findOne(id);
	}
	
	public List<AssetBalance> findAllAssetBalanceById(Long id) {
		return iaAssetBalanceRepository.findAll();
	}
}
