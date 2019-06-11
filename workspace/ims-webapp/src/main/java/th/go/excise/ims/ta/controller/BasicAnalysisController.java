package th.go.excise.ims.ta.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ta.service.BasicAnalysisService;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;

@Controller
@RequestMapping("/api/ta/basic-analysis")
public class BasicAnalysisController {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisController.class);

	private BasicAnalysisService basicAnalysisService;

	@Autowired
	public BasicAnalysisController(BasicAnalysisService basicAnalysisService) {
		this.basicAnalysisService = basicAnalysisService;
	}

	@PostMapping("/inquiry-{analysisType}-data")
	@ResponseBody
	public DataTableAjax<?> inquiryData(@PathVariable("analysisType") String analysisType, @RequestBody BasicAnalysisFormVo formVo) {
		logger.info("inquiryData analysisType={}", analysisType);

		DataTableAjax<?> response = new DataTableAjax<>();
		try {
			response = basicAnalysisService.inquiry(analysisType, formVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return response;
	}

	@PostMapping("/save")
	@ResponseBody
	public ResponseData<String> saveData(@RequestBody BasicAnalysisFormVo formVo) {
		logger.info("saveData");

		ResponseData<String> response = new ResponseData<>();
		try {
			basicAnalysisService.save(formVo);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(e.getMessage());
			response.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}

		return response;
	}
	
	@PostMapping("/get-ba-number-list/{auditPlanCode}")
	@ResponseBody
	public ResponseData<List<String>> getBaNumberList(@PathVariable("auditPlanCode") String auditPlanCode) {
		logger.info("getBaNumberList auditPlanCode={}", auditPlanCode);
		ResponseData<List<String>> response = new ResponseData<>();
		try {
			List<String> baNumberList = basicAnalysisService.getBaNumberList(auditPlanCode);
			response.setData(baNumberList);
			response.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}

		return response;
	}

}
