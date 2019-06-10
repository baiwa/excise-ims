package th.go.excise.ims.ia.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ia.service.Int0501Service;
import th.go.excise.ims.ia.vo.Int0501FormVo;
import th.go.excise.ims.ia.vo.Int0501Vo;

@Controller
@RequestMapping("/api/ia/int05/01")
public class Int0501Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Int0501Controller.class);
	
	@Autowired
	private Int0501Service int0501Service;
	
	
	@PostMapping("/listPerson")
	@ResponseBody
	public DataTableAjax<Int0501Vo> listPerson(@RequestBody Int0501FormVo form) {
		DataTableAjax<Int0501Vo> response = new DataTableAjax<Int0501Vo>();
		List<Int0501Vo> personList = new ArrayList<Int0501Vo>();
		try {	
			personList = int0501Service.listPerson(form);
			response.setData(personList);
		} catch (Exception e) {
			logger.error("Int0501Controller : " , e);
		}
		return response;
	}

}
