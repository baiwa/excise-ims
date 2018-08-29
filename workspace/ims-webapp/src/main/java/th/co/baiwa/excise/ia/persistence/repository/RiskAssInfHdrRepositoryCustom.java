package th.co.baiwa.excise.ia.persistence.repository;


import java.util.List;

import th.co.baiwa.excise.domain.Int0802Vo;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfHdr;

public interface RiskAssInfHdrRepositoryCustom {
	
	public List<RiskAssInfHdr> findByCriteria(RiskAssInfHdr riskAssInfHdr);
	
	public List<Int0802Vo> findInfNameByBudgetYear(String budgetYear);
	public List<Int0802Vo> findData(String budgetYear, String infName);
	
	//public void updatePercent(BigDecimal percent , Long id);
	
}
