package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.IntCtrlAss;

@Repository
public interface IntCtrlAssRepository extends CommonJpaCrudRepository<IntCtrlAss, Long>{
	
	public List<IntCtrlAss> findByBudgetYearAndOfficeCode(String budgetYear , String officeCode);
	
}
