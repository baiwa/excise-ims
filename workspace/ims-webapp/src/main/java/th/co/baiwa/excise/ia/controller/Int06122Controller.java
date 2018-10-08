package th.co.baiwa.excise.ia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import th.co.baiwa.excise.ia.persistence.entity.Expenses;
import th.co.baiwa.excise.ia.persistence.vo.Int06121Vo;
import th.co.baiwa.excise.ia.service.Int06122Service;

@Controller
@RequestMapping("api/ia/int06122")
public class Int06122Controller {

	@Autowired
	private Int06122Service int06122Service;

	@PostMapping("/save")
    @ResponseBody
    public String save(@RequestBody Expenses expenses){
	    return int06122Service.save(expenses);
    }

	@PostMapping("/edit")
	@ResponseBody
	public String edit(@RequestBody Int06121Vo expenses){
		return int06122Service.edit(expenses);
	}
	
	@PostMapping("/checkData")
	@ResponseBody
	public String checkDate(@RequestBody String accountId) {
		return int06122Service.checkData(accountId);		 
	}

}