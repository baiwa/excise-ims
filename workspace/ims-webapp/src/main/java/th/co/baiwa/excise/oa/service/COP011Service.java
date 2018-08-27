package th.co.baiwa.excise.oa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.oa.persistence.entity.OaOperPlan;
import th.co.baiwa.excise.oa.persistence.repository.OaOperPlanRepository;
import th.co.baiwa.excise.oa.persistence.vo.COP011SaveRequestFormVo;

@Service
public class COP011Service {
	
	@Autowired
	private OaOperPlanRepository oaOperPlanRepository;
	
	public Message saveOaOperPlan(COP011SaveRequestFormVo cop011SaveRequestFormVo) {
		Message message = null;
		OaOperPlan saveOaOperPlan = null;
		
		Boolean isAddMode = cop011SaveRequestFormVo.getOperPlanId() == null;
		
		if (isAddMode) {
			saveOaOperPlan = new OaOperPlan();
			saveOaOperPlan.setAnalysisId(1L);
			saveOaOperPlan.setMinisFinanceTarget(cop011SaveRequestFormVo.getMinisFinanceTarget());
			saveOaOperPlan.setMinisFinanceExcise(cop011SaveRequestFormVo.getMinisFinanceExcise());
			saveOaOperPlan.setMinisFinanceStrat(cop011SaveRequestFormVo.getMinisFinanceStrat());
			saveOaOperPlan.setStrategy(cop011SaveRequestFormVo.getStrategy());			
			saveOaOperPlan.setObjective1(cop011SaveRequestFormVo.getObjective1());
			saveOaOperPlan.setObjective2(cop011SaveRequestFormVo.getObjective2());
			saveOaOperPlan.setObjective3(cop011SaveRequestFormVo.getObjective3());
			saveOaOperPlan.setObjective4(cop011SaveRequestFormVo.getObjective4());
			saveOaOperPlan.setObjective5(cop011SaveRequestFormVo.getObjective5());
			saveOaOperPlan.setKeyIndicators(cop011SaveRequestFormVo.getKeyIndicators());
			saveOaOperPlan.setPlanner(cop011SaveRequestFormVo.getPlanner());
			saveOaOperPlan.setPrimaryResponse(cop011SaveRequestFormVo.getPrimaryResponse());
			saveOaOperPlan.setPrimaryName(cop011SaveRequestFormVo.getPrimaryName());
			saveOaOperPlan.setPrimaryPosition(cop011SaveRequestFormVo.getPrimaryPosition());
			saveOaOperPlan.setApprover(cop011SaveRequestFormVo.getApprover());
			saveOaOperPlan.setApproverName(cop011SaveRequestFormVo.getApproverName());
			saveOaOperPlan.setApproverPosition(cop011SaveRequestFormVo.getApproverPosition());
			saveOaOperPlan.setPlanStart(cop011SaveRequestFormVo.getPlanStart());
			saveOaOperPlan.setPlanEnd(cop011SaveRequestFormVo.getPlanEnd());
			saveOaOperPlan.setPhone(cop011SaveRequestFormVo.getPhone());
			saveOaOperPlan.setPlanDetail(cop011SaveRequestFormVo.getPlanDetail());
			saveOaOperPlan.setPlanName(cop011SaveRequestFormVo.getPlanName());
			
			oaOperPlanRepository.save(saveOaOperPlan);
			
			message = ApplicationCache.getMessage("MSG_00002");
		} else {
			message = ApplicationCache.getMessage("MSG_00003");
		}
		return message;
	}
	
}
