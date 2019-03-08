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
import th.go.excise.ims.ia.service.Int030406Service;
import th.go.excise.ims.ia.vo.Int030406FormVo;
import th.go.excise.ims.ia.vo.Int030406Vo;

@Controller
@RequestMapping("/api/ia/int03/04/06")
public class Int030406Controller {
	@Autowired
	private Int030406Service int030406Service;
	
	private Logger logger = LoggerFactory.getLogger(Int030406Controller.class);
	
	@PostMapping("/checkPeriodList")
	@ResponseBody
	public DataTableAjax<Int030406Vo> systemUnworkingList(@RequestBody Int030406FormVo form) {
		DataTableAjax<Int030406Vo> response = new DataTableAjax<Int030406Vo>();
		List<Int030406Vo> checkPeriodList = new ArrayList<Int030406Vo>();

		try {
			checkPeriodList = int030406Service.checkPeriodList(form);
			response.setData(checkPeriodList);

		} catch (Exception e) {
			logger.error("Int030404Controller checkPeriodList : ", e);
		}
		return response;
	}
}
