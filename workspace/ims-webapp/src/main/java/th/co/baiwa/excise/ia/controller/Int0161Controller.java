package th.co.baiwa.excise.ia.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int0161FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0161Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int091FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int091Vo;
import th.co.baiwa.excise.ia.service.Int0111Service;
import th.co.baiwa.excise.ia.service.Int0161Service;
import th.co.baiwa.excise.ws.WebServiceExciseService;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncFri8020;
import th.co.baiwa.excise.ws.entity.response.licfri6020.LicFri6020;
import th.co.baiwa.excise.ws.entity.response.licfri6020.LicenseList6020;
import th.co.baiwa.excise.ws.entity.response.licfri6020.ResponseData6020;

@Controller
@RequestMapping("api/ia/int0161")
public class Int0161Controller {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Int0161Service int0161Service;
	
	@PostMapping("/list")
	@ResponseBody
	public List<LicenseList6020> list(@RequestBody Int0161FormVo formVo){
		
		List<LicenseList6020> licenseList6020List = new ArrayList<LicenseList6020>();
		try {
			licenseList6020List = int0161Service.licFri6020(formVo);
			
		} catch (Exception e) {
			log.error("Error ! ==> Int0161Controller method findAll",e);
		}
		
		return licenseList6020List;
	}
}
