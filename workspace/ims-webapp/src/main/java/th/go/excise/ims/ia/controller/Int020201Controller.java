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
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
import th.go.excise.ims.ia.service.Int020201Service;
import th.go.excise.ims.ia.vo.Int0201FormVo2;
import th.go.excise.ims.ia.vo.Int0201Vo;
import th.go.excise.ims.ia.vo.Int020201DtlVo;
import th.go.excise.ims.ia.vo.Int020201JoinVo;
import th.go.excise.ims.ia.vo.Int020201SidesVo;
import th.go.excise.ims.ia.vo.Int020201Vo;

@Controller
@RequestMapping("/api/ia/int020201")
public class Int020201Controller {
	private static final Logger logger = LoggerFactory.getLogger(Int020201Controller.class);

	@Autowired
	private Int020201Service int020201Service;
	
	@PostMapping("/find-qtnside-by-id")
	@ResponseBody
	public ResponseData<List<IaQuestionnaireSide>> findQtnSideById(@RequestBody Int020201SidesVo request) {
		logger.info("find-by-id IaQuestionnaireSide");

		ResponseData<List<IaQuestionnaireSide>> response = new ResponseData<List<IaQuestionnaireSide>>();
		List<IaQuestionnaireSide> data = null;
		try {
			data = int020201Service.findQtnSideById(request);
			response.setData(data);
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}
	
	@PostMapping("/find-qtnside-dtl-by-id")
	@ResponseBody
	public ResponseData<Int020201Vo> findQtnSideDtlById(@RequestBody Int020201SidesVo request) {
		logger.info("find-by-id IaQuestionnaireSideDtl");

		ResponseData<Int020201Vo> response = new ResponseData<Int020201Vo>();
		Int020201Vo data = null;
		try {
			data = int020201Service.findQtnSideDtlById(request);
			response.setData(data);
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

}
