package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;
import th.go.excise.ims.common.constant.ProjectConstants.EXCISE_OFFICE_CODE;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.preferences.vo.ExciseDepartmentVo;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSend;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetSendRepository;
import th.go.excise.ims.ta.util.TaxAuditUtils;
import th.go.excise.ims.ta.vo.PlanWorkSheetSendVo;

@Service
public class PlanWorkSheetSendService {
	private static final Logger logger = LoggerFactory.getLogger(PlanWorkSheetSendService.class);

	@Autowired
	private TaPlanWorksheetSendRepository planWorksheetSendRepository;

	public List<PlanWorkSheetSendVo> getPlanWorkSheetSend() {
		List<PlanWorkSheetSendVo> listAll = new ArrayList<>();
		PlanWorkSheetSendVo allData = null;
		List<ExciseDept> listSector = new ArrayList<>();
		List<ExciseDept> listArea = new ArrayList<>();
		List<ExciseDept> areaList = null;
		List<TaPlanWorksheetSend> listPlan = new ArrayList<>();
		TaPlanWorksheetSend plan = null;
		ExciseDept dataListNull = new ExciseDepartmentVo();

		String convertOfficeCode = ExciseUtils.whereInLocalOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
		listPlan = planWorksheetSendRepository.findByOfficeCodeAndBudgetYearAll(convertOfficeCode, ExciseUtils.getCurrentBudgetYear());
		if (ExciseUtils.isCentral(UserLoginUtils.getCurrentUserBean().getOfficeCode())) {
			listSector = TaxAuditUtils.getExciseSectorList();
			for (ExciseDept list : listSector) {
				String officeCode = "";
				if (EXCISE_OFFICE_CODE.TA_CENTRAL.equals(list.getOfficeCode())) {
					officeCode = EXCISE_OFFICE_CODE.CENTRAL;
				} else {
					officeCode = list.getOfficeCode();
				}
				areaList = new ArrayList<>();
				areaList = ApplicationCache.getExciseAreaList(officeCode);
				listArea.addAll(areaList);
			}

			for (ExciseDept s : listSector) {
				String officeCode = "";
				if (EXCISE_OFFICE_CODE.TA_CENTRAL.equals(s.getOfficeCode())) {
					officeCode = EXCISE_OFFICE_CODE.CENTRAL;
				} else {
					officeCode = s.getOfficeCode();
				}
				allData = new PlanWorkSheetSendVo();
				plan = new TaPlanWorksheetSend();
				for (int i = 0; i < listPlan.size(); i++) {
					TaPlanWorksheetSend p = listPlan.get(i);
					allData.setPlanWorksheetSend(plan);
					if (officeCode.equals(p.getOfficeCode())) {
						allData.setPlanWorksheetSend(p);
						if (p.getFacInNum() != null || p.getFacOutNum() != null) {
							Integer facInNum = null;
							Integer facOutNum = null;
							facInNum = p.getFacInNum();
							facOutNum = p.getFacOutNum();
							if (null == facInNum) {
								facInNum = 0;
							}
							if (null == facOutNum) {
								facOutNum = 0;
							}
							allData.setTotalFacNum(facInNum + facOutNum);
						}
						break;
					}
				}
				if (allData.getPlanWorksheetSend().getOfficeCode() == null) {
					plan.setOfficeCode(officeCode);
					allData.setPlanWorksheetSend(plan);
				}
				allData.setSector(s);
				allData.setArea(dataListNull);
				listAll.add(allData);
			}
			for (ExciseDept a : listArea) {
				allData = new PlanWorkSheetSendVo();
				plan = new TaPlanWorksheetSend();
				for (int i = 0; i < listPlan.size(); i++) {
					TaPlanWorksheetSend p = listPlan.get(i);
					allData.setPlanWorksheetSend(plan);
					if (a.getOfficeCode().equals(p.getOfficeCode())) {
						allData.setPlanWorksheetSend(p);
						if (p.getFacInNum() != null && p.getFacOutNum() != null) {
							Integer facInNum = null;
							Integer facOutNum = null;
							facInNum = p.getFacInNum();
							facOutNum = p.getFacOutNum();
							if (null == facInNum) {
								facInNum = 0;
							}
							if (null == facOutNum) {
								facOutNum = 0;
							}
							allData.setTotalFacNum(facInNum + facOutNum);
						}
						break;
					}
				}
				if (allData.getPlanWorksheetSend().getOfficeCode() == null) {
					plan.setOfficeCode(a.getOfficeCode());
					allData.setPlanWorksheetSend(plan);
				}
				allData.setSector(dataListNull);
				allData.setArea(a);
				if (!a.getOfficeCode().substring(0, 2).equals("00")) {
					listAll.add(allData);
				}
			}
		} else if (ExciseUtils.isSector(UserLoginUtils.getCurrentUserBean().getOfficeCode())) {
			listSector = ApplicationCache.getExciseAreaList(UserLoginUtils.getCurrentUserBean().getOfficeCode());
			List<TaPlanWorksheetSend> sector = listPlan.stream()
				    .filter(p -> StringUtils.equals(UserLoginUtils.getCurrentUserBean().getOfficeCode(), p.getOfficeCode())).collect(Collectors.toList());
			for (ExciseDept list : listSector) {
				String officeCode = "";
				if (EXCISE_OFFICE_CODE.TA_CENTRAL.equals(list.getOfficeCode())) {
					officeCode = EXCISE_OFFICE_CODE.CENTRAL;
				} else {
					officeCode = list.getOfficeCode();
				}
				areaList = new ArrayList<>();
				areaList = ApplicationCache.getExciseAreaList(officeCode);
				listArea.addAll(areaList);
			}
			allData = new PlanWorkSheetSendVo();
			
			allData.setSector(ApplicationCache.getExciseDept(UserLoginUtils.getCurrentUserBean().getOfficeCode()));
			allData.setArea(dataListNull);
			allData.setPlanWorksheetSend(sector.get(0));
			listAll.add(allData);
			for (ExciseDept s : listSector) {
				String officeCode = "";
				if (EXCISE_OFFICE_CODE.TA_CENTRAL.equals(s.getOfficeCode())) {
					officeCode = EXCISE_OFFICE_CODE.CENTRAL;
				} else {
					officeCode = s.getOfficeCode();
				}
				allData = new PlanWorkSheetSendVo();
				plan = new TaPlanWorksheetSend();
				for (int i = 0; i < listPlan.size(); i++) {
					TaPlanWorksheetSend p = listPlan.get(i);
					allData.setPlanWorksheetSend(plan);
					if (officeCode.equals(p.getOfficeCode())) {
						allData.setPlanWorksheetSend(p);
						if (p.getFacInNum() != null || p.getFacOutNum() != null) {
							Integer facInNum = null;
							Integer facOutNum = null;
							facInNum = p.getFacInNum();
							facOutNum = p.getFacOutNum();
							if (null == facInNum) {
								facInNum = 0;
							}
							if (null == facOutNum) {
								facOutNum = 0;
							}
							allData.setTotalFacNum(facInNum + facOutNum);
						}
						break;
					}
				}
				if (allData.getPlanWorksheetSend().getOfficeCode() == null) {
					plan.setOfficeCode(officeCode);
					allData.setPlanWorksheetSend(plan);
				}
				allData.setSector(dataListNull);
				allData.setArea(s);
				listAll.add(allData);
			}
		} else {
			ExciseDept area = ApplicationCache.getExciseDept(UserLoginUtils.getCurrentUserBean().getOfficeCode());
			allData = new PlanWorkSheetSendVo();
			plan = new TaPlanWorksheetSend();
			Integer facInNum = null;
			Integer facOutNum = null;
			allData.setPlanWorksheetSend(plan);
			allData.setPlanWorksheetSend(listPlan.get(0));
			facInNum = listPlan.get(0).getFacInNum();
			facOutNum = listPlan.get(0).getFacOutNum();
			if (null == facInNum) {
				facInNum = 0;
			}
			if (null == facOutNum) {
				facOutNum = 0;
			}
			allData.setTotalFacNum(facInNum + facOutNum);
			
			allData.setSector(dataListNull);
			allData.setArea(area);
			listAll.add(allData);
		}

		return listAll;
	}
}
