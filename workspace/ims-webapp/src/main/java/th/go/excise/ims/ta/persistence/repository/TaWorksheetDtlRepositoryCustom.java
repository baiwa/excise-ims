package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.bean.LabelValueBean;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetDtl;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

public interface TaWorksheetDtlRepositoryCustom {

    public void batchInsert(List<TaWorksheetDtl> taWorksheetHdrList);

    public List<TaxOperatorDetailVo> findByCriteria(TaxOperatorFormVo formVo);

    public Long countByCriteria(TaxOperatorFormVo formVo);

    List<LabelValueBean> groupCondSubCapital(String analysisNumber);

    List<LabelValueBean> groupCondSubRisk(String analysisNumber);

}
