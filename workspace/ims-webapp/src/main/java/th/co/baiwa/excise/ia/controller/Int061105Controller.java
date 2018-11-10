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
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.entity.DisbursementRequest;
import th.co.baiwa.excise.ia.persistence.vo.Int061105FormSearchVo;
import th.co.baiwa.excise.ia.service.Int061105Service;

@Controller
@RequestMapping("api/ia/int061105")
public class Int061105Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Int061105Service int061105Service;

	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<DisbursementRequest> save(@ModelAttribute Int061105FormSearchVo ids) {
		return int061105Service.search(ids);
	}
	
	@PostMapping("/approve")
	@ResponseBody
	public Message approve(@RequestBody Int061105FormSearchVo ids) {
		return int061105Service.approve(ids);
	}

	// @PostMapping("/save")
	// @ResponseBody
	// public Message save(@RequestBody DisbursementRequest en) {
	// logger.info("Saved to saveProcurement");
	// try {
	// int06115Service.save(en);
	// } catch (Exception e) {
	// return ApplicationCache.getMessage("MSG_00003");
	// }
	// return ApplicationCache.getMessage("MSG_00002");
	// }

}
