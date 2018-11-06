package th.co.baiwa.excise.ia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import th.co.baiwa.excise.ia.service.Int061103Service;

@Controller
@RequestMapping("api/ia/int061103")
public class Int061103Controlleer {

	 @Autowired
	 private Int061103Service int061103Service;
	 
	 
}
