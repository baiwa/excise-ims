package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.preferences.vo.ExciseDepartmentVo;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSend;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetSendRepository;
import th.go.excise.ims.ta.vo.PlanWorkSheetSendVo;

@Service
public class PlanWorkSheetSendService {

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

        listSector = ApplicationCache.getExciseSectorList();
        for (ExciseDept list : listSector) {
            areaList = new ArrayList<>();
            areaList = ApplicationCache.getExciseAreaList(list.getOfficeCode());
            listArea.addAll(areaList);
        }
        listPlan = planWorksheetSendRepository.findByBudgetYear(ExciseUtils.getCurrentBudgetYear());
        if (CollectionUtils.isNotEmpty(listPlan)) {

            for (ExciseDept s : listSector) {
                allData = new PlanWorkSheetSendVo();
                plan = new TaPlanWorksheetSend();
                for (int i = 0; i < listPlan.size(); i++) {
                    TaPlanWorksheetSend p = listPlan.get(i);
                    allData.setPlanWorksheetSend(plan);
                    if (s.getOfficeCode().equals(p.getOfficeCode())) {
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
                        	allData.setTotalFacNum(facInNum+facOutNum);
                        }
                        break;
                    }
                }
                if (allData.getPlanWorksheetSend().getOfficeCode() == null) {
                    plan.setOfficeCode(s.getOfficeCode());
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
                            allData.setTotalFacNum(facInNum+facOutNum);
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
                listAll.add(allData);
            }
        }
        return listAll;
    }
}
