package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.ia.persistence.entity.HealthCareWelFareEntity;
import th.co.baiwa.excise.ia.persistence.entity.MedicalReceipt;
import th.co.baiwa.excise.ia.persistence.vo.Int061102FormVo;
import th.co.baiwa.excise.ia.service.Int061102Service;

@Controller
@RequestMapping("api/ia/int061102")
public class Int061102Controller {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Int061102Service int061102Service;

	@PostMapping("/save")
	@ResponseBody
	public HealthCareWelFareEntity save(@RequestBody Int061102FormVo int061102FormVo) {
		HealthCareWelFareEntity data = new HealthCareWelFareEntity();
		try {
			data = int061102Service.save(int061102FormVo);
			int061102FormVo.setId(data.getId());
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return data;
	}

	@PostMapping("/upload")
	@ResponseBody
	public Message upload(@ModelAttribute Int061102FormVo int061102FormVo) {
		Message msg;
		msg = ApplicationCache.getMessage("MSG_00002");
		try {
			int061102FormVo.setType("RH");
			int061102Service.upload(int061102FormVo);
			msg = ApplicationCache.getMessage("MSG_00002");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return msg;
	}
	
	@GetMapping("/hospital")
	@ResponseBody
	public List<Lov> hospital() {
		return int061102Service.hospital();
	}
	
	@PostMapping("/receipt/save")
	@ResponseBody
	public Message receiptSave(@RequestBody List<MedicalReceipt> req) {
		Message msg;
		msg = ApplicationCache.getMessage("MSG_00002");
		try {
			int061102Service.saveReceipt(req);
			msg = ApplicationCache.getMessage("MSG_00002");
		} catch(Exception e) {
			e.printStackTrace();
			ApplicationCache.getMessage("MSG_00003");
		}
		return msg;
	}

}
