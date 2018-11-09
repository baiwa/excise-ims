package th.co.baiwa.excise.ia.controller;

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
import th.co.baiwa.excise.ia.persistence.entity.TuitionFee;
import th.co.baiwa.excise.ia.persistence.vo.Int061103FormUpload;
import th.co.baiwa.excise.ia.persistence.vo.Int061103Vo;
import th.co.baiwa.excise.ia.service.Int061103Service;

@Controller
@RequestMapping("api/ia/int061103")
public class Int061103Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	 @Autowired
	 private Int061103Service int061103Service;
	 
	 @PostMapping("/save")
	 @ResponseBody
	 public TuitionFee save(@RequestBody Int061103Vo tuitionFee){		 
		 return int061103Service.save(tuitionFee); 
	 }
	 
	 @PostMapping("/upload")
		@ResponseBody
		public Message uploadList(@ModelAttribute Int061103FormUpload files) {
			logger.info("upload Int061103");
			Message msg;
			msg = ApplicationCache.getMessage("MSG_00003");
			try {
				int061103Service.uploadFiles(files);
				msg = ApplicationCache.getMessage("MSG_00002");
			} catch (Exception e) {
				e.getStackTrace();
			}
			return msg;
		}
}
