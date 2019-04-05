package th.go.excise.ims.oa.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.oa.service.Oa07Service;
import th.go.excise.ims.oa.vo.Oa07FormVo;
import th.go.excise.ims.oa.vo.Oa07Vo;

@Controller
@RequestMapping("/api/oa/07")
public class Oa07Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Oa07Controller.class);
	
	@Autowired
	private Oa07Service oa07Service;
	
	@PostMapping("/reg4000")
	@ResponseBody
	public ResponseData<List<Oa07Vo>> reg4000(@RequestBody Oa07FormVo formVo){
		ResponseData<List<Oa07Vo>> response = new ResponseData<>();
		try {
			List<Oa07Vo> data = oa07Service.reg4000(formVo);
			response.setData(data);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0208Controller::findByBudgetYear ", e);
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

}
