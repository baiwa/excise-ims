package th.co.baiwa.excise.cop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.cop.persistence.vo.Cop064FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop092BudgetFormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop092BudgetVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop092ProductFormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop092ProductVo;
import th.co.baiwa.excise.cop.service.Cop092Service;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;

@Controller
@RequestMapping("api/cop/cop092")
public class Cop092Controller {

	private static Logger log = LoggerFactory.getLogger(Cop071Controller.class);

	@Autowired
	private Cop092Service cop092Service;

	@PostMapping("/updateFlag")
	@ResponseBody
	public CommonMessage<Long> updateFlagdate(@RequestBody Cop064FormVo formVo) {
		Long id = 0L;
		CommonMessage<Long> message = new CommonMessage<Long>();
		try {
			cop092Service.updateFlag(formVo);
			message.setData(id);
		} catch (Exception e) {
			log.error("Error ! upupdateFlagdate ", e);
			message.setMsg(ApplicationCache.getMessage("MSG_00003"));
			return message;
		}
		message.setMsg(ApplicationCache.getMessage("MSG_00002"));
		return message;
	}

	@PostMapping("/product/list")
	@ResponseBody
	public DataTableAjax<Cop092ProductVo> getCop092Product(@RequestBody Cop092ProductFormVo formVo) {
		DataTableAjax<Cop092ProductVo> list = null;
		log.info("formVo.getSearchFlag() {}", formVo.getSearchFlag());
		try {
			list = cop092Service.findProductAll(formVo);
			log.info("Data list {} row", list.getData().size());
		} catch (Exception e) {
			log.error("Error ! findAll", e);
		}
		return list;
	}
	
	@PostMapping("/budget/list")
	@ResponseBody
	public DataTableAjax<Cop092BudgetVo> getCop092Budget(@RequestBody Cop092BudgetFormVo formVo) {
		DataTableAjax<Cop092BudgetVo> list = null;
		log.info("formVo.getSearchFlag() {}", formVo.getSearchFlag());
		try {
			list = cop092Service.findBudgetAll(formVo);
			log.info("Data list {} row", list.getData().size());
		} catch (Exception e) {
			log.error("Error ! findAll", e);
		}
		return list;
	}

}
