package th.go.excise.ims.ta.service;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.common.constant.ProjectConstants.EXCISE_OFFICE_CODE;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetHdr;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSelect;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSend;
import th.go.excise.ims.ta.persistence.repository.*;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetStatus;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlanWorksheetService {

    private static final Logger logger = LoggerFactory.getLogger(PlanWorksheetService.class);

    @Autowired
    private WorksheetSequenceService worksheetSequenceService;
    
    @Autowired
    private TaWorksheetDtlRepository taWorksheetDtlRepository;
    
    @Autowired
    private TaPlanWorksheetHdrRepository taPlanWorksheetHdrRepository;
    @Autowired
    private TaPlanWorksheetDtlRepository taPlanWorksheetDtlRepository;
    
    @Autowired
    private TaPlanWorksheetSendRepository taPlanWorksheetSendRepository;
    @Autowired
    private TaPlanWorksheetSelectRepository taPlanWorksheetSelectRepository;

    public TaPlanWorksheetHdr getPlanWorksheetHdr(PlanWorksheetVo formVo) {
        logger.info("getPlanWorksheetHdr budgetYear={}", formVo.getBudgetYear());
        return taPlanWorksheetHdrRepository.findByBudgetYear(formVo.getBudgetYear());
    }

    @Transactional(rollbackFor = {Exception.class})
    public void savePlanWorksheetHdr(PlanWorksheetVo formVo) {
    	String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
        String planNumber = worksheetSequenceService.getPlanNumber(officeCode, formVo.getBudgetYear());
        logger.info("savePlanWorksheetHdr officeCode={}, budgetYear={}, analysisNumber={}, planNumber={}", officeCode, formVo.getBudgetYear(), formVo.getAnalysisNumber(), planNumber);

        TaPlanWorksheetHdr planHdr = new TaPlanWorksheetHdr();
        planHdr.setAnalysisNumber(formVo.getAnalysisNumber());
        planHdr.setBudgetYear(formVo.getBudgetYear());
        planHdr.setPlanNumber(planNumber);
        planHdr.setSendAllFlag(formVo.getSendAllFlag());
        taPlanWorksheetHdrRepository.save(planHdr);

        // Add more logic for support Send All and Send Hierarchy
        List<TaPlanWorksheetSend> planSendList = new ArrayList<>();
        TaPlanWorksheetSend planSend = null;
        if (FLAG.Y_FLAG.equals(formVo.getSendAllFlag())) {
            List<ExciseDept> sectorList = ApplicationCache.getExciseSectorList();
            List<ExciseDept> areaList = null;
            // Sector
            for (ExciseDept sector : sectorList) {
                planSend = new TaPlanWorksheetSend();
                planSend.setBudgetYear(formVo.getBudgetYear());
                planSend.setPlanNumber(planNumber);
                planSend.setOfficeCode(sector.getOfficeCode());
                planSend.setSendDate(LocalDate.now());
                planSendList.add(planSend);
                // Area
                areaList = ApplicationCache.getExciseAreaList(sector.getOfficeCode());
                for (ExciseDept area : areaList) {
                    planSend = new TaPlanWorksheetSend();
                    planSend.setBudgetYear(formVo.getBudgetYear());
                    planSend.setPlanNumber(planNumber);
                    planSend.setOfficeCode(area.getOfficeCode());
                    planSend.setSendDate(LocalDate.now());
                    planSendList.add(planSend);
                }
            }
        } else {
            planSend = new TaPlanWorksheetSend();
            planSend.setBudgetYear(formVo.getBudgetYear());
            planSend.setPlanNumber(planNumber);
            planSend.setOfficeCode(EXCISE_OFFICE_CODE.CENTRAL);
            planSend.setSendDate(LocalDate.now());
            planSendList.add(planSend);
        }
        taPlanWorksheetSendRepository.saveAll(planSendList);
        
        // Initial Data for PlanWorksheetSelect
        List<String> newRegIdList = taWorksheetDtlRepository.findNewRegIdByAnalysisNumber(formVo.getAnalysisNumber());
        taPlanWorksheetSelectRepository.batchInsert(formVo.getBudgetYear(), newRegIdList);
    }

    @Transactional
    public void savePlanWorksheetDtl(PlanWorksheetVo formVo) {
    	String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
    	String budgetYear = formVo.getBudgetYear();
        logger.info("savePlanWorkSheetDtl officeCode={}, budgetYear={}, planNumber={}, analysisNumber={}, newRegIds={}",
        	officeCode, budgetYear, formVo.getPlanNumber(), formVo.getAnalysisNumber(),
        	org.springframework.util.StringUtils.collectionToCommaDelimitedString(formVo.getIds()));
        
        List<String> planNewRegIdList = taPlanWorksheetDtlRepository.findNewRegIdByOfficeCodeAndPlanNumber(officeCode, formVo.getPlanNumber());
        List<String> planNewRegIdFlagNList = taPlanWorksheetDtlRepository.findNewRegIdByOfficeCodeAndPlanNumberAndIsDeletedFlagN(officeCode, formVo.getPlanNumber());
        List<String> selectNewRegIdList = formVo.getIds();

        // Keep New and Delete newRegId list
        TaPlanWorksheetDtl planDtl = null;
        List<String> insertNewRegIdList = new ArrayList<>();
        List<String> updateNewRegIdList = new ArrayList<>();

        // Loop selectNewRegIdList for Insert NewRegId
        for (String selNewRegId : selectNewRegIdList) {
            if (!planNewRegIdList.contains(selNewRegId)) {
                insertNewRegIdList.add(selNewRegId);
            }
        }

        // Loop planWorksheetDtlNewRegIdList for Update NewRegId
        for (String planRewRegId : planNewRegIdList) {
            if (!selectNewRegIdList.contains(planRewRegId)) {
                updateNewRegIdList.add(planRewRegId);
            }
        }

        // Insert newRegId to planWorksheetDtl
        if (CollectionUtils.isNotEmpty(insertNewRegIdList)) {
            for (String newRegId : insertNewRegIdList) {
                planDtl = new TaPlanWorksheetDtl();
                planDtl.setPlanNumber(formVo.getPlanNumber());
                planDtl.setAnalysisNumber(formVo.getAnalysisNumber());
                planDtl.setOfficeCode(officeCode);
                planDtl.setNewRegId(newRegId);
                planDtl.setAuditStatus("I"); // FIXME
                taPlanWorksheetDtlRepository.save(planDtl);
                
                updateFlagWorksheetSelect(budgetYear, newRegId, officeCode, FLAG.Y_FLAG, LocalDate.now());
            }
        }

        // Update newRegId from planWorksheetDtl
        String isDeletedPlanDtl = null;
        String selFlag = null;
        LocalDate selDate = null;
        if (CollectionUtils.isNotEmpty(updateNewRegIdList)) {
            for (String newRegId : updateNewRegIdList) {
                if (selectNewRegIdList.contains(newRegId)) {
                    isDeletedPlanDtl = FLAG.N_FLAG;
                    selFlag = FLAG.Y_FLAG;
                    selDate = LocalDate.now();

                } else {
                    isDeletedPlanDtl = FLAG.Y_FLAG;
                    selFlag = null;
                    selDate = null;
                }
                taPlanWorksheetDtlRepository.updateIsDeletedByPlanNumberAndNewRegId(isDeletedPlanDtl, formVo.getPlanNumber(), newRegId);
                updateFlagWorksheetSelect(budgetYear, newRegId, officeCode, selFlag, selDate);
            }
        } else {
            for (String selecNewRegId : selectNewRegIdList) {
                if (!planNewRegIdFlagNList.contains(selecNewRegId)) {
                    isDeletedPlanDtl = FLAG.N_FLAG;
                    selFlag = FLAG.Y_FLAG;
                    selDate = LocalDate.now();
                    taPlanWorksheetDtlRepository.updateIsDeletedByPlanNumberAndNewRegId(isDeletedPlanDtl, formVo.getPlanNumber(), selecNewRegId);
                    updateFlagWorksheetSelect(budgetYear, selecNewRegId, officeCode, selFlag, selDate);
                }
            }
        }
    }

    private void updateFlagWorksheetSelect(String budgetYear, String newRegId, String officeCode, String selFlag, LocalDate selDate) {
    	logger.info("updateFlagWorksheetSelect budgetYear={}, newRegId={}, officeCode={}, selFlag={}, selDate={}",
    		budgetYear, newRegId, officeCode, selFlag, selDate);
    	TaPlanWorksheetSelect planWorksheetSel = taPlanWorksheetSelectRepository.findByBudgetYearAndNewRegId(budgetYear, newRegId);
        if (ExciseUtils.isCentral(officeCode)) {
        	planWorksheetSel.setCentralSelFlag(selFlag);
        	planWorksheetSel.setCentralSelDate(selDate);
        	planWorksheetSel.setCentralSelOfficeCode(officeCode);
        } else if (ExciseUtils.isSector(officeCode)) {
        	planWorksheetSel.setSectorSelFlag(selFlag);
        	planWorksheetSel.setSectorSelDate(selDate);
        	planWorksheetSel.setSectorSelOfficeCode(officeCode);
        } else if (ExciseUtils.isArea(officeCode)) {
        	planWorksheetSel.setAreaSelFlag(selFlag);
        	planWorksheetSel.setAreaSelDate(selDate);
        	planWorksheetSel.setAreaSelOfficeCode(officeCode);
        }
        taPlanWorksheetSelectRepository.save(planWorksheetSel);
    }

    public List<TaPlanWorksheetDtl> findPlanWorksheetDtl(PlanWorksheetVo formVo) {
        String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
        logger.info("findPlanWorkSheetDtl officeCode={}, planNumber={}", officeCode, formVo.getPlanNumber());
        List<TaPlanWorksheetDtl> list = taPlanWorksheetDtlRepository.findByOfficeCodeAndPlanNumber(officeCode, formVo.getPlanNumber());
        return list;
    }

    public DataTableAjax<PlanWorksheetDatatableVo> planDtlDatatable(PlanWorksheetVo formVo) {
        formVo.setOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());

        DataTableAjax<PlanWorksheetDatatableVo> dataTableAjax = new DataTableAjax<>();
        dataTableAjax.setData(taPlanWorksheetDtlRepository.findByCriteria(formVo));
        dataTableAjax.setDraw(formVo.getDraw() + 1);
        int count = taPlanWorksheetDtlRepository.countByCriteria(formVo).intValue();
        dataTableAjax.setRecordsFiltered(count);
        dataTableAjax.setRecordsTotal(count);

        return dataTableAjax;
    }

    public Boolean checkSubmitDatePlanWorksheetSend(PlanWorksheetVo formVo) {
        TaPlanWorksheetSend planSend = taPlanWorksheetSendRepository.findByPlanNumberAndOfficeCodeAndSubmitDateIsNull(formVo.getPlanNumber(), UserLoginUtils.getCurrentUserBean().getOfficeCode());
        if (planSend != null) {
            return false;
        }
        return true;
    }

    @Transactional
    public void deletePlanWorksheetDlt(String id) {
        logger.info("deletePlanWorksheetDlt by ID : {}", id);
        String budgetYear = ExciseUtils.getCurrentBudgetYear();
        TaPlanWorksheetHdr planHdr = taPlanWorksheetHdrRepository.findByBudgetYear(budgetYear);
        if (planHdr != null) {
            taPlanWorksheetDtlRepository.deleteByPlanNumberAndNewRegId(planHdr.getPlanNumber(), id);
        }
    }

    public void savePlanWorksheetSend(PlanWorksheetVo formVo) {
        String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
        formVo.setOfficeCode(officeCode);
        TaPlanWorksheetSend planSend = taPlanWorksheetSendRepository.findByPlanNumberAndOfficeCode(formVo.getPlanNumber(), officeCode);
        Long count = taPlanWorksheetDtlRepository.countByCriteria(formVo);
        planSend.setFacInNum(new Integer(count.intValue()));
        planSend.setSubmitDate(LocalDate.now());
        taPlanWorksheetSendRepository.save(planSend);

        TaPlanWorksheetHdr planHdr = this.taPlanWorksheetHdrRepository.findByPlanNumber(formVo.getPlanNumber());

        if (FLAG.N_FLAG.equalsIgnoreCase(planHdr.getSendAllFlag())) {
            if (ExciseUtils.isCentral(officeCode)) {
                List<ExciseDept> sectorList = ApplicationCache.getExciseSectorList();
                for (ExciseDept sector : sectorList) {
                    if (!officeCode.equals(sector.getOfficeCode())) {
                        saveObjectTaPlanWorksheetSend(formVo, sector.getOfficeCode());
                    }
                }
            }
            if (ExciseUtils.isSector(officeCode)) {
                List<ExciseDept> areaList = ApplicationCache.getExciseAreaList(officeCode);
                for (ExciseDept area : areaList) {
                    saveObjectTaPlanWorksheetSend(formVo, area.getOfficeCode());
                }
            }
        }
    }

    private void saveObjectTaPlanWorksheetSend(PlanWorksheetVo formVo, String officeCode) {
        TaPlanWorksheetSend planSendStampDate = new TaPlanWorksheetSend();
        planSendStampDate.setBudgetYear(formVo.getBudgetYear());
        planSendStampDate.setPlanNumber(formVo.getPlanNumber());
        planSendStampDate.setOfficeCode(officeCode);
        planSendStampDate.setSendDate(LocalDate.now());
        taPlanWorksheetSendRepository.save(planSendStampDate);
    }

    public PlanWorksheetStatus getPlanHeaderStatus(PlanWorksheetVo formVo) {
        PlanWorksheetStatus status = new PlanWorksheetStatus();
        TaPlanWorksheetHdr planWsHdr = taPlanWorksheetHdrRepository.findByBudgetYear(formVo.getBudgetYear());

        if (planWsHdr != null) {
            ParamInfo statusDesc = ApplicationCache.getParamInfoByCode("TA_PLAN_STATUS", planWsHdr.getPlanStatus());
            if (statusDesc != null) {
                status.setPlanStatus(statusDesc.getParamCode());
                status.setApprovalComment(planWsHdr.getApprovalComment());
                status.setApprovedComment(planWsHdr.getApprovedComment());
                status.setPlanStatusDesc(statusDesc.getValue1());
            }
        }

        return status;
    }

    public void insertComment(TaPlanWorksheetHdr form) {
        TaPlanWorksheetHdr comment = taPlanWorksheetHdrRepository.findByBudgetYear(form.getBudgetYear());
        if (StringUtils.isEmpty(form.getApprovedComment())) {
            comment.setApprovalBy(UserLoginUtils.getCurrentUsername());
            comment.setApprovalDate(LocalDateTime.now());
            comment.setApprovalComment(form.getApprovalComment());
            comment.setPlanStatus("W");
        } else {
            comment.setApprovedBy(UserLoginUtils.getCurrentUsername());
            comment.setApprovedDate(LocalDateTime.now());
            comment.setApprovedComment(form.getApprovedComment());
            comment.setPlanStatus("P");
        }
        taPlanWorksheetHdrRepository.save(comment);
    }
    
    @Transactional(rollbackFor = {Exception.class})
    public void clearDataByBudgetYear(String budgetYear) {
    	logger.info("clearDataByBudgetYear budgetYear={}", budgetYear);
    	taPlanWorksheetDtlRepository.forceDeleteByBudgetYear(budgetYear);
    	taPlanWorksheetHdrRepository.forceDeleteByBudgetYear(budgetYear);
    	taPlanWorksheetSendRepository.forceDeleteByBudgetYear(budgetYear);
    	taPlanWorksheetSelectRepository.forceDeleteByBudgetYear(budgetYear);
    }

}
