package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsHdr;

public interface RiskAssRiskWsHdrRepositoryCustom {

	public List<RiskAssRiskWsHdr> findByCriteria(RiskAssRiskWsHdr riskAssRiskWsHdr);

}
