package th.go.excise.ims.ia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import th.go.excise.ims.ia.service.Int0401Service;

@Controller
@RequestMapping("/api/ia/int04/01")
public class Int0401Controller {

	@Autowired
	private Int0401Service int0401Service;
	
}
