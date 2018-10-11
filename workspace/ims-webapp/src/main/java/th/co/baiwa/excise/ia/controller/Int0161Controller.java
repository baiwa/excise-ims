package th.co.baiwa.excise.ia.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.ia.persistence.vo.Int0161FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0171FormVo;
import th.co.baiwa.excise.ia.service.Int0161Service;
import th.co.baiwa.excise.ws.entity.response.licfri6010.LicenseList6010;

@Controller
@RequestMapping("api/ia/int0161")
public class Int0161Controller {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Int0161Service int0161Service;
	
	@PostMapping("/list")
	@ResponseBody
	public List<LicenseList6010> list(@RequestBody Int0161FormVo formVo){
		
		List<LicenseList6010> licenseList6010List = new ArrayList<LicenseList6010>();
		try {
			licenseList6010List = int0161Service.licFri6010(formVo);
			
		} catch (Exception e) {
			log.error("Error ! ==> Int0161Controller method findAll",e);
		}
		
		return licenseList6010List;
	}
	
	@PostMapping("/list2")
	@ResponseBody
	public List<LicenseList6010> list2(@RequestBody Int0161FormVo formVo){
		
		List<LicenseList6010> licenseList6010List = new ArrayList<LicenseList6010>();
		try {
			licenseList6010List = int0161Service.licFri60102(formVo);
			
		} catch (Exception e) {
			log.error("Error ! ==> Int0161Controller method findAll",e);
		}
		
		return licenseList6010List;
	}
	
	@GetMapping("/exportFile")
	@ResponseBody
	public  void exportFile(@ModelAttribute Int0161FormVo formVo, HttpServletResponse response) throws Exception {
		try {
			int0161Service.exportFile(formVo, response);
		} catch (Exception e) {
			log.error("Error ! ==> exportFile method exportFile",e);
		}
		
	}
}
