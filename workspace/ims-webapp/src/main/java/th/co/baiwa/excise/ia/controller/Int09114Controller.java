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
import th.co.baiwa.excise.ia.persistence.vo.Int09114FormVo;
import th.co.baiwa.excise.ia.service.Int09114Service;

@Controller
@RequestMapping("api/ia/int09114")
public class Int09114Controller {

	private static Logger log = LoggerFactory.getLogger(Int09114Controller.class);
	
	@Autowired
	private Int09114Service int09114Service;
	
	@PostMapping("/save")
	@ResponseBody
	public Message add(@RequestBody Int09114FormVo formVo){
		
		try {
			int09114Service.add(formVo);
			 
		} catch (Exception e) {
			log.error("Error ! add ",e);
			return ApplicationCache.getMessage("MSG_00003");
		}
		return ApplicationCache.getMessage("MSG_00002");
	}

	

}
