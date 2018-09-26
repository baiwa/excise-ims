package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.ia.persistence.entity.AllocatedBudget;
import th.co.baiwa.excise.ia.persistence.entity.PublicUtility;
import th.co.baiwa.excise.ia.persistence.vo.Int068FormVo;
import th.co.baiwa.excise.ia.service.Int068Service;

@Controller
@RequestMapping("api/ia/int068")
public class Int068Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Int068Service int068Service;
	
	@PostMapping("/saveAB")
	@ResponseBody
	public CommonMessage<AllocatedBudget> saveAB(@RequestBody AllocatedBudget ab){
		logger.info("AB_AllocatedBudget: {}",ab.getAllocatedBudget());
		return int068Service.saveAB(ab);
	}
	
	@PostMapping("/savePU")
	@ResponseBody
	public CommonMessage<PublicUtility> savePU(@RequestBody List<Int068FormVo> vo){
//		logger.info("PU_AllocatedBudget: {}", vo.getAllocatedBudgetId());

		return int068Service.savePU(vo);
	}

}
