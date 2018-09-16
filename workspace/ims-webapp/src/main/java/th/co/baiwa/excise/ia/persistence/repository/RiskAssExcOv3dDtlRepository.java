package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcOv3dDtl;

public interface RiskAssExcOv3dDtlRepository extends CommonJpaCrudRepository<RiskAssExcOv3dDtl, Long> {
	
	
	@Query("select r from RiskAssExcOv3dDtl r where r.isDeleted = '" + FLAG.N_FLAG + "' and r.riskHrdId = ?1")
	public List<RiskAssExcOv3dDtl> findByRiskHrdId(Long riskHrdId);
	
}
