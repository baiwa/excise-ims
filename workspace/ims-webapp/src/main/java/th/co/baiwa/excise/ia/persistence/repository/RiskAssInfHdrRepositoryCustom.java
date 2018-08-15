package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfHdr;

public interface RiskAssInfHdrRepositoryCustom {
	
	public List<RiskAssInfHdr> findByCriteria(RiskAssInfHdr riskAssInfHdr);
}
