package th.co.baiwa.excise.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int09213Vo;
import th.co.baiwa.excise.ia.service.Int09213Service;

@Controller
@RequestMapping("api/ia/int09213")
public class Int09213Controller {
	
	private static Logger logger = LoggerFactory.getLogger(Int09213Controller.class);
	
	@Autowired
	private Int09213Service int09213Service;
	
	
	@GetMapping("/list")
	@ResponseBody
	public DataTableAjax<Int09213Vo> list(){
		DataTableAjax<Int09213Vo> list = null;
		try {
			 list = int09213Service.findAll();
			 logger.info("Data {} row",list.getData().size());
		} catch (Exception e) {
			logger.error("Error ! ==> Int0911Controller method findAll");
		}
		
		return list;
	}

}
