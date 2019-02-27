package th.go.excise.ims.ta.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetHdr;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSend;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetSendRepository;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanWorksheetService {

    private static final Logger logger = LoggerFactory.getLogger(PlanWorksheetService.class);

    @Autowired
    private WorksheetSequenceService worksheetSequenceService;

    @Autowired
    private TaPlanWorksheetHdrRepository planWorksheetHdrRepository;

    @Autowired
    private TaPlanWorksheetDtlRepository planWorksheetDtlRepository;

    @Autowired
    private TaPlanWorksheetSendRepository planWorksheetSendRepository;

    public TaPlanWorksheetHdr getPlanWorksheetHdr(PlanWorksheetVo formVo) {
        logger.info("getPlanWorksheetHdr budgetYear={}", formVo.getBudgetYear());
        return planWorksheetHdrRepository.findByBudgetYear(formVo.getBudgetYear());
    }

    public void savePlanWorksheetHdr(PlanWorksheetVo formVo) {
        logger.info("savePlanWorkSheetHdr formVo={}", ToStringBuilder.reflectionToString(formVo, ToStringStyle.JSON_STYLE));

        TaPlanWorksheetHdr plantHdr = new TaPlanWorksheetHdr();
        plantHdr.setAnalysisNumber(formVo.getAnalysisNumber());
        plantHdr.setBudgetYear(formVo.getBudgetYear());
        plantHdr.setPlanNumber(worksheetSequenceService.getPlanNumber(UserLoginUtils.getCurrentUserBean().getOfficeCode(), formVo.getBudgetYear()));

        planWorksheetHdrRepository.save(plantHdr);

        // Add more logic for support Send All and Send Hierarchy
    }

    public void savePlanWorksheetDtl(PlanWorksheetVo formVo) {
        logger.info("savePlanWorkSheetDtl formVo={}", ToStringBuilder.reflectionToString(formVo, ToStringStyle.JSON_STYLE));

        String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();

        TaPlanWorksheetHdr hdr = planWorksheetHdrRepository.findByBudgetYear(formVo.getPlanNumber());
        // set data search all start 0 length 0
        List<TaPlanWorksheetDtl> planWorksheetDtlList = planWorksheetDtlRepository.findByAnalysisNumberAndPlanNumberAndOfficeCode(formVo.getAnalysisNumber(), formVo.getPlanNumber(), officeCode);

        List<TaPlanWorksheetDtl> planWorksheetDtls = new ArrayList<>();
        for (TaPlanWorksheetDtl planWorksheetDtl : planWorksheetDtlList) {

            TaPlanWorksheetDtl planDtl = new TaPlanWorksheetDtl();
            planDtl.setPlanNumber(hdr.getPlanNumber());
            planDtl.setAnalysisNumber(hdr.getAnalysisNumber());
            planDtl.setOfficeCode(officeCode);
            planDtl.setNewRegId(planWorksheetDtl.getNewRegId());
            planDtl.setAuditStatus(null);
            planDtl.setAuditType(null);
            planDtl.setAuditStartDate(null);
            planDtl.setAuditEndDate(null);

            planWorksheetDtls.add(planDtl);
        }
        planWorksheetDtlRepository.saveAll(planWorksheetDtls);
    }

    public List<TaPlanWorksheetDtl> findPlanWorksheetDtl(PlanWorksheetVo formVo) {
        String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
        logger.info("findPlanWorkSheetDtl planNumber={}, officeCode={}", formVo.getPlanNumber(), officeCode);
        return planWorksheetDtlRepository.findByPlanNumberAndOfficeCode(formVo.getPlanNumber(), officeCode);
    }

    public List<TaPlanWorksheetSend> getPlanWorksheetSend() {
        logger.info("getPlanWorkSheetSend budgetYear={}", ExciseUtils.getCurrentBudgetYear());
        return planWorksheetSendRepository.findByBudgetYear(ExciseUtils.getCurrentBudgetYear());
    }

    public DataTableAjax<PlanWorksheetDatatableVo> planDtlDatatable(PlanWorksheetVo formVo) {

        DataTableAjax<PlanWorksheetDatatableVo> dataTableAjax = new DataTableAjax<>();
        dataTableAjax.setData(planWorksheetDtlRepository.planDtlDatatable(formVo));
        dataTableAjax.setDraw(formVo.getDraw() + 1);
        dataTableAjax.setRecordsFiltered(planWorksheetDtlRepository.countPlanDtlDatatable(formVo).intValue());
        dataTableAjax.setRecordsTotal(planWorksheetDtlRepository.countPlanDtlDatatable(formVo).intValue());

        return dataTableAjax;
    }

    @Transactional
    public void deletePlanWorksheetDlt(String id) {
        logger.info("deletePlanWorksheetDlt by ID : {}", id);
        String budgetYear = ExciseUtils.getCurrentBudgetYear();
        TaPlanWorksheetHdr planHdr = planWorksheetHdrRepository.findByBudgetYear(budgetYear);
        if (planHdr != null) {
            planWorksheetDtlRepository.deleteByPlanNumberAndNewRegId(planHdr.getPlanNumber(), id);
        }
    }
}
