package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcNocDtl;


public interface RiskAssExcNocDtlRepository extends CommonJpaCrudRepository<RiskAssExcNocDtl, Long>{

	
	@Query("select r from RiskAssExcNocDtl r where r.isDeleted = '" + FLAG.N_FLAG + "' and r.riskHrdId = ?1")
	
	public List<RiskAssExcNocDtl> findByRiskHrdId(Long riskInfHrdId);
}
