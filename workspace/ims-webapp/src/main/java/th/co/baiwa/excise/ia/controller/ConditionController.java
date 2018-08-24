
package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.excise.ia.persistence.entity.Condition;
import th.co.baiwa.excise.ia.persistence.vo.ConditionFormVo;
import th.co.baiwa.excise.ia.service.ConditionService;

@Controller
@RequestMapping("api/ia/condition")
public class ConditionController {

	private Logger logger = LoggerFactory.getLogger(ConditionController.class);

	@Autowired
	private ConditionService conditionService;
	
	@PostMapping("/findConditionByParentId")
	@ResponseBody
	public List<Condition> findConditionByParentId(@RequestBody Condition condition) {
		logger.info("findConditionByParentId" + (condition.getParentId()));
		return conditionService.findConditionByParentId(condition.getParentId() , condition.getRiskType() , condition.getPage());
	}
	
	
	@PostMapping("/insertCondition")
	@ResponseBody
	public Message insertCondition(@RequestBody ConditionFormVo condition) {
		return conditionService.insertCondition(condition);
	}

}
