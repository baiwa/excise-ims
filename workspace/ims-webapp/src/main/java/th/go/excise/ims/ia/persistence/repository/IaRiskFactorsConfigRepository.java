
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;

public interface IaRiskFactorsConfigRepository
    extends CommonJpaCrudRepository<IaRiskFactorsConfig, BigDecimal>
{
	@Query(value = "Select e.* from IA_RISK_FACTORS_CONFIG e  WHERE e.ID_FACTORS = ?1 AND Is_Deleted = 'N'",nativeQuery = true)
	public IaRiskFactorsConfig findByIdFactors(BigDecimal idFactors);

}
