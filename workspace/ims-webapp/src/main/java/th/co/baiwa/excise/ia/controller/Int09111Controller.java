package th.co.baiwa.excise.ia.controller;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
import th.co.baiwa.excise.ia.persistence.vo.Int09111FormVo;
import th.co.baiwa.excise.ia.service.Int09111Service;

@Controller
@RequestMapping("api/ia/int09111")
public class Int09111Controller {

	private static Logger log = LoggerFactory.getLogger(Int09111Controller.class);
	
	@Autowired
	private Int09111Service int09111Service;
	
	@PostMapping("/save")
	@ResponseBody
	public Message add(@RequestBody Int09111FormVo formVo){
		
		try {
			int09111Service.add(formVo);
			 
		} catch (Exception e) {
			log.error("Error ! add ",e);
			return ApplicationCache.getMessage("MSG_00003");
		}
		return ApplicationCache.getMessage("MSG_00002");
	}

	

}
