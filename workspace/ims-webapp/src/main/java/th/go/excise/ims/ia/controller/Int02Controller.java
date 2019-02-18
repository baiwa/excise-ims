package th.go.excise.ims.ia.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.preferences.rest.controller.MessageRestController;
import th.go.excise.ims.ia.service.Int02Service;
import th.go.excise.ims.ia.vo.Int02FormVo;
import th.go.excise.ims.ia.vo.Int02Vo;

@Controller
@RequestMapping("/api/ia/questionnaire-hdr")
public class Int02Controller {
	private static final Logger logger = LoggerFactory.getLogger(MessageRestController.class);
	
	@Autowired
	private Int02Service iaQuestionnaireHdrService;
	
	@PostMapping("/filter-qtn-hdr")
	@ResponseBody
	public DataTableAjax<Int02Vo> filterQtnHdr(@RequestBody Int02FormVo request) {
		logger.info("filter Datatable int02");
		List<Int02Vo> data = new ArrayList<Int02Vo>();
		DataTableAjax<Int02Vo> dataTableAjax = new DataTableAjax<Int02Vo>();
		try {
			data = iaQuestionnaireHdrService.filterQtnHdr(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(data.size());
		return dataTableAjax;
	}
}
