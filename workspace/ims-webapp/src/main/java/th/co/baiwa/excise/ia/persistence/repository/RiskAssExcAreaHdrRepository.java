package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcAreaHdr;

@Repository
public interface RiskAssExcAreaHdrRepository extends CommonJpaCrudRepository<RiskAssExcAreaHdr, Long>, RiskAssExcAreaHdrRepositoryCustom{
	
	@Query("SELECT r FROM RiskAssExcAreaHdr r WHERE r.isDeleted = '"+FLAG.N_FLAG+"' AND r.budgetYear =  ?1")
	public List<RiskAssExcAreaHdr> findByBudgetYear(String budgetYear);
	
}
