package th.co.baiwa.excise.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.excise.domain.Int0803Vo;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcAreaHdr;

public interface RiskAssExcAreaHdrRepositoryCustom {

	public List<RiskAssExcAreaHdr> findByCriteria(RiskAssExcAreaHdr riskAssRiskWsHdr);
	public List<Int0803Vo> findProjectBaseByBudgetYear(String budgetYear);
	public List<Int0803Vo> findData(String budgetYear,  String depName);
	
	public void updatePercent(BigDecimal percent , Long id);
	public List<RiskAssExcAreaHdr> findRiskAssExcAreaHdrByCriteria(RiskAssExcAreaHdr riskAssExcAreaHdr);
	
	
}
