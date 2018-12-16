
package th.co.baiwa.excise.ta.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ta.persistence.entity.PlanTaxAudit;

public interface PlanTaxAuditRepository extends CommonJpaCrudRepository<PlanTaxAudit, Long>  , PlanTaxAuditCustom
{
	public List<PlanTaxAudit> findByBudgetYearAndStatusPlan(String budgetYear , String statusPlan);
	public List<PlanTaxAudit> findByBudgetYearOrderByTaPlanTaxAuditId(String budgetYear);
	public PlanTaxAudit findByAnalysNumber(String analysNumber);

}
