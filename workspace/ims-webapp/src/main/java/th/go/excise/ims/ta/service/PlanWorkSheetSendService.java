package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.constant.ProjectConstants.EXCISE_OFFICE_CODE;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.preferences.vo.ExciseDepartment;
import th.go.excise.ims.preferences.vo.ExciseDepartmentVo;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSend;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetSendRepository;
import th.go.excise.ims.ta.vo.PlanWorkSheetSendVo;

@Service
public class PlanWorkSheetSendService {
	
	private static final Logger logger = LoggerFactory.getLogger(PlanWorkSheetSendService.class);

	@Autowired
	private TaPlanWorksheetSendRepository planWorksheetSendRepository;
	
	// FIXME Send budgetYear
	public List<PlanWorkSheetSendVo> getPlanWorkSheetSend() {
		logger.info("getPlanWorkSheetSend");
		
		List<PlanWorkSheetSendVo> planWorkSheetSendVoList = new ArrayList<>();
		List<ExciseDepartment> sectorList = null;
		List<ExciseDepartment> areaList = null;

		String userLoginOfficeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		List<TaPlanWorksheetSend> planWorksheetSendList = planWorksheetSendRepository.findByOfficeCodeAndBudgetYearAll(ExciseUtils.whereInLocalOfficeCode(userLoginOfficeCode), ExciseUtils.getCurrentBudgetYear());
		
		if (ExciseUtils.isCentral(userLoginOfficeCode)) {
			// Central
			planWorkSheetSendVoList.add(buildPlanWorkSheetSendVo(EXCISE_OFFICE_CODE.TA_CENTRAL_OPERATOR1, planWorksheetSendList));
			planWorkSheetSendVoList.add(buildPlanWorkSheetSendVo(EXCISE_OFFICE_CODE.TA_CENTRAL_OPERATOR2, planWorksheetSendList));
			
			sectorList = ApplicationCache.getExciseSectorList();
			for (ExciseDepartment sector : sectorList) {
				if (ExciseUtils.isCentral(sector.getOfficeCode())) {
					continue;
				}
				planWorkSheetSendVoList.add(buildPlanWorkSheetSendVo(sector.getOfficeCode(), planWorksheetSendList));
				areaList = ApplicationCache.getExciseAreaList(sector.getOfficeCode());
				for (ExciseDepartment area : areaList) {
					planWorkSheetSendVoList.add(buildPlanWorkSheetSendVo(area.getOfficeCode(), planWorksheetSendList));
				}
			}
		} else if (ExciseUtils.isSector(userLoginOfficeCode)) {
			// Sector
			planWorkSheetSendVoList.add(buildPlanWorkSheetSendVo(userLoginOfficeCode, planWorksheetSendList));
			areaList = ApplicationCache.getExciseAreaList(userLoginOfficeCode);
			for (ExciseDepartment area : areaList) {
				planWorkSheetSendVoList.add(buildPlanWorkSheetSendVo(area.getOfficeCode(), planWorksheetSendList));
			}
		} else {
			// Area
			planWorkSheetSendVoList.add(buildPlanWorkSheetSendVo(userLoginOfficeCode, planWorksheetSendList));
		}

		return planWorkSheetSendVoList;
	}
	
	private PlanWorkSheetSendVo buildPlanWorkSheetSendVo(String officeCode, List<TaPlanWorksheetSend> planWorksheetSendList) {
		PlanWorkSheetSendVo planWorkSheetSendVo = new PlanWorkSheetSendVo();
		if (ExciseUtils.isCentral(officeCode) || ExciseUtils.isSector(officeCode)) {
			planWorkSheetSendVo.setSector(ApplicationCache.getExciseDepartment(officeCode));
			planWorkSheetSendVo.setArea(new ExciseDepartmentVo());
		} else if (ExciseUtils.isArea(officeCode)) {
			planWorkSheetSendVo.setSector(new ExciseDepartmentVo());
			planWorkSheetSendVo.setArea(ApplicationCache.getExciseDepartment(officeCode));
		}
		planWorkSheetSendVo.setPlanWorksheetSend(getPlanWorksheetSendByOfficeCode(officeCode, planWorksheetSendList));
		planWorkSheetSendVo.setTotalFacNum(getTotalFacNum(planWorkSheetSendVo.getPlanWorksheetSend()));
		
		return planWorkSheetSendVo;
	}
	
	private TaPlanWorksheetSend getPlanWorksheetSendByOfficeCode(String officeCode, List<TaPlanWorksheetSend> planWorksheetSendList) {
		TaPlanWorksheetSend planWorksheetSend = null;
		
		for (TaPlanWorksheetSend taPlanWorksheetSend : planWorksheetSendList) {
			if (officeCode.equals(taPlanWorksheetSend.getOfficeCode())) {
				planWorksheetSend = taPlanWorksheetSend;
			}
		}
		
		if (planWorksheetSend == null) {
			planWorksheetSend = new TaPlanWorksheetSend();
			planWorksheetSend.setOfficeCode(officeCode);
		}
		
		return planWorksheetSend;
	}
	
	private Integer getTotalFacNum(TaPlanWorksheetSend planWorksheetSend) {
		return ObjectUtils.defaultIfNull(planWorksheetSend.getFacInNum(), 0) + ObjectUtils.defaultIfNull(planWorksheetSend.getFacOutNum(), 0);
	}
	
}
