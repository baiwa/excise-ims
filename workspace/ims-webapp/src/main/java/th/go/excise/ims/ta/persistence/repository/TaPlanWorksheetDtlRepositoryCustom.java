package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import th.go.excise.ims.preferences.persistence.entity.ExcisePerson;
import th.go.excise.ims.ta.vo.AuditCalendarCriteriaFormVo;
import th.go.excise.ims.ta.vo.PersonAssignForm;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDtlVo;
import th.go.excise.ims.ta.vo.PlanWorksheetSendTableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;

public interface TaPlanWorksheetDtlRepositoryCustom {

	public List<PlanWorksheetDatatableVo> findByCriteria(PlanWorksheetVo formVo);
	
	public List<PlanWorksheetDatatableVo> findAllByCriteria(PlanWorksheetVo formVo);

	public Long countByCriteria(PlanWorksheetVo formVo);
	
	public List<PlanWorksheetDtlVo> findByCriteria(AuditCalendarCriteriaFormVo formVo);
	
	public void updateStatusPlanWorksheetDtl(ExcisePerson formVo,String status);
	
	public void updateStatusPlanWorksheetDtlByList(PersonAssignForm formVo,String status);
	
	public List<PlanWorksheetSendTableVo> findPlanWorksheetByDtl(PlanWorksheetVo formVo);
}
