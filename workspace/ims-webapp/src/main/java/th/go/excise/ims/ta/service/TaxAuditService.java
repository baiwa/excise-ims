package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetDtlRepository;
import th.go.excise.ims.ta.vo.AuditCalendarVo;
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
    
    public List<ParamInfo> getAuditType(AuditCalendarVo form) {
    	List<ParamInfo> auditType = new ArrayList<>();
    	auditType = ApplicationCache.getParamInfoListByGroupCode(PARAM_GROUP.TA_AUDIT_TYPE);
    	return auditType;
    }
    
    public List<ParamInfo> getAuditStatus(AuditCalendarVo form) {
    	List<ParamInfo> auditStatus = new ArrayList<>();
    	auditStatus = ApplicationCache.getParamInfoListByGroupCode(PARAM_GROUP.TA_AUDIT_STATUS);
    	return auditStatus;
    }
}
