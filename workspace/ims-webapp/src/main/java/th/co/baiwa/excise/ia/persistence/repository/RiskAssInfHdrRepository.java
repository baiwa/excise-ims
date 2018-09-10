package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfHdr;


public interface RiskAssInfHdrRepository extends CommonJpaCrudRepository<RiskAssInfHdr, Long>  ,RiskAssInfHdrRepositoryCustom{
	
	@Query("SELECT r FROM RiskAssInfHdr r WHERE r.isDeleted = '"+FLAG.N_FLAG+"' AND r.budgetYear =  ?1")
	public List<RiskAssInfHdr> findByBudgetYear(String budgetYear);
	
	@Query("SELECT r FROM RiskAssInfHdr r WHERE r.isDeleted = '"+FLAG.N_FLAG+"' AND r.budgetYear =  ?1 AND r.active = ?2")
	public List<RiskAssInfHdr> findByBudgetYearActive(String budgetYear , String active);
	
}
