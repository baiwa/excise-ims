package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import th.go.excise.ims.ta.persistence.entity.TaWorksheetDtl;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

public interface TaWorksheetDtlRepositoryCustom {
	
	public void batchInsert(List<TaWorksheetDtl> taWorksheetHdrList);
	
	public List<TaxOperatorDetailVo> findByCriteria(TaxOperatorFormVo formVo);
	
	public Long countByCriteria(TaxOperatorFormVo formVo);
	
}
