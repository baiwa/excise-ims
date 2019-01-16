package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.ia.persistence.entity.Condition;
import th.co.baiwa.excise.ia.persistence.repository.ConditionRepository;
import th.co.baiwa.excise.ia.persistence.vo.ConditionFormVo;

@Service
public class ConditionService {

	private Logger logger = LoggerFactory.getLogger(ConditionService.class);
	@Autowired
	private ConditionRepository conditionRepository;

	public Message insertCondition(ConditionFormVo condition) {
		Message msg = ApplicationCache.getMessage("MSG_00003");
		List<Condition> cond = new ArrayList<>();
		Condition con = new Condition();
//		for (Condition co : condition.getCondition()) {
//			if (BeanUtils.isEmpty(co.getConditionId())) {
//				cond.add(co);
//			}
//			else {
//				con = new Condition();
//				con = conditionRepository.findOne(co.getConditionId());
//				cond.add(con);
//			}
//		}
		conditionRepository.save(condition.getCondition());
		msg = ApplicationCache.getMessage("MSG_00002");
		return msg;
	}

	public List<Condition> findConditionByParentId(Long parentId, String riskType, String page) {
		logger.info("insertCondition in " + parentId);
		return conditionRepository.findConditionByParentId(parentId, riskType, page);
	}
}
