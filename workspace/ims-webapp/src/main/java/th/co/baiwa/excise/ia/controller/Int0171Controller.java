package th.co.baiwa.excise.ia.controller;

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

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
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
			log.error("Error ! ==> Int0161Controller method findAll",e);
		}
		
		return licenseList6010List;
	}
}
