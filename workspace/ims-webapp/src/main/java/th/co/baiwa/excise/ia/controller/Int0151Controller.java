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

import th.co.baiwa.excise.ia.persistence.vo.Int0151FormVo;
import th.co.baiwa.excise.ia.service.Int0151Service;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncomeList;

@Controller
@RequestMapping("api/ia/int0151")
public class Int0151Controller {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Int0151Service int0151Service;
	
	@PostMapping("/list")
	@ResponseBody
	public List<IncomeList> list(@RequestBody Int0151FormVo formVo){
		
		List<IncomeList> licenseList8020List = new ArrayList<IncomeList>();
		try {
			licenseList8020List = int0151Service.licFri8020(formVo);
			
		} catch (Exception e) {
			log.error("Error ! ==> Int0151Controller method licFri8020",e);
		}
		
		return licenseList8020List;
	}
	
	@GetMapping("/exportFile")
	@ResponseBody
	public  void exportFile(@ModelAttribute Int0151FormVo formVo, HttpServletResponse response) throws Exception {
		try {
			int0151Service.exportFile(formVo, response);
		} catch (Exception e) {
			log.error("Error ! ==> exportFile method exportFile",e);
		}
		
	}
}
