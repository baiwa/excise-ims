package th.go.excise.ims.ta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetDtlRepository;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;

@Service
public class TaxAuditService {

    @Autowired
    private TaPlanWorksheetDtlRepository taPlanWorksheetDtlRepository;

    public  DataTableAjax<PlanWorksheetDatatableVo> getPlanWorksheetDtl(PlanWorksheetVo formVo){

        DataTableAjax<PlanWorksheetDatatableVo> dataTableAjax = new DataTableAjax<>();

        dataTableAjax.setData(taPlanWorksheetDtlRepository.findByCriteria(formVo));
        dataTableAjax.setDraw(formVo.getDraw() + 1);
        int count = taPlanWorksheetDtlRepository.countByCriteria(formVo).intValue();
        dataTableAjax.setRecordsFiltered(count);
        dataTableAjax.setRecordsTotal(count);

        return dataTableAjax;
    }
}
