package th.co.baiwa.excise.ia.controller;

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

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int0171FormVo;
import th.co.baiwa.excise.ia.service.Int0171Service;
import th.co.baiwa.excise.ws.entity.response.licfri6010.LicenseList6010;

@Controller
@RequestMapping("api/ia/int0171")
public class Int0171Controller {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Int0171Service int0171Service;
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<LicenseList6010> list(@RequestBody Int0171FormVo formVo){
		
		DataTableAjax<LicenseList6010> licenseList6010List =  new DataTableAjax<>();
		try {
			licenseList6010List = int0171Service.licFri6010(formVo);
			
		} catch (Exception e) {
			log.error("Error ! ==> Int0161Controller method licFri6010",e);
		}
		
		return licenseList6010List;
	}
	
	@GetMapping("/exportFile")
	@ResponseBody
	public  void exportFile(@ModelAttribute Int0171FormVo formVo, HttpServletResponse response) throws Exception {
		try {
			int0171Service.exportFile(formVo, response);
		} catch (Exception e) {
			log.error("Error ! ==> exportFile method exportFile",e);
		}
		
	}
}
