package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.entity.Condition;
import th.co.baiwa.excise.ia.persistence.repository.ConditionRepository;

@Service
public class ConditionService {
	
	private Logger logger = LoggerFactory.getLogger(ConditionService.class);
	@Autowired
	private ConditionRepository conditionRepository;
	public void insertCondition(Condition condition) {
		
			conditionRepository.save(condition);
		
	}
	
	public List<Condition> findConditionByParentId(Long parentId , String riskType , String page){
		logger.info("insertCondition in "+ parentId);
		return conditionRepository.findConditionByParentId(parentId, riskType, page);
	}
}
