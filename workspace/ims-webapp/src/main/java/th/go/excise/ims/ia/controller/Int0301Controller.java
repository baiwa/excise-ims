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


import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.service.Int0301Service;
import th.go.excise.ims.ia.vo.Int0301FormVo;

@Controller
@RequestMapping("/api/ia/int03/01")
public class Int0301Controller {
	
	private Logger logger = LoggerFactory.getLogger(Int0301Controller.class);
	
	@Autowired
	private Int0301Service Int0301Service;

	@PostMapping("/list")
	@ResponseBody
	public ResponseData<List<IaRiskFactors>> list(@RequestBody Int0301FormVo form) {
		ResponseData<List<IaRiskFactors>> response = new ResponseData<List<IaRiskFactors>>();
		List<IaRiskFactors> iaRiskFactorsList = new ArrayList<IaRiskFactors>();
		try {	
			iaRiskFactorsList = Int0301Service.list(form);
			response.setData(iaRiskFactorsList);
			response.setMessage("SUCCESS");
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int0301Controller : " , e);
			response.setMessage("ERROR");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
//	@PostMapping("/saveRiskFactorsLevel")
//	@ResponseBody
//	public ResponseData<String> saveRiskFactorsLevel(@RequestBody Int0301FormVo form) {
//		ResponseData<String> response = new ResponseData<String>();
//		try {	
//			Int0301Service.saveRiskFactorsLevel(form);
//			response.setData("SUCCESS");
//			response.setMessage("SUCCESS");
//			response.setStatus(RESPONSE_STATUS.SUCCESS);
//		} catch (Exception e) {
//			logger.error("Int0301Controller : " , e);
//			response.setMessage("ERROR");
//			response.setStatus(RESPONSE_STATUS.FAILED);
//		}
//		return response;
//	}

}
