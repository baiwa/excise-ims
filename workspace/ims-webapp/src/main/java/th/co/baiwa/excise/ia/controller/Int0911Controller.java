package th.co.baiwa.excise.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.entity.Budget;
import th.co.baiwa.excise.ia.persistence.vo.Int0911FormVo;
import th.co.baiwa.excise.ia.service.Int0911Service;

@Controller
@RequestMapping("api/ia/int0911")
public class Int0911Controller {

	private static Logger logger = LoggerFactory.getLogger(Int0911Controller.class);
	
	@Autowired
	private Int0911Service int0911Service;
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Budget> list(@RequestBody Int0911FormVo formVo){
		DataTableAjax<Budget> list = null;
		//try {
			 list = int0911Service.findAll(formVo);
		//	 logger.info("Data {} row",list.getData().size());
		//} catch (Exception e) {
		//.error("Error ! ==> Int0911Controller method findAll");
		//}
		
		return list;
	}
	
}
