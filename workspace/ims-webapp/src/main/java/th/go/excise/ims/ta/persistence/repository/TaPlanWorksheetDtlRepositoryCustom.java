package th.go.excise.ims.ta.persistence.repository;

import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;

import java.util.List;

public interface TaPlanWorksheetDtlRepositoryCustom {

	public List<PlanWorksheetDatatableVo> findByCriteria(PlanWorksheetVo formVo);

	public Long countByCriteria(PlanWorksheetVo formVo);

}
