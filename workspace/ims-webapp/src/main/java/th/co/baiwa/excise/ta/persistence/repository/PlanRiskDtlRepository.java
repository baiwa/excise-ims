
package th.co.baiwa.excise.ta.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ta.persistence.entity.PlanRiskDtl;

public interface PlanRiskDtlRepository extends CommonJpaCrudRepository<PlanRiskDtl, BigDecimal>
{
	public List<PlanRiskDtl> findByWorkSheetHeaderId(BigDecimal workSheetHeaderId);

}
