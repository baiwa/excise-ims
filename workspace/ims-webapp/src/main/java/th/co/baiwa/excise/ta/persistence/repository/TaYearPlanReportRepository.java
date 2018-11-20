package th.co.baiwa.excise.ta.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ta.persistence.entity.TaYearPlanReport;

public interface TaYearPlanReportRepository extends CommonJpaCrudRepository<TaYearPlanReport, Long> {

	public TaYearPlanReport findByYearPlanId(Long yearPlanId);

}
