package th.go.excise.ims.ia.controller;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
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
import th.go.excise.ims.ia.persistence.entity.IaEmpWorkingDtl;
import th.go.excise.ims.ia.service.Int090102Service;
import th.go.excise.ims.ia.vo.IaEmpWorkingDtlSaveVo;

@Controller
@RequestMapping("/api/ia/int090102")
public class Int090102Controller {
	private static final Logger logger = LoggerFactory.getLogger(Int090102Controller.class);
	
	@Autowired
	private Int090102Service int090102Service;

	@PostMapping("/save")
	@ResponseBody
	public ResponseData<T> save(@RequestBody IaEmpWorkingDtlSaveVo request) {
		logger.info("save -> Int090102");
		ResponseData<T> responseData = new ResponseData<T>();
		try {
			int090102Service.save(request);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("/get-by-month")
	@ResponseBody
	public ResponseData<List<IaEmpWorkingDtl>> getByMonth(@RequestBody IaEmpWorkingDtlSaveVo request) {
		logger.info("save -> Int090102");
		ResponseData<List<IaEmpWorkingDtl>> responseData = new ResponseData<List<IaEmpWorkingDtl>>();
		try {
			responseData.setData(int090102Service.getByMonth(request));
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
}
