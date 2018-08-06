package th.co.baiwa.excise.ia.persistence.repository;

import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsHdr;

@Repository
public interface RiskAssRiskWsHdrRepository extends CommonJpaCrudRepository<RiskAssRiskWsHdr, Long>, RiskAssRiskWsHdrRepositoryCustom{

}
