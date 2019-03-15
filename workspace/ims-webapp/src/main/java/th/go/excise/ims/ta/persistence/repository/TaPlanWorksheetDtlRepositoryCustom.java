package th.go.excise.ims.ta.persistence.repository;

import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;
import th.go.excise.ims.ta.vo.AuditCalendarCriteriaFormVo;
import th.go.excise.ims.ta.vo.PlanMapVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;

import java.util.List;

public interface TaPlanWorksheetDtlRepositoryCustom {

	public List<PlanWorksheetDatatableVo> findByCriteria(PlanWorksheetVo formVo);

	public Long countByCriteria(PlanWorksheetVo formVo);
	
	public List<TaPlanWorksheetDtl> findByCriteria(AuditCalendarCriteriaFormVo formVo);

	public List<PlanMapVo> findByInBudgetYearPlanDtl(List<String> budgetYears);
}
