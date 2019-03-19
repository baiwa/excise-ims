package th.go.excise.ims.oa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.oa.persistence.entity.OaCustomer;
import th.go.excise.ims.oa.service.Oa0207Service;
import th.go.excise.ims.oa.vo.Oa0207Vo;

@Controller
@RequestMapping("/api/oa/02/07")
public class Oa0207Controller {

	private static final Logger logger = LoggerFactory.getLogger(Oa0207Controller.class);

	@Autowired
	private Oa0207Service oa0207Service;

	@PostMapping("/filter")
	@ResponseBody
	public DataTableAjax<OaCustomer> filterByCriteria(@RequestBody Oa0207Vo request) {
		DataTableAjax<OaCustomer> response = new DataTableAjax<>();
		try {
			response = oa0207Service.filterByCriteria(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Oa0207Controller::filterByCriteria => ", e);
		}
		return response;
	}
	
}
