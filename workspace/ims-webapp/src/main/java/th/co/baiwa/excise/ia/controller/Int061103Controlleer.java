package th.co.baiwa.excise.ia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.ia.persistence.vo.Int061103Vo;
import th.co.baiwa.excise.ia.service.Int061103Service;

@Controller
@RequestMapping("api/ia/int061103")
public class Int061103Controlleer {

	 @Autowired
	 private Int061103Service int061103Service;
	 
	 @PostMapping("/save")
	 @ResponseBody
	 public Int061103Vo save(@RequestBody Int061103Vo tuitionFee){
		 return tuitionFee;
	 }
}
