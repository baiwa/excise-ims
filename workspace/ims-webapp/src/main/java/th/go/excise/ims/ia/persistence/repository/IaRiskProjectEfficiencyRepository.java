package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRiskProjectEfficiency;

public interface IaRiskProjectEfficiencyRepository extends CommonJpaCrudRepository<IaRiskProjectEfficiency, BigDecimal> {

	@Query(value = "Select e.* from IA_RISK_PROJECT_EFFICIENCY e  WHERE e.PROJECT_YEAR = ?1 AND Is_Deleted = 'N' ", nativeQuery = true)
	public List<IaRiskProjectEfficiency> findByProjectYear(String projectYear);

}
