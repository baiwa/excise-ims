
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;

public interface IaRiskFactorsMasterRepository extends CommonJpaCrudRepository<IaRiskFactorsMaster, BigDecimal>
{
	@Query("Select e from #{#entityName} e  WHERE e.budgetYear = :budgetYear AND e.inspectionWork = :inspectionWork AND Is_Deleted = 'N'")
	public List<IaRiskFactorsMaster> findByBudgetYearByInspectionWork(@Param("budgetYear") String budgetYear,@Param("inspectionWork") BigDecimal inspectionWork);

}
