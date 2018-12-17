package th.co.baiwa.excise.ta.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ta.persistence.entity.analysis.PlanWorksheetHeader;


public interface PlanWorksheetHeaderRepository extends CommonJpaCrudRepository<PlanWorksheetHeader, Long> {

	public List<PlanWorksheetHeader> findByFlagNotIn(String flag);
	
	public PlanWorksheetHeader findByExciseIdAndAnalysNumber(String exciseId ,  String analysNumber);
	
	public List<PlanWorksheetHeader> findByAnalysNumber(String analysNumber);
	
	public List<PlanWorksheetHeader> findByFlag(String flag);
	
}
