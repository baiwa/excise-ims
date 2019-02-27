package th.go.excise.ims.ta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetHdr;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSend;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetSendRepository;
import th.go.excise.ims.ta.persistence.repository.jdbc.TaxOperatorJdbcRepository;
import th.go.excise.ims.ta.vo.PlanWorkSheetVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanWorkSheetService {

    @Autowired
    private TaPlanWorksheetHdrRepository planWorksheetHdrRepository;

    @Autowired
    private TaxOperatorJdbcRepository taxOperatorJdbcRepository;

    @Autowired
    private TaPlanWorksheetDtlRepository planWorksheetDtlRepository;
    
    @Autowired
    private TaPlanWorksheetSendRepository planWorksheetSendRepository;

    @Autowired
    private WorksheetSequenceService worksheetSequenceService;


    public TaPlanWorksheetHdr checkBudgetPlanHeader(PlanWorkSheetVo formVo) {
        TaPlanWorksheetHdr planHdr = planWorksheetHdrRepository.findByBudgetYear(formVo.getBudgetYear());
        return planHdr;
    }

    public void savePlanWorkSheetHdr(PlanWorkSheetVo formVo) {

        TaPlanWorksheetHdr plantHdr = new TaPlanWorksheetHdr();
        plantHdr.setAnalysisNumber(formVo.getAnalysisNumber());
        plantHdr.setBudgetYear(formVo.getBudgetYear());
        plantHdr.setPlanNumber(worksheetSequenceService.getPlanNumber(UserLoginUtils.getCurrentUserBean().getOfficeCode(), formVo.getBudgetYear()));

        planWorksheetHdrRepository.save(plantHdr);
    }

    public void savePlanWorkSheetDtl(PlanWorkSheetVo formVo) {    	    
    	
        TaPlanWorksheetHdr hdr = planWorksheetHdrRepository.findByBudgetYear(formVo.getBudgetYear());        
        TaxOperatorFormVo formVoTax = new TaxOperatorFormVo();
        // set data search all start 0 length 0
        formVoTax.setAnalysisNumber(hdr.getAnalysisNumber());
        List<TaxOperatorDetailVo> list = taxOperatorJdbcRepository.getTaxOperatorFromSelect(formVo);

        List<TaPlanWorksheetDtl> planWorksheetDtls = new ArrayList<>();
        for (TaxOperatorDetailVo item : list) {                   
                
            
                TaPlanWorksheetDtl planDtl = new TaPlanWorksheetDtl();
                planDtl.setPlanNumber(hdr.getPlanNumber());
                planDtl.setAnalysisNumber(hdr.getAnalysisNumber());
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

    public List<TaPlanWorksheetDtl> findPlanWorkSheetDtl(PlanWorkSheetVo formVo) {
        List<TaPlanWorksheetDtl> list = planWorksheetDtlRepository.findByAnalysisNumberAndPlanNumberAndOfficeCodeAndIsDeleted(formVo.getAnalysisNumber(), formVo.getPlanNumber(), UserLoginUtils.getCurrentUserBean().getOfficeCode(), "N");
        return list;
    }
    
    public List<TaPlanWorksheetSend> getPlanWorkSheetSend(TaPlanWorksheetSend form) {
    	List<TaPlanWorksheetSend> list = new ArrayList<>();
    	list = planWorksheetSendRepository.findByBudgetYear(form.getBudgetYear());
    	return list;
    }
}
