package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;
import th.go.excise.ims.ta.vo.FactoryVo;
import th.go.excise.ims.ta.vo.OutsidePlanFormVo;
import th.go.excise.ims.ta.vo.OutsidePlanVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.WsReg4000Vo;

public interface TaWsReg4000RepositoryCustom {

	public void batchMerge(List<TaWsReg4000> taWsReg4000List);

	public List<WsReg4000Vo> findByCriteria(TaxOperatorFormVo formVo);

	public Long countByCriteria(TaxOperatorFormVo formVo);

	public List<OutsidePlanVo> outsidePlan(OutsidePlanFormVo formVo);

	public Long countOutsidePlan(OutsidePlanFormVo formVo);

	public FactoryVo findByNewRegId(String newRegId);
	
	public List<WsReg4000Vo> findByCriteriaDuty(TaxOperatorFormVo formVo, String startMonth, String endMonth);

	public Long countByCriteriaDuty(TaxOperatorFormVo formVo);
	
}
