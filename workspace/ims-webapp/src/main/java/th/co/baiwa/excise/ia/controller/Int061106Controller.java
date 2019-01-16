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
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.entity.DisbursementRequest;
import th.co.baiwa.excise.ia.persistence.vo.Int061105FormSearchVo;
import th.co.baiwa.excise.ia.persistence.vo.Int061106FormVo;
import th.co.baiwa.excise.ia.service.Int061106Service;

@Controller
@RequestMapping("api/ia/int061106")
public class Int061106Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Int061106Service int061106Service;

	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<DisbursementRequest> save(@ModelAttribute Int061106FormVo from) {
		return int061106Service.search(from);
	}

	@PostMapping("/edit")
	@ResponseBody
	public CommonMessage<Long> edit(@RequestBody Int061106FormVo from) {

		Long id = 0L;
		CommonMessage<Long> message = new CommonMessage<Long>();
		try {
			int061106Service.edit(from);
			message.setData(id);
		} catch (Exception e) {
			logger.error("Error ! edit ", e);
			message.setMsg(ApplicationCache.getMessage("MSG_00003"));
			return message;
		}
		message.setMsg(ApplicationCache.getMessage("MSG_00002"));
		return message;
	}

	@PostMapping("/add")
	@ResponseBody
	public CommonMessage<Long> add(@RequestBody Int061106FormVo from) {

		Long id = 0L;
		CommonMessage<Long> message = new CommonMessage<Long>();
		try {
			int061106Service.add(from);
			message.setData(id);
		} catch (Exception e) {
			logger.error("Error ! add ", e);
			message.setMsg(ApplicationCache.getMessage("MSG_00003"));
			return message;
		}
		message.setMsg(ApplicationCache.getMessage("MSG_00002"));
		return message;
	}

	@PostMapping("/approve")
	@ResponseBody
	public CommonMessage<Long> approve(@RequestBody Int061106FormVo from) {
		Long id = 0L;
		CommonMessage<Long> message = new CommonMessage<Long>();
		try {
			int061106Service.approve(from);
			message.setData(id);
		} catch (Exception e) {
			logger.error("Error ! approve ", e);
			message.setMsg(ApplicationCache.getMessage("MSG_00003"));
			return message;
		}
		message.setMsg(ApplicationCache.getMessage("MSG_00002"));
		return message;
	}


}
