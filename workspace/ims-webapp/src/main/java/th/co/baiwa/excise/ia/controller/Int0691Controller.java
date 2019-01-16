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
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.ia.persistence.entity.BudgetList;
import th.co.baiwa.excise.ia.persistence.entity.TransferList;
import th.co.baiwa.excise.ia.service.Int0691Service;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 

@Controller
@RequestMapping("api/ia/int0691")
public class Int0691Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Int0691Service int0691Service;
	
	@PostMapping("/save")
	@ResponseBody
	public Message save(@RequestBody List<TransferList> entity){
		logger.info("SAVE DATATABLE Int0691!!");
		Message msg = null;
		try {
			if(BeanUtils.isNotEmpty(entity)) {
				int0691Service.saveTable(entity);
				msg = ApplicationCache.getMessage("MSG_00002");
			}
		} catch (Exception e) {
			msg = ApplicationCache.getMessage("MSG_00003");
		}
		
		return msg;
	}
	
	@PostMapping("/queryBudgetList")
	@ResponseBody
	public List<BudgetList> queryBudgetList(){
		logger.info("QUERY BudgetList Int0691!!");
		return int0691Service.queryBudgetList();
	}
	
	@PostMapping("/queryByIdToEdit")
	@ResponseBody
	public TransferList queryByIdToEdit(@RequestBody TransferList en){
		logger.info("QUERY TransferList Int0691!!");
		return int0691Service.queryByIdToEdit(en);
	}
	
	@PostMapping("/update")
	@ResponseBody
	public Message update(@RequestBody TransferList entity){
		logger.info("UPDATE Int0691!!");
		Message msg = null;
		try {
			if(BeanUtils.isNotEmpty(entity)) {
				int0691Service.update(entity);
				msg = ApplicationCache.getMessage("MSG_00002");
			}
		} catch (Exception e) {
			msg = ApplicationCache.getMessage("MSG_00003");
		}
		
		return msg;
	}

	@PostMapping("/quryBudgetName")
	@ResponseBody
	public List<BudgetList> quryBudgetName(){
		logger.info("QUERY BudgetName Int0691!!");
		return int0691Service.quryBudgetName();
	}
	
	@PostMapping("/getCtgBudget")
	@ResponseBody
	public List<BudgetList> getCtgBudget(@RequestBody BudgetList budgetId){
		logger.info("getCtgBudget Int0691!!");
		return int0691Service.getCtgBudget(budgetId);
	}
	
	@PostMapping("/getListName")
	@ResponseBody
	public List<BudgetList> getListName(@RequestBody BudgetList categoryId){
		logger.info("getListName Int0691!!");
		return int0691Service.getListName(categoryId);
	}
	
}
