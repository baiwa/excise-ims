package th.co.baiwa.excise.ia.persistence.repository;

import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcAreaHdr;

@Repository
public interface RiskAssExcAreaHdrRepository extends CommonJpaCrudRepository<RiskAssExcAreaHdr, Long>, RiskAssExcAreaHdrRepositoryCustom{
	
	
	
	
}
