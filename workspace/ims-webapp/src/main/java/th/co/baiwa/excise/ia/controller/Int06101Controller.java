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

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.excise.ia.persistence.entity.BudgetList;
import th.co.baiwa.excise.ia.service.Int06101Service;


@Controller
@RequestMapping("api/ia/int06101")
public class Int06101Controller {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Int06101Service int06101Service;
	
	@PostMapping("/pmmethod")
	@ResponseBody
	public List<Lov> pmmethod() {
		return int06101Service.pmmethod();
	}
	
	@PostMapping("/activity")
	@ResponseBody
	public List<Lov> activity() {
		return int06101Service.activity();
	}
	
	@PostMapping("/budge")
	@ResponseBody
	public List<Lov> budge() {
		return int06101Service.budge();
	}
	
	@PostMapping("/budged")
	@ResponseBody
	public List<BudgetList> budged() {
		return int06101Service.budged();
	}
	
	
	@PostMapping("/category")
	@ResponseBody
	public List<BudgetList> getCtgBudget(@RequestBody BudgetList budgetId){
		logger.info("category Int06101!!");
		return int06101Service.getCtgBudget(budgetId);
	}
	
	@PostMapping("/list")
	@ResponseBody
	public List<BudgetList> getListName(@RequestBody BudgetList categoryId){
		logger.info("list Int06101!!");
		return int06101Service.getListName(categoryId);
	}
	

}