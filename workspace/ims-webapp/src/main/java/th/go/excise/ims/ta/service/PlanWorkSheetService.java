package th.go.excise.ims.ta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetHdr;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetHdrRepository;
import th.go.excise.ims.ta.persistence.repository.jdbc.TaxOperatorJdbcRepository;
import th.go.excise.ims.ta.vo.PlanWorkSheetVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PlanWorkSheetService {

    @Autowired
    private TaPlanWorksheetHdrRepository planWorksheetHdrRepository;

    @Autowired
    private TaxOperatorJdbcRepository taxOperatorJdbcRepository;

    @Autowired
    private TaPlanWorksheetDtlRepository planWorksheetDtlRepository;

    public void savePlanWorkSheet(PlanWorkSheetVo formVo) {

        TaPlanWorksheetHdr plantHdr = new TaPlanWorksheetHdr();
        plantHdr.setAnalysisNumber(formVo.getAnalysisNumber());
        plantHdr.setBudgetYear(formVo.getBudgetYear());
        plantHdr.setPlanNumber(ConvertDateUtils.formatDateToString(new Date(), ConvertDateUtils.YYYYMMDDHHMMSS, ConvertDateUtils.LOCAL_EN));

        planWorksheetHdrRepository.save(plantHdr);

        TaxOperatorFormVo formVoTax = new TaxOperatorFormVo();
        // set data search all start 0 length 0
        formVoTax.setAnalysisNumber(plantHdr.getAnalysisNumber());
        formVoTax.setStart(0);
        formVoTax.setLength(0);
        List<TaxOperatorDetailVo> list = taxOperatorJdbcRepository.getTaxOperator(formVoTax);

        List<TaPlanWorksheetDtl> planWorksheetDtls = new ArrayList<>();
        for (TaxOperatorDetailVo item : list) {

            TaPlanWorksheetDtl planDtl = new TaPlanWorksheetDtl();
            planDtl.setPlanNumber(plantHdr.getPlanNumber());
            planDtl.setAnalysisNumber(plantHdr.getAnalysisNumber());
            planDtl.setOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
            planDtl.setNewRegId(item.getNewRegId());
            planDtl.setAuditStatus(null);
            planDtl.setAuditType(null);
            planDtl.setAuditStartDate(null);
            planDtl.setAuditEndDate(null);

            planWorksheetDtls.add(planDtl);
        }
        planWorksheetDtlRepository.saveAll(planWorksheetDtls);
    }
}
