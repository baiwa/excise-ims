
package th.co.baiwa.excise.ta.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ta.persistence.entity.PlanCriteria;

public interface PlanCriteriaRepository extends CommonJpaCrudRepository<PlanCriteria, Long>
{
	public List<PlanCriteria> findByTaPlanTaxAuditIdOrderByTaPlanCriteriaId(Long taPlanTaxAuditId);

}
