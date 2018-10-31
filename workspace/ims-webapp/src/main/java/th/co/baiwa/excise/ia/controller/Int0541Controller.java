package th.co.baiwa.excise.ia.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.ia.persistence.entity.IaProcurement;
import th.co.baiwa.excise.ia.persistence.entity.IaProcurementList;
import th.co.baiwa.excise.ia.persistence.vo.Int0541Vo;
import th.co.baiwa.excise.ia.service.Int0541Service;

@Controller
@RequestMapping("api/ia/int0541")
public class Int0541Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Int0541Service int0541Service;
	
	@PostMapping("/upload")
	@ResponseBody
	public CommonMessage<IaProcurement> uploadProcurement(@ModelAttribute Int0541Vo vo) throws IOException {
		logger.info("upload to uploadProcurement {}",vo.getApproveDatePlan());
		
		return int0541Service.saveProcurement(vo);
	}
	
	@PostMapping("/savePcmList")
	@ResponseBody
		public Message saveProcurement(@RequestBody List<IaProcurementList> vo) {
		logger.info("Saved to saveProcurement");
		try {
			int0541Service.savePcmList(vo);
		} catch (Exception e) {
			return ApplicationCache.getMessage("MSG_00003");
		}
		return ApplicationCache.getMessage("MSG_00002");
	}
	
	@PostMapping("/findByid")
	@ResponseBody
	public Int0541Vo findByid(@RequestBody IaProcurement pcm){
		logger.info("findByid {}",pcm.getProcurementId());
		return int0541Service.findByid(pcm);
	}

}
