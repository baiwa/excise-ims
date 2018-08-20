package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsDtl;

public interface RiskAssRiskWsDtlRepository extends CommonJpaCrudRepository<RiskAssRiskWsDtl, Long> {
	
	@Query("select r from RiskAssRiskWsDtl r where r.isDeleted = '" + FLAG.N_FLAG + "' and r.riskHrdId = ?1")
	public List<RiskAssRiskWsDtl> findByGroupRiskHrdId(Long riskHrdId);
	
}
