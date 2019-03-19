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
import th.go.excise.ims.oa.service.Oa0107Service;
import th.go.excise.ims.oa.vo.Oa0107Vo;

@Controller
@RequestMapping("/api/oa/01/07")
public class Oa0107Controller {

	private static final Logger logger = LoggerFactory.getLogger(Oa0107Controller.class);

	@Autowired
	private Oa0107Service oa0107Service;

	@PostMapping("/filter")
	@ResponseBody
	public DataTableAjax<OaCustomer> filterByCriteria(@RequestBody Oa0107Vo request) {
		DataTableAjax<OaCustomer> response = new DataTableAjax<>();
		try {
			response = oa0107Service.filterByCriteria(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Oa0107Controller::filterByCriteria => ", e);
		}
		return response;
	}
	
}
