package th.co.baiwa.excise.cop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.cop.persistence.vo.Cop071FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop071Vo;
import th.co.baiwa.excise.cop.service.Cop071Service;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;

@Controller
@RequestMapping("api/cop/cop071")
public class Cop071Controller {

	private static Logger log = LoggerFactory.getLogger(Cop071Controller.class);
	
	@Autowired
	private Cop071Service cop071Service;
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Cop071Vo> list(@RequestBody Cop071FormVo formVo){
		DataTableAjax<Cop071Vo> list = null;
		log.info("formVo.getSearchFlag() {}",formVo.getSearchFlag());
		try {
			 list = cop071Service.findAll(formVo);
			 log.info("Data {} row",list.getData().size());
		} catch (Exception e) {
			log.error("Error ! ==> Cop071Controller method findAll");
		}
		
		return list;
	}
	
	@PostMapping("/edit")
	@ResponseBody
	public CommonMessage<Cop071FormVo> edit(@RequestBody Cop071FormVo formVo){

		CommonMessage<Cop071FormVo> message = new CommonMessage<Cop071FormVo>();
		try {
			cop071Service.checkAddAndEdit(formVo);	
			message.setData(formVo);
		} catch (Exception e) {
			log.error("Error ! edit ",e);
			message.setMsg(ApplicationCache.getMessage("MSG_00003"));
			return message;
		}
		message.setMsg(ApplicationCache.getMessage("MSG_00002"));
		return message;
	}

	

}
