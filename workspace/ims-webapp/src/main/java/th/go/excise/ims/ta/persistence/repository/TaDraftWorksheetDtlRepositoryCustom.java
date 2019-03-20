package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import th.go.excise.ims.ta.persistence.entity.TaDraftWorksheetDtl;
import th.go.excise.ims.ta.vo.TaxDratfVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

public interface TaDraftWorksheetDtlRepositoryCustom {
	
	public void batchInsert(List<TaDraftWorksheetDtl> draftWorksheetDtlList);
	
	public List<TaxOperatorDetailVo> findByCriteria(TaxOperatorFormVo formVo);
	
	public Long countByCriteria(TaxOperatorFormVo formVo);
	
	public List<TaxDratfVo> findByDraftNumber(String analysisNumber);
	
}
