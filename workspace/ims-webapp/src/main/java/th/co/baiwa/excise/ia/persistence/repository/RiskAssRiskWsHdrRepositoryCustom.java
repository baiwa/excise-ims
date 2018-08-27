package th.co.baiwa.excise.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.excise.domain.Int0801Vo;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsHdr;

public interface RiskAssRiskWsHdrRepositoryCustom {

	public List<RiskAssRiskWsHdr> findByCriteria(RiskAssRiskWsHdr riskAssRiskWsHdr);
	public List<Int0801Vo> findProjectBaseByBudgetYear(String budgetYear);
	public List<Int0801Vo> findData(String budgetYear, String projectBase , String depName);
	
	public void updatePercent(BigDecimal percent , Long id);
	
	
}
