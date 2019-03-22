package th.go.excise.ims.ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaPlanDayActivity;
import th.go.excise.ims.ia.persistence.entity.IaPlanDtl;
import th.go.excise.ims.ia.persistence.repository.IaPlanDayActivityRepository;
import th.go.excise.ims.ia.persistence.repository.IaPlanDtlRepository;
import th.go.excise.ims.ia.vo.Int0101FormVo;
import th.go.excise.ims.ia.vo.Int0101PlanDayVo;

@Service
public class Int0101Service {
	
	@Autowired
	private IaPlanDayActivityRepository iaPlanDayActivityRepository;
	
	@Autowired
	private IaPlanDtlRepository iaPlanDtlRepository;
	
	public void save(Int0101FormVo request) {
		/* update plan-detail */
		IaPlanDtl dataFilterDtl = iaPlanDtlRepository.findById(request.getPlanDtlId()).get();
		dataFilterDtl.setResponsiblePerson(request.getResponsiblePerson());
		dataFilterDtl.setPosition(request.getPosition());
		iaPlanDtlRepository.save(dataFilterDtl);
		
		/* save plan-day */
		IaPlanDayActivity entityPlanDay = null;
		if(request.getPlanVo().size() > 0) {
			for (Int0101PlanDayVo planDay : request.getPlanVo()) {
				entityPlanDay = new IaPlanDayActivity();
				entityPlanDay.setPlanHdrId(dataFilterDtl.getPlanHdrId());
				entityPlanDay.setPlanDtlId(dataFilterDtl.getPlanDtlId());
				entityPlanDay.setActivity(planDay.getActivity());
				if(planDay.getActivity().equals(IaConstants.PLAN_DAY_WORDING.ENGAGEMENT_FULL)) {
					entityPlanDay.setActivityShort(IaConstants.PLAN_DAY_WORDING.ENGAGEMENT_ABBREVIATION);
				}else if(planDay.getActivity().equals(IaConstants.PLAN_DAY_WORDING.AUDIT_FULL)) {
					entityPlanDay.setActivityShort(IaConstants.PLAN_DAY_WORDING.AUDIT_ABBREVIATION);
				}else if(planDay.getActivity().equals(IaConstants.PLAN_DAY_WORDING.REPORT_FULL)) {
					entityPlanDay.setActivityShort(IaConstants.PLAN_DAY_WORDING.REPORT_ABBREVIATION);
				}else {
					entityPlanDay.setActivityShort(IaConstants.PLAN_DAY_WORDING.MONITORING_ABBREVIATION);
				}
				entityPlanDay.setDateStartActivity(ConvertDateUtils.parseStringToDate(planDay.getDateStartActivity(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				entityPlanDay.setDateEndActivity(ConvertDateUtils.parseStringToDate(planDay.getDateEndActivity(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				
				iaPlanDayActivityRepository.save(entityPlanDay);
			}
		}

	}

}
