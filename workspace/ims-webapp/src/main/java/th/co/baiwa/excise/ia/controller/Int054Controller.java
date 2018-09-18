package th.co.baiwa.excise.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.entity.IaProcurement;
import th.co.baiwa.excise.ia.persistence.vo.Int0541Vo;
import th.co.baiwa.excise.ia.service.Int054Service;

@Controller
@RequestMapping("api/ia/int054")
public class Int054Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Int054Service int054Service;
	
	@PostMapping("/filterFindIaPcm")
	@ResponseBody
	public ResponseDataTable<Int0541Vo> oderByfilter(IaProcurement pcm) {
		logger.info("Query IaProcurement to filter");
		return int054Service.oderByfilter(pcm);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public Message deletePcm(@PathVariable("id") String idList) {
		return int054Service.deletePcm(idList);
	}
}
