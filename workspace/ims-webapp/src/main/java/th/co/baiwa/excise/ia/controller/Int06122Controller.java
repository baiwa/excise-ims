package th.co.baiwa.excise.ia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import th.co.baiwa.excise.ia.persistence.entity.Expenses;
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

}
