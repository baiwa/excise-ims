package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.BudgetList;

public interface BudgetListRepository extends CommonJpaCrudRepository<BudgetList, Long> {

	@Query(
			value = "select * from IA_BUDGET_LIST L",
			nativeQuery = true
	)
	public List<BudgetList> quryBudgetName();
	
	List<BudgetList> findDistinctByBudgetId(String budgetId);

	
	
}
