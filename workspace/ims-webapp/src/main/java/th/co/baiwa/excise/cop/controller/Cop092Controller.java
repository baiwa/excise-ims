package th.co.baiwa.excise.cop.controller;

import java.math.BigDecimal;

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
import th.co.baiwa.excise.cop.persistence.vo.Cop064FormVo;
import th.co.baiwa.excise.cop.service.Cop092Service;
import th.co.baiwa.excise.domain.CommonMessage;

@Controller
@RequestMapping("api/cop/cop092")
public class Cop092Controller {
	
	private static Logger log = LoggerFactory.getLogger(Cop071Controller.class);

	@Autowired
	private Cop092Service cop092Service;
	
	
	@PostMapping("/updateFlag")
	@ResponseBody
	public CommonMessage<Long> upupdateFlagdate(@RequestBody Cop064FormVo formVo) {
		Long id = 0L;
		CommonMessage<Long> message = new CommonMessage<Long>();
		try {
			cop092Service.updateFlag(formVo);	
			message.setData(id);
		} catch (Exception e) {
			log.error("Error ! upupdateFlagdate ",e);
			message.setMsg(ApplicationCache.getMessage("MSG_00003"));
			return message;
		}
		message.setMsg(ApplicationCache.getMessage("MSG_00002"));
		return message;
	}
		
	

	
}
