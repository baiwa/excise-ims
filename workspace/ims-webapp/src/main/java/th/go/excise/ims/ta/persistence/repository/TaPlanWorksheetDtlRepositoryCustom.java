package th.go.excise.ims.ta.persistence.repository;

import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;

import java.util.List;

public interface TaPlanWorksheetDtlRepositoryCustom {

   public void deletePlanWorkSheet(String analysisNumber, String planNumber, String officeCode, String newRegId, String createdBy);
   public List<PlanWorksheetDatatableVo> planDtlDatatable(PlanWorksheetVo formVo);
   public Long countPlanDtlDatatable(PlanWorksheetVo formVo);
}
