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
import th.co.baiwa.excise.ia.persistence.entity.DisbursementRequest;
import th.co.baiwa.excise.ia.service.Int06115Service;

@Controller
@RequestMapping("api/ia/int06115")
public class Int06115Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Int06115Service int06115Service;

	@PostMapping("/save")
	@ResponseBody
	public Message save(@RequestBody DisbursementRequest en) {
		logger.info("Saved to saveProcurement");
		try {
			int06115Service.save(en);
		} catch (Exception e) {
			return ApplicationCache.getMessage("MSG_00003");
		}
		return ApplicationCache.getMessage("MSG_00002");
	}

}
