package th.co.baiwa.excise.cop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.cop.persistence.vo.Cop091FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop091Vo;
import th.co.baiwa.excise.cop.service.Cop091Service;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int09111And3FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int091FormVo;

@Controller
@RequestMapping("api/cop/cop091")
public class Cop091Controller {

	private static Logger log = LoggerFactory.getLogger(Cop091Controller.class);
	
	@Autowired
	private Cop091Service cop091Service;
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Cop091Vo> list(@RequestBody Cop091FormVo formVo){
		DataTableAjax<Cop091Vo> list = null;
		log.info("formVo.getSearchFlag() {}",formVo.getSearchFlag());
		try {
			 list = cop091Service.findAll(formVo);
			 log.info("Data list {} row",list.getData().size());
		} catch (Exception e) {
			log.error("Error ! findAll",e);
		}
		
		return list;
	}
	
	@PostMapping("/list2")
	@ResponseBody
	public DataTableAjax<Cop091Vo> list2(@RequestBody Cop091FormVo formVo){
		DataTableAjax<Cop091Vo> list = null;
		log.info("formVo.getSearchFlag() {}",formVo.getSearchFlag());
		try {
			 list = cop091Service.findAll2(formVo);
			 log.info("Data list2 {} row",list.getData().size());
		} catch (Exception e) {
			log.error("Error ! findAll2",e);
		}
		
		return list;
	}

}
