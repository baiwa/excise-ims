
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRiskSystemUnworking;

public interface IaRiskSystemUnworkingRepository
    extends CommonJpaCrudRepository<IaRiskSystemUnworking, BigDecimal>
{

	@Query(value = "Select e.* from IA_RISK_SYSTEM_UNWORKING e  WHERE e.BUDGET_YEAR = ?1", nativeQuery = true)
	public  List<IaRiskSystemUnworking> findByBudgetYear(String budgetYear);
}
