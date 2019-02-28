package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubCapital;

public interface TaMasCondSubCapitalRepository extends CommonJpaCrudRepository<TaMasCondSubCapital, Long> {
	
	public List<TaMasCondSubCapital> findByBudgetYear(String budgetYear);
	public TaMasCondSubCapital findByBudgetYearAndDutyCode(String budgetYear, String dutyCode);

}
