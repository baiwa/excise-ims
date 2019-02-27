package th.go.excise.ims.ia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
import th.go.excise.ims.ia.persistence.entity.IaRiskQtnConfig;
import th.go.excise.ims.ia.service.Int0201Service;
import th.go.excise.ims.ia.vo.Int0201FormVo;
import th.go.excise.ims.ia.vo.Int0201FormVo2;
import th.go.excise.ims.ia.vo.Int0201Vo;

@Controller
@RequestMapping("/api/ia/int0201")
public class Int0201Controller {
	private static final Logger logger = LoggerFactory.getLogger(Int0201Controller.class);

	@Autowired
	private Int0201Service int0201Service;

	@PostMapping("/find-qtnside-by-id")
	@ResponseBody
	public ResponseData<List<IaQuestionnaireSide>> findQtnSideById(@RequestBody Int0201FormVo request) {
		logger.info("find-by-id IaQuestionnaireSide");

		ResponseData<List<IaQuestionnaireSide>> response = new ResponseData<List<IaQuestionnaireSide>>();
		List<IaQuestionnaireSide> data = null;
		try {
			data = int0201Service.findQtnSideById(request);
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
	public ResponseData<Int0201Vo> findQtnSideDtlById(@RequestBody Int0201FormVo2 request) {
		logger.info("find-by-id IaQuestionnaireSideDtl");

		ResponseData<Int0201Vo> response = new ResponseData<Int0201Vo>();
		Int0201Vo data = null;
		try {
			data = int0201Service.findQtnSideDtlById(request);
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

	@PostMapping("/send-qtn-form")
	@ResponseBody
	public ResponseData<?> sendQtnForm(@RequestBody Int0201FormVo request) {
		logger.info("send questionaire form");

		ResponseData<?> response = new ResponseData<>();
		try {
			int0201Service.sendQtnForm(request);
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	/*
	 * ==================== == CONFIGS `START`== ====================
	 */
	@GetMapping("/config/{idQtnHdr}")
	@ResponseBody
	public ResponseData<IaRiskQtnConfig> findConfigByIdQtnHdr(@PathVariable("idQtnHdr") String idQtnHdrStr) {
		ResponseData<IaRiskQtnConfig> response = new ResponseData<IaRiskQtnConfig>();
		try {
			IaRiskQtnConfig data = int0201Service.findConfigByIdQtnHdr(idQtnHdrStr);
			response.setData(data);
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Int0201Controller::findConfigByIdQtnHdr => ", e);
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PutMapping("/config/")
	@ResponseBody
	public ResponseData<IaRiskQtnConfig> updateConfig(@RequestBody IaRiskQtnConfig request) {
		ResponseData<IaRiskQtnConfig> response = new ResponseData<IaRiskQtnConfig>();
		try {
			IaRiskQtnConfig data = int0201Service.updateConfig(request);
			response.setData(data);
			response.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Int0201Controller::updateConfig => ", e);
			response.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/config/")
	@ResponseBody
	public ResponseData<IaRiskQtnConfig> saveConfig(@RequestBody IaRiskQtnConfig request) {
		ResponseData<IaRiskQtnConfig> response = new ResponseData<IaRiskQtnConfig>();
		try {
			IaRiskQtnConfig data = int0201Service.saveConfig(request);
			response.setData(data);
			response.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Int0201Controller::saveConfig => ", e);
			response.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	/*
	 * ==================== == CONFIGS `END`== ====================
	 */

}
