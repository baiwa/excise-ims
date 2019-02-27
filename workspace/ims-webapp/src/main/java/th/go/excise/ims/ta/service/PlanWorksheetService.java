package th.go.excise.ims.ta.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetHdr;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSend;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetDtl;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetSendRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetDtlRepository;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;

@Service
public class PlanWorksheetService {

	private static final Logger logger = LoggerFactory.getLogger(PlanWorksheetService.class);

	@Autowired
	private WorksheetSequenceService worksheetSequenceService;

	@Autowired
	private TaWorksheetDtlRepository taWorksheetDtlRepository;

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

	@Transactional
	public void savePlanWorksheetDtl(PlanWorksheetVo formVo) {
		logger.info("savePlanWorkSheetDtl formVo={}", ToStringBuilder.reflectionToString(formVo, ToStringStyle.JSON_STYLE));

		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();

		List<String> planNewRegIdList = planWorksheetDtlRepository.findByPlanNumberAndOfficeCodeWithoutIsDeletedFlag(formVo.getPlanNumber(), officeCode);
		List<String> selectNewRegIdList = formVo.getIds();

		// Keep New and Delete newRegId list
		TaPlanWorksheetDtl planDtl = null;
		TaWorksheetDtl worksheetDtl = null;
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
				planWorksheetDtlRepository.save(planDtl);

				worksheetDtl = taWorksheetDtlRepository.findByAnalysisNumberAndNewRegId(formVo.getAnalysisNumber(), newRegId);
				if (ExciseUtils.isCentral(officeCode)) {
					worksheetDtl.setCenterSelFlag(FLAG.Y_FLAG);
					worksheetDtl.setCenterSelDate(LocalDate.now());
				} else if (ExciseUtils.isSector(officeCode)) {
					worksheetDtl.setSectorSelFlag(FLAG.Y_FLAG);
					worksheetDtl.setSectorSelDate(LocalDate.now());
				} else if (ExciseUtils.isArea(officeCode)) {
					worksheetDtl.setAreaSelFlag(FLAG.Y_FLAG);
					worksheetDtl.setAreaSelDate(LocalDate.now());
				}
				taWorksheetDtlRepository.save(worksheetDtl);
			}
		}

		// Update newRegId from planWorksheetDtl
		String isDeletedPlanDtl = null;
		String selFlag = null;
		LocalDate selDate = null;
		if (CollectionUtils.isNotEmpty(updateNewRegIdList)) {
			for (String newRegId : updateNewRegIdList) {
				planDtl = planWorksheetDtlRepository.findByPlanNumberAndNewRegIdWithoutIsDeletedFlag(formVo.getPlanNumber(), newRegId);
				if (FLAG.N_FLAG.equals(planDtl.getIsDeleted())) {
					isDeletedPlanDtl = FLAG.Y_FLAG;
					selFlag = null;
					selDate = null;
				} else {
					isDeletedPlanDtl = FLAG.N_FLAG;
					selFlag = FLAG.Y_FLAG;
					selDate = LocalDate.now();
				}
				planWorksheetDtlRepository.updateIsDeletedByPlanNumberAndNewRegId(isDeletedPlanDtl, formVo.getPlanNumber(), newRegId);

				worksheetDtl = taWorksheetDtlRepository.findByAnalysisNumberAndNewRegId(formVo.getAnalysisNumber(), newRegId);
				if (ExciseUtils.isCentral(officeCode)) {
					worksheetDtl.setCenterSelFlag(selFlag);
					worksheetDtl.setCenterSelDate(selDate);
				} else if (ExciseUtils.isSector(officeCode)) {
					worksheetDtl.setSectorSelFlag(selFlag);
					worksheetDtl.setSectorSelDate(selDate);
				} else if (ExciseUtils.isArea(officeCode)) {
					worksheetDtl.setAreaSelFlag(selFlag);
					worksheetDtl.setAreaSelDate(selDate);
				}
				taWorksheetDtlRepository.save(worksheetDtl);
			}
		}
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
