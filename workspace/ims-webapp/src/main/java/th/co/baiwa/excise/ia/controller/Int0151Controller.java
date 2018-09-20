package th.co.baiwa.excise.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int091FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int091Vo;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncFri8020;

@Controller
@RequestMapping("api/ia/int0151")
public class Int0151Controller {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Int091Vo> list(@RequestBody Int091FormVo formVo){
		DataTableAjax<Int091Vo> list = null;
		log.info("formVo.getSearchFlag() {}",formVo.getSearchFlag());
		try {
			 //list = int091Service.findAll(formVo);
			 log.info("Data {} row",list.getData().size());
		} catch (Exception e) {
			log.error("Error ! ==> Int091Controller method findAll");
		}
		
		return list;
	}
}
