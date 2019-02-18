package th.go.excise.ims.ta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.BusinessException;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.preferences.constant.MessageConstants;
import th.go.excise.ims.ta.persistence.entity.TaWsCondHdr;
import th.go.excise.ims.ta.service.WorksheetConditionService;

@Controller
@RequestMapping("/api/ta/worksheet-condition")
public class WorksheetConditionController {
	
	@Autowired
	WorksheetConditionService worksheetConditionService;

	@PostMapping("/create-worksheet-condition")
	@ResponseBody
	public ResponseData<String> insertWorkSheet(@RequestBody TaWsCondHdr formVo) {
		ResponseData<String> responseData = new ResponseData<String>();
		try {
			responseData.setData(worksheetConditionService.insertWorkSheet(formVo));
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/find-allhdr")
	@ResponseBody
	public ResponseData<List<TaWsCondHdr>> findAllHdr() {
		ResponseData<List<TaWsCondHdr>> responseData = new ResponseData<List<TaWsCondHdr>>();
		try {
			List<TaWsCondHdr> list = worksheetConditionService.findAllHdr();
			String msg = "SUCCESS";
			if (list.isEmpty()) {
				msg =  MessageConstants.TA.NOT_DATA;
			}
			responseData.setData(list);
			responseData.setMessage(msg);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
