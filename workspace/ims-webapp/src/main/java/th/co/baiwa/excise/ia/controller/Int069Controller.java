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

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.ia.persistence.entity.TransferList;
import th.co.baiwa.excise.ia.persistence.vo.Int069FormVo;
import th.co.baiwa.excise.ia.service.Int069Service;

@Controller
@RequestMapping("api/ia/int069")
public class Int069Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Int069Service int069Service;

	@PostMapping("/filter")
	@ResponseBody
	public DataTableAjax<TransferList> filter(@ModelAttribute Int069FormVo en) {
		logger.info("Filter BudgetList Int069!!");
		return int069Service.filterByform(en);
	}

	@PostMapping("/delete")
	@ResponseBody
	public Message delete(@RequestBody TransferList vo) {
		Message msg = ApplicationCache.getMessage("MSG_00003");

		try {
			int069Service.delete(vo);
			msg = ApplicationCache.getMessage("MSG_00002");
		} catch (Exception e) {
			e.printStackTrace();
			msg = ApplicationCache.getMessage("MSG_00003");
		}

		return msg;
	}

}