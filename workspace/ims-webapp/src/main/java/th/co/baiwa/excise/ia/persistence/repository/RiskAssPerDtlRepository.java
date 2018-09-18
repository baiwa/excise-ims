package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssPerDtl;


public interface RiskAssPerDtlRepository extends CommonJpaCrudRepository<RiskAssPerDtl, Long>{

	
	@Query("select r from RiskAssPerDtl r where r.isDeleted = '" + FLAG.N_FLAG + "' and r.riskHrdId = ?1")
	public List<RiskAssPerDtl> findByRiskHrdId(Long riskInfHrdId);
	
}
