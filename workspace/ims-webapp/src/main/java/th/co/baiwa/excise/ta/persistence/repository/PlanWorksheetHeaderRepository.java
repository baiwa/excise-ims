package th.co.baiwa.excise.ta.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ta.persistence.entity.analysis.PlanWorksheetHeader;


public interface PlanWorksheetHeaderRepository extends CommonJpaCrudRepository<PlanWorksheetHeader, Long> {

	List<PlanWorksheetHeader> findByFlagNotIn(String flag);
}
