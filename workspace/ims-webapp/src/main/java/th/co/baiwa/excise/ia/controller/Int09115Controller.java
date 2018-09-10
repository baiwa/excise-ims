package th.co.baiwa.excise.ia.controller;

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
import th.co.baiwa.excise.ia.persistence.vo.Int09115FormVo;
import th.co.baiwa.excise.ia.service.Int09115Service;

@Controller
@RequestMapping("api/ia/int09115")
public class Int09115Controller {

	private static Logger log = LoggerFactory.getLogger(Int09115Controller.class);
	
	@Autowired
	private Int09115Service int09115Service;
	
	@PostMapping("/save")
	@ResponseBody
	public Message add(@RequestBody Int09115FormVo formVo){
		
		try {
			int09115Service.add(formVo);
			 
		} catch (Exception e) {
			log.error("Error ! add ",e);
			return ApplicationCache.getMessage("MSG_00003");
		}
		return ApplicationCache.getMessage("MSG_00002");
	}

	

}
