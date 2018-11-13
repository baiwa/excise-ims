package th.co.baiwa.excise.ta.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ta.persistence.entity.PlanFromWsHeader;


public interface PlanFromWsHeaderRepository extends CommonJpaCrudRepository<PlanFromWsHeader, Long>,PlanFromWsHeaderCustom {
		
}
