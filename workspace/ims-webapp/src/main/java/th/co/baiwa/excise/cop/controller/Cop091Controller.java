package th.co.baiwa.excise.cop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.cop.persistence.entity.CopCheckFiscalReport;
import th.co.baiwa.excise.cop.persistence.vo.Cop091FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop091Vo;
import th.co.baiwa.excise.cop.service.Cop091Service;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;

@Controller
@RequestMapping("api/cop/cop091")
public class Cop091Controller {

	private static Logger log = LoggerFactory.getLogger(Cop091Controller.class);

	@Autowired
	private Cop091Service cop091Service;

	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Cop091Vo> list(@RequestBody Cop091FormVo formVo) {
		DataTableAjax<Cop091Vo> list = null;
		log.info("formVo.getSearchFlag() {}", formVo.getSearchFlag());
		try {
			list = cop091Service.findAll(formVo);
			log.info("Data list {} row", list.getData().size());
		} catch (Exception e) {
			log.error("Error ! findAll", e);
		}

		return list;
	}

	@PostMapping("/list2")
	@ResponseBody
	public DataTableAjax<Cop091Vo> list2(@RequestBody Cop091FormVo formVo) {
		DataTableAjax<Cop091Vo> list = null;
		log.info("formVo.getSearchFlag() {}", formVo.getSearchFlag());
		try {
			list = cop091Service.findAll2(formVo);
			log.info("Data list2 {} row", list.getData().size());
		} catch (Exception e) {
			log.error("Error ! findAll2", e);
		}

		return list;
	}

	@PostMapping("/dataReport")
	@ResponseBody
	public CopCheckFiscalReport dataReport(@RequestBody Long id) {
		return cop091Service.dataReport(id);

	}
}
